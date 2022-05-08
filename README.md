# payconiq
Payconiq Assignment

This application is created by using Java 11 and Springboot. This is purely a java based application.

To build docker image use below command:
docker build -f Dockerfile -t payconiq .

To run docker container use below command:
docker run -p 9999:9999 payconiq

Implemented OpenAPI for API documentation. To access Swagger UI for testing API endpoints use below url: 
http://localhost:9999/payconiq/swagger-ui.html

Used H2 in-memory DB for CRUD operation. To access H2 DB for checking records use below url: 
http://localhost:9999/payconiq/h2-console
