# Count My Words
Assignment:

1. As a developer I want a REST service with a single endpoint that accepts a json message with two fields.."id" and "message". (example: { "id": "123", "message": "hello world" })

- So that endpoint returns a json document with a single field "count" that contains the total number of words contained in all the messages received to that point.

- For example, if the first message contains 3 words it would responsd with count = 3. If the next message contains 5 words it would respond with count = 8.


2. As a developer I want the service to ignore messages with duplicate ids. (i.e. ids that have already been processed) so that I don't duplicate work.

3. As a user I want to be able to type a word or phrase into a web browser and it tell me how many words are in the phrase so that I don't have to count.

4. As a user I want to be able to log into a public facing web page (production env) and type my word or phrase so that I don't have to run it locally. 


Project Dependencies
@Requires java 8+
@Requires maven

To Run
1. `git clone https://github.com/scottrbrtsn/count-my-words.git`
2. Run tests `mvn test` (#tDD)
3. Compile `mvn clean`
4. Build executable .jar `mvn install`
5. All of the above `mvn clean install`
	-  This is preferred.
	-  When working with nested .jar projects, one ought build their .jars so other projects can reference any changes.  
	-  Running tests during every build is also helpful during development.
6. Run the project `mvn spring-boot:run`
7. Build and run the project `mvn clean spring-boot:run`
8. Feel free to create aliases to simplify any of the above commands.
9. The service will run at `http://localhost:9000/`
10. Run the following command to test the service
- `curl -d '{"id":"1", "phrase":"1 2 3"}' -H "Content-Type: application/json" -X POST http://localhost:9000/phrases/count`
11. Check the H2 Db at `http://localhost/9000/console` and select `jdbc:h2:~/count`
12. Go to `http://localhost:9000/v2/api-docs` for basic api documentation, or `http://localhost:9000/swagger-ui.html` for pretty api documentation.


Notes
- While this service is running, it persists all messages it processes into an H2 embedded DB.  This will be reset if the service is reset.  To permanently persist, add a story to the backlog to convert H2 to Postgres or some other DB.  

 