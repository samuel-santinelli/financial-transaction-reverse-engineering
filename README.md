# Financial Transaction Reverse Engineering and Fraud Detection

<img width="1536" height="1024" alt="ChatGPT Image 17 de jul  de 2025, 07_44_10" src="https://github.com/user-attachments/assets/ef93874a-c3c0-419f-bf28-0d2282315a15" />

## Project Overview

This project focuses on reverse engineering a legacy banking system to analyze financial transactions and detect potential frauds. The objective is to extract business rules and data flows from legacy code, reconstruct the logic in a modular system, and provide tools for transaction monitoring and fraud detection.

The system is developed in Java using Spring Boot and includes modules for code analysis, fraud detection algorithms, RESTful APIs, and data persistence.

## Features

- Static analysis of legacy Java code to extract transaction processing logic  
- Reconstruction of business rules in a modular architecture  
- Fraud detection using predefined rules and anomaly detection algorithms  
- REST API endpoints to access and manage transaction data  
- Data persistence with PostgreSQL  
- (Optional) Dashboard for visualization of transactions and alerts  

## Technologies

- Java 17+  
- Spring Boot  
- JavaParser (for code analysis)  
- PostgreSQL (or H2 for local testing)  
- JUnit 5 for testing  
- Maven for build and dependency management  

## Getting Started

### Prerequisites

- Java 17 or higher  
- Maven  
- PostgreSQL (or H2 for in-memory DB)  

### How to Run

1. Clone the repository:  
   `git clone https://github.com/samuel-santinelli/financial-transaction-reverse-engineering.git`  

2. Navigate to the project folder:  
   `cd financial-transaction-reverse-engineering`  

3. Build the project:  
   `mvn clean install`  

4. Run the application:  
   `mvn spring-boot:run`  

The application will start on `http://localhost:8081`.

## Project Structure

- `/analyzer` - Module for reverse engineering and code parsing  
- `/fraud-detection` - Module implementing fraud detection logic  
- `/controller` - REST API endpoints  
- `/model` - Domain models and database entities  
- `/repository` - Data persistence with Spring Data JPA  
- `/service` - Business logic services  
- `/config` - Application configuration

## Architecture Diagram

Below is a high-level architecture diagram illustrating the main components and their interactions in the system.

<img width="3840" height="3204" alt="Untitled diagram _ Mermaid Chart-2025-07-15-110617" src="https://github.com/user-attachments/assets/4811ec23-fd5b-4024-a40a-6148411574db" />
<img width="1536" height="1024" alt="ChatGPT Image 17 de jul  de 2025, 08_30_07" src="https://github.com/user-attachments/assets/adae4909-4c3d-4763-80b0-a070baa57485" />


---

## Contributing

Contributions are welcome. Please fork the repository and submit pull requests.

## License

MIT License

---

Made by Samuel Santinelli
