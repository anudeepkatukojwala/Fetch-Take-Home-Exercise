import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.System.exit;

public class HealthCheck {
    // HttpClient instance for making HTTP requests
    private final HttpClient httpClient;
    // Two map instances to track the number of successful and total requests per domain
    private final Map<String, Integer> successfulRequests = new HashMap<>();
    private final Map<String, Integer> totalRequests = new HashMap<>();
    // Constructor to initialize the HttpClient
    public HealthCheck(){
        /* Use the below commented code if you need your HTTP request
        to follow redirects for HTTP 3xx responses
        This might be useful for https://www.fetchrewards.com/ url
        as it was consistently returning 301 status code
        */
//        this.httpClient = HttpClient.newBuilder()
//                .followRedirects(HttpClient.Redirect.NORMAL)
//                .build();
        this.httpClient = HttpClient.newHttpClient();
    }
    /**
     * Checks the health of all endpoints provided in the list.
     *
     * @param endpoints A list of endpoints to check.
     */
    public void checkEndpoints(List<Endpoint> endpoints) {
        try {
            // Iterate over each endpoint and perform health check
            for (Endpoint endpoint : endpoints) {
                checkEndpoint(endpoint);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit(1); //Exit program since exception occurred
        }
        // Log the availability of each domain after health check
        logAvailability();
    }
    /**
     * Checks the health of a single endpoint.
     *
     * @param endpoint The endpoint to check.
     */
    private void checkEndpoint(Endpoint endpoint) {
        // Extract domain from URL and track the number of requests
        String domain = getDomainFromUrl(endpoint.getUrl());
        totalRequests.put(domain, totalRequests.getOrDefault(domain, 0) + 1);
        // Construct the HttpRequest with the given endpoint details
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(endpoint.getUrl()));

        // Configure request method and body if needed
        String method = endpoint.getMethod();
        if (!method.equals("GET")) {
            builder.method(method, HttpRequest.BodyPublishers.ofString(
                    endpoint.getBody() != null ? endpoint.getBody() : ""));
        } else {
            builder.GET();
        }

        // Add headers to the request if they are present
        Map<String, String> headers = endpoint.getHeaders();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        // Build the HttpRequest
        HttpRequest request = builder.build();

        try {
            long startTime = System.currentTimeMillis(); // Start time
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            long endTime = System.currentTimeMillis(); // End time
            long responseTime = endTime - startTime; // Calculate response time
            // Determine if the endpoint is up based on the response status code
            boolean isUp = response.statusCode() >= 200 && response.statusCode() < 300 && responseTime < 500;

//            System.out.println("Request is: "+request+": status code: "+response.statusCode()+": final uri: "+response.uri()+": response time: "+responseTime);
            // Track successful requests
            if (isUp) {
                successfulRequests.put(domain, successfulRequests.getOrDefault(domain, 0) + 1);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
            exit(1);
        }
    }

    /**
     * Logs the availability percentage of each domain.
     */
    private void logAvailability() {
        System.out.println("*****************************");
        // Calculate and log the availability percentage for each domain
        for (String domain : totalRequests.keySet()) {
            int total = totalRequests.get(domain);
            int successful = successfulRequests.getOrDefault(domain, 0);
            int availabilityPercentage = (int) ((double) successful / total  * 100);
            System.out.println(domain + " has " + availabilityPercentage + "% availability percentage");
        }
        System.out.println("*****************************");
    }
    /**
     * Extracts the domain from a given URL.
     *
     * @param url The URL to extract the domain from.
     * @eturn The domain extracted from the URL.
     */
    private String getDomainFromUrl(String url) {
        // Extract the domain from the URL
        URI uri = URI.create(url);
        String domain = uri.getHost();
//        System.out.println("Domain: "+domain);
        // Remove 'www.' prefix if present and return the domain
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
