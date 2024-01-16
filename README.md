# Fetch-Take-Home-Exercise

## Overview
This project is a Java-based tool designed to periodically check the health of various web endpoints. It reads endpoint configurations from a YAML file, performs health checks at regular intervals, and logs the availability of these domains.

## Features
- Read endpoint configurations from a YAML file.
- Perform regular health checks (every 15 seconds) on a list of endpoints.
- Log the availability percentage of each domain.

## Getting Started

### Prerequisites
1) ```Java 11 or higher```
2) ```
   snakeyaml-2.1.jar:
   (This is must) You can find this jar file under src/main/resources/
    If you are using IntelliJ IDE, be sure to add this JAR file as Library
    under Project Structure if it is not already present.
   ```


### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/anudeepkatukojwala/Fetch-Take-Home-Exercise-Anudeep-Katukojwala.git

2. Navigate to the project directory:
   ```bash
    cd Fetch-Take-Home-Exercise-Anudeep-Katukojwala/src/main/java/com/fetch/project/
3. Make sure you have snakeyaml-2.1.jar file as described in Prerequisites

### Usage
- Run the Main class in the project.
- When prompted, enter the valid path to your YAML file containing the endpoint configurations.

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