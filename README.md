# springboot-web-login

This project started as a copy of some code from a Spring Security example.
I believe it was this one:  
https://github.com/spring-guides/gs-securing-web

## application database

I'm using an H2 in-memory database for this example app. There is no need to associate this with a real database.

The in-memory database uses the file `src/main/resources/data.sql` for initialization at startup time.

## Spring Boot actuators

I enabled all the Spring Boot actuators. For reference:   
https://www.baeldung.com/spring-boot-actuators
