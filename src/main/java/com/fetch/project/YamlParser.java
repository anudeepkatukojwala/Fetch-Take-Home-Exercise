import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlParser {
    /**
     * Parses a YAML file and converts it into a list of src.main.java.com.fetch.project.Endpoint objects.
     *
     * @param filePath The path to the YAML file to be parsed.
     * @return A list of src.main.java.com.fetch.project.Endpoint objects extracted from the YAML file.
     */
    public static List<Endpoint> parseYAMLFile(String filePath){
        // Configure LoaderOptions for YAML parsing
        LoaderOptions options = new LoaderOptions();
        // Use a CustomClassLoaderConstructor for YAML parsing with specific class loader options
        CustomClassLoaderConstructor constructor = new CustomClassLoaderConstructor(Endpoint.class.getClassLoader(), options);
        // Create a new Yaml instance with the specified constructor
        Yaml yaml = new Yaml(constructor);
        // Initialize an empty list to store the endpoints
        List<Endpoint> endpoints = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            // Load all YAML data from the file
            Iterable<Object> objects = yaml.loadAll(inputStream);
            // Iterate over each object in the YAML file
            for (Object object : objects) {
                // Check if the object is a list
                if (object instanceof List) {
                    List<?> list = (List<?>) object;
                    // Iterate over each item in the list
                    for (Object item : list) {
                        // Check if the item is a map
                        if (item instanceof Map) {
                            Map<?, ?> map = (Map<?, ?>) item;
                            // Convert the map to an Endpoint object and add it to the list
                            Endpoint endpoint = mapToEndpoint(map);
                            endpoints.add(endpoint); // Add the endpoint to the list
                        }
                    }
                }
                else {
                    // Log if the object is not a list and return null
                    System.out.println("yaml.loadAll returned objects is not of List type");
                    return null;
                }
            }
        } catch (Exception e) {
            // Print the stack trace in case of an exception and return null
            e.printStackTrace();
            return null;
        }
        return endpoints;
    }
    /**
     * Converts a map to an Endpoint object.
     *
     * @param map A map representing the properties of an Endpoint.
     * @return An Endpoint object with properties set as per the map.
     */
    private static Endpoint mapToEndpoint(Map<?, ?> map) {
        // Create a new src.main.java.com.fetch.project.Endpoint object
        Endpoint endpoint = new Endpoint();
        // Set properties of the endpoint from the map
        endpoint.setName((String) map.get("name"));
        endpoint.setUrl((String) map.get("url"));
        endpoint.setMethod((String) map.get("method"));
        // Safely process headers map
        Object headersObj = map.get("headers");
        if (headersObj instanceof Map<?, ?>) {
            Map<String, String> headers = new HashMap<>();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) headersObj).entrySet()) {
                String key = safeCastToString(entry.getKey());
                String value = safeCastToString(entry.getValue());
                if (key != null && value != null) {
                    headers.put(key, value);
                }
            }
            endpoint.setHeaders(headers);
        }

        endpoint.setBody((String) map.get("body"));
        return endpoint;
    }
    private static String safeCastToString(Object obj) {
        return obj instanceof String ? (String) obj : null;
    }
}
