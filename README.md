# st-school-backend
backend of st-school project https://github.com/shin-nguyen/st-school-frontend
Build Status Coverage Status License
Requirements
For building and running the application you need:

JDK 1.8
Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.stschools.Application class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:
mvn spring-boot:run

This will create:
If you want to access the app from outside your OpenShift installation, you have to expose the st-school-backend service:

expose st-school-backend --hostname=https://st-school.herokuapp.com/

Copyright
Released under the Apache License 2.0. See the LICENSE file.
