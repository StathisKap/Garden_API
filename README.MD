# Garden-API
## Description
This is an API that uses SpringBoot, JUnit, Lombok, Mockito, MySQL, Swagger and JBDC to retrieve infromation about the state of a garden
and give commands to water it or perform other Functions

### Logic
The Spring boot app starts by defining a model of the object that we will be storing in the Database. 

It then uses a repository object to inerface with our Database and perform CRUD operations. Calls to the repo class happen from the functions defined in the Service class and those functions are called from our Controller class by Mapping each call to a web request.

The JUnit tests cover 85% of the code and use mockito to create temporary repos and service classes to isolate each Unit test.

Swagger is also installed to provide a nice front end for anyone looking to get familiar with the API.

## TODO
- The Unit tests don't all work as desired, so some work will be done on those
- Add DTOs
- Rework the Services so that it can be useful to an actual garden
- Add models for Air and Soil tempereature and humidity
- Potentially turn it into a Home Assitant addon to be used with my [Garduino](https://github.com/StathisKap/Garduino) project
