import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Prompt the user to enter the path to the YAML file
        System.out.println("Enter the path to the YAML file:");
        // Read the file path from the user's input
        String filePath = scanner.nextLine();

        try {
            File file = new File(filePath);
            // Check if the file exists, is a regular file, and is readable
            // If not, throw an IOException with a detailed message
            if (!file.exists() || !file.isFile() || !file.canRead()) {
                throw new IOException("**Either the file do not " +
                        "exist or the input is not a file or the user " +
                        "cannot read the specified file**");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            System.out.println("Exiting the program...");
            return;
        }
        // Parse the input YAML file to retrieve a list of endpoints
        List<Endpoint> endpoints = YamlParser.parseYAMLFile(filePath);
        // Check if endpoints is null
        if(endpoints==null){
            System.out.println("Parsing YAML file returned null");
            System.out.println("Exiting the program...");
            return;
        }

        // Start the Health Check process of the parsed URL
        HealthCheck checker = new HealthCheck();
        // Create a ScheduledExecutorService to run the health check at fixed intervals
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Schedule the health check task to run every 15 seconds
        executor.scheduleAtFixedRate(() -> checker.checkEndpoints(endpoints), 0, 15, TimeUnit.SECONDS);

    }
}
