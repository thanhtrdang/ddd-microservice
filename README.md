# ddd-microservice
Fix starting Netty error: https://thoeni.io/post/macos-sierra-java/
Fix terminal JUnit 4+5: https://github.com/junit-team/junit5-samples/tree/master/junit5-gradle-consumer

- Improve the template with some dummies - service, repository..
- Improve sample *Specs: DB, Cache, External Integrations.. So when we ping the microservice, if return pong, then we ensure all configurations correctly.
- Apply Maven parent
- Deploy and apply AWS, Spring Cloud
- Isolate running test types: Integration, Unit, Stress.. 

cd ${project root}
gradle build
cd ${project root}/build/libs
java -jar -Dspring.profiles.active=dev ddd-microservice-1.0.1-SNAPSHOT.jar