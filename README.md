# Mitigia

### Fullstack Application for Junior DevOps Position

---

## Project Overview 

Mitigia is a fullstack web application designed as a showcase for Junior DevOps skills. This project demonstrates expertise in backend development, DevOps pipelines, and containerization practices, utilizing **Java** and **Docker** to establish efficient deployment workflows and server management.

The project is accessible at [http://52.57.148.133:8080/](http://52.57.148.133:8080/).

## Key Features

- **Backend**: Java-based backend with Spring Boot framework.
- **Containerization**: Deployed using Docker for efficient environment management.
- **Configuration Management**: Integrates with Docker Compose for multi-container orchestration.
- **Automated Testing**: Configured for CI/CD integration with automated test setups.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Z20LET/Mitigia.git
   cd Mitigia
   ```
2. **Run with Docker Compose**:
   ```bash
   docker-compose up --build
   ```

2. **Load Databse**:
   ```bash
   psql -h localhost -d carbon_footprint_db -p 5432 -U postgres <mitigia.sql
   ```

## Technologies Used

- **Java**: Core application logic.
- **Spring Boot**: For backend framework.
- **Docker & Docker Compose**: For containerized development and deployment.
- **CI/CD**: Designed for automated builds and tests.

## Project Structure

- **/src**: Contains the application source code.
- **/data**: Placeholder for data-related files and configurations.
- **Docker & Compose YML**: Dockerfile and `docker-compose.yml` for container management.

## Contributing

Contributions are welcome! Please create a pull request or open an issue if you’d like to suggest improvements or features.

## License

This project is licensed under the MIT License.

---

> **Note for Reviewers**: This project was built as a practical demonstration of DevOps and backend capabilities, specifically for a Junior DevOps role. You can view the deployed application at [http://52.57.148.133:8080/](http://52.57.148.133:8080/).