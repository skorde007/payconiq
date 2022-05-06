# payconiq
Payconiq Assignment

To Build Docker Image Use Below Command: 
docker build -f Dockerfile -t payconiq .

To Run Docker Container Use Below Command: 
docker run -p 9999:9999 payconiq

To Access Swagger UI for testing API endpoints use below url: 
http://localhost:9999/payconiq/swagger-ui.html

To Access H2 DB for checking records use below url: 
http://localhost:9999/payconiq/h2-console
