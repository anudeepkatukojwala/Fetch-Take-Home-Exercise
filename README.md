# Fetch-Take-Home-Exercise

## Overview
This project is a Java-based tool designed to periodically check the health of various web endpoints. It reads endpoint configurations from a YAML file, performs health checks at regular intervals, and logs the availability of these domains.

## Features
- Read endpoint configurations from a YAML file.
- Perform regular health checks (every 15 seconds) on a list of endpoints.
- Log the availability percentage of each domain.

## Getting Started

### Prerequisites
1) ```Java 18 or higher```
2) ```
   snakeyaml-2.1.jar:
   (This is must) You can find this jar file under src/main/resources/
    If you are using IntelliJ IDE, be sure to add this JAR file as Library
    under Project Structure if it is not already present.
   ```

## Usage Instructions

### Using IntelliJ IDEA

1. **Clone the Repository**:
    ```bash
   git clone https://github.com/anudeepkatukojwala/Fetch-Take-Home-Exercise.git
   
2. **Open the Project**:
- Open IntelliJ IDEA.
- Select `File > Open` and choose the project directory.

3. **Configure Project**:
- Ensure that the JDK 18 or higher is set correctly in `File > Project Structure`.
- Add `snakeyaml-2.1.jar` to your project's libraries: `File > Project Structure > Libraries > + > Java > [navigate to and select snakeyaml-2.1.jar]`. 
  You can find `snakeyaml-2.1.jar` file under src/main/resources/

4. **Run the Application**:
- Right-click on `src/main/java/com/fetch/project/Main.java`.
- Select `Run 'Main.main()'`.

5. **Enter the YAML File Path**:
- After the application starts, enter the path to your YAML file when prompted in the console.
- For testing, you can use the YAML file present in the below path
- Test YAML file path in project: `src/main/resources/testYamlFile`

### Using Command Line

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/anudeepkatukojwala/Fetch-Take-Home-Exercise.git
2. Navigate to the correct project directory to execute commands:
    ```bash
    cd Fetch-Take-Home-Exercise/
   ```
> **Note:**
> - You need to stay inside `Fetch-Take-Home-Exercise/` and outside `src/` directory to execute the below commands.
> - The correct directory to stay inside is the directory that directly contains the `src` folder.


3. **Compile the Project using below command**:
    ```bash
   javac -cp "src/main/resources/snakeyaml-2.1.jar" src/main/java/com/fetch/project/*.java

4. **Run the Application using below command**:
    ```bash
   java -cp "src/main/java/com/fetch/project:src/main/resources/snakeyaml-2.1.jar" Main

5. **Enter the YAML File Path**:
- After the application starts, enter the path to your YAML file when prompted in the console.
- For testing, you can use the YAML file present in the below path
- Test YAML file path in project: `src/main/resources/testYamlFile`

### Configuration
The application reads endpoint configurations from a YAML file. The YAML file should follow this format:
```angular2html
- name: (required)
  url: (required)
  method: (optional)
  headers:  (optional)
  body: (optional)

```

### Contact
``` 
Name: Anudeep Katukojwala
Email: anudeep.katukojwala@gmail.com
```