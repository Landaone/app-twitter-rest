# APP-TWITTER-REST
Microservice that consumes Tweets and based on criteria of
configuration persists them in a database for management through a REST API.
## First steps

1. Navigate in the code following this path: src/main/resources.
2. Add the API keys and Twitter Token in the file: **twitter4j.properties**.
3. In that same directory (src/main/resources) is the file **application.properties** there the values that will be used as parameters in the management of tweets are established. 
```
twitter:
  min-followers: 1500 - minimum number of followers a twitter account must have for its tweets to be persisted 
  languages: es,fr,it - Languages in which tweets must be written to be persisted
  num-hashtags: 10 - number of most used hashtags  
```
## Running app-twitter-rest

1. Plugin spring-boot de Maven:
```
$ mvnw spring-boot:run
```
The application starts by default on local port 8080.

##  H2 Database Access
With the application running, type in the browser ``/h2-console``, login with the following credentials:
* user: sa
* password:


## Exposed API endpoints
``
GET /tweets
`` Returns all the tweets stored in the database.

``
PATCH /tweets/{id}/valid
`` Change the value of the valid field to true of the tweet with the id passed as a parameter.

``
GET /tweets/validTweets/{user}
`` Returns all the validated tweets of a user (name) passed as a parameter.

``
GET /tweets/hashtags
`` Returns a list of the most used hashtags (default 10).

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* [Lombok library](https://projectlombok.org/features/all)

