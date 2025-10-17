# bank-app.asad
## Overview
 - A banking system using **Microservices architecture** (Accounts, Loans, Cards) built with **Spring Boot and Spring Cloud**, featuring **Eureka for service discovery**, **Config Server for centralized configuration**, and **Gateway Server for API routing.**
 - Each service is **containerized with Docker** for easy deployment and uses an H2 in-memory database.
   
##  Microservices
 - **Accounts Service** – Manages customer account details.  
 - **Loans Service** – Handles loan records.  
 - **Cards Service** – Manages card data and limits.
 - **Config Server** – Centralized configuration for all services.  
 - **Eureka Server** – Service registry for service discovery. 
 - **Gateway Server** – Routes client requests to respective microservices.
   
## Features
 - **Microservices Architecture** – Independent services for accounts, loans, and cards (Performs CRUD on these services). 
 - **Service Discovery** – Auto-registration and lookup via **Eureka Server**.
 - **Centralized Configurations** – Managed through **Spring Cloud Config Server**. Config repo has configuration related to three different environments (default, production and qa).
 - **API Gateway** – Single entry point for all service endpoints.
 - **Swagger UI** - Properly organized API Documentation.
 - **In-memory H2 Databases** – Lightweight persistence for development and testing.   
 - **Dockerized Setup** – Each service containerized for isolated deployment.
 - **Feign client** - Facilitates Inter-Service Communication.
 - **DTO Mapping, Validation & Exception Handling** – Ensures clean, consistent APIs.

## Tech Stack
 - Java 21, Spring Boot, Spring Cloud (Eureka, Config, Gateway), Spring Data JPA, H2 Database, Docker.

 
 
 

