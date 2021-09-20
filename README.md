[![Java CI with Maven](https://github.com/ToMrArcher/Leksjon-3-Http-Client/actions/workflows/maven.yml/badge.svg)](https://github.com/ToMrArcher/Leksjon-3-Http-Client/actions/workflows/maven.yml)

# Leksjon-3-Http-Client

Simple HTTP Client. 

Usage: 
```java
HttpClient httpClient = new HttpClient("HOSTNAME", PORT, "PATH");
httpClient.getHeader("Connection"); //returns for example "keep-alive"
httpClient.getStatusCode(); //returns for example 200, 404, etc
httpClient.getBody(); //returns the body of the request, for example a HTML document.
```
