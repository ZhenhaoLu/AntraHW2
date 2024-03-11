# Java Security

[//]: # (### Java Security)
The function implemented in this project:
* HipoLab Functions
  * get all: GET /universities 200
  * get by countries (CompletableFuture): GET /universities/complete?country=    200
  * get by countries (Single Thread): GET /universities/single?country=    200
  * Code: 
    * src/main/java/com/example/demo/controller/ControllerHipoLab.java
    * src/main/java/com/example/demo/entity/University.java
    * src/main/java/com/example/demo/service/inter/IHipoLabService.java
    * src/main/java/com/example/demo/service/inter/IAsynService.java (not used)
    * src/main/java/com/example/demo/service/impl/HipoLabService.java
    * src/main/java/com/example/demo/service/impl/AsyncService.java
* Java Security implements UsernamePasswordAuthentication and JWTAuthentication
  * log in: POST /login 200
    * username: user, password: password, return JWT token
  * Use JWT token to access HipoLab services.
  * Code:
    * src/main/java/com/example/demo/config/SecurityConfig.java
    * src/main/java/com/example/demo/controller/ControllerLogin.java
    * src/main/java/com/example/demo/entity/LogRequest.java
    * src/main/java/com/example/demo/exception/HandleSecurityException.java
    * src/main/java/com/example/demo/filter/JWTAuthFilter.java
    * src/main/java/com/example/demo/service/inter/IJWTService.java
    * src/main/java/com/example/demo/service/impl/JWTService.java
    * src/main/java/com/example/demo/service/impl/UserService.java (implements UserDetailsService)
* Other code:
  * Password Encoder and RestTemplate: src/main/java/com/example/demo/config/OtherConfig.java
  * Secret Key and HipoLab URL: src/main/resources/application.properties

[//]: # (* [Official Apache Maven documentation]&#40;https://maven.apache.org/guides/index.html&#41;)

[//]: # (* [Spring Boot Maven Plugin Reference Guide]&#40;https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/&#41;)

[//]: # (* [Create an OCI image]&#40;https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/#build-image&#41;)

