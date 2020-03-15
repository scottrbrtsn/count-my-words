# Count My Words
## Assignment:

1. As a developer I want a REST service with a single endpoint that accepts a json message with two fields.."id" and "message". (example: { "id": "123", "message": "hello world" })

- So that endpoint returns a json document with a single field "count" that contains the total number of words contained in all the messages received to that point.

- For example, if the first message contains 3 words it would responsd with count = 3. If the next message contains 5 words it would respond with count = 8.


2. As a developer I want the service to ignore messages with duplicate ids. (i.e. ids that have already been processed) so that I don't duplicate work.

## Heroku
This app is running on Heroku.  Please understand, the Heroku free version puts the service to sleep after some time, so when it is hit with a request, the first request will take a few seconds to wake it up. 
If ths service goes to sleep, it will reset the db. Otherwise, it will keep a total, and so other users (me, another team member) will affect running totals.  Check the full list of processed words to verify total.

1. Add a phrase, e.g. `curl -d '{"id":"1", "phrase":"1 2 3"}' -H "Content-Type: application/json" -X POST https://count-my-words.herokuapp.com/phrases/count`
2. Goto `https://count-my-words.herokuapp.com/phrases/` to see the full list of processed phrases

## Extra Work 
### i.e. A basic React UI as a demonstration of the service to a user
Because I believe in small vertical slices as a single unit of work.

3. As a user I want to be able to type a word or phrase into a web browser and it tell me how many words are in the phrase so that I don't have to count.

4. As a user I want to be able to log into a public facing web page (production env) and type my word or phrase so that I don't have to run it locally. 

Very basic UI running at `https://scottrbrtsn.github.io/count-my-words-ui/`
1. Enter your id (any string), and your phrase to count words, 
2. Click count
3. Code `https://github.com/scottrbrtsn/count-my-words-ui`

## To Run
### Project Dependencies
- @Requires java 8+
- @Requires maven
1. `git clone https://github.com/scottrbrtsn/count-my-words.git`
1. `cd count-my-words`
2. Run tests `mvn test` (#tDD)
3. Delete previously built java files `mvn clean`
3. Compile java fils `mvn compile`
4. Build executable .jar `mvn install`
5. All of the above `mvn clean install`
	-  This is preferred.
	-  When working with nested .jar projects, one ought build their .jars so other projects can reference any changes.  
	-  Running tests during every build is also helpful during development.
6. Run the project `mvn spring-boot:run`
7. Delete previous compiled versions and run the project `mvn clean spring-boot:run`
8. Feel free to create aliases to simplify any of the above commands:  add `alias mvnrun = "mvn spring-boot:run"` to your ~/.bashrc or ~/.zshrc
9. The service will run at `http://localhost:9000/`
10. Run the following command to test the service: `curl -d '{"id":"1", "phrase":"1 2 3"}' -H "Content-Type: application/json" -X POST http://localhost:9000/phrases/count`
11. Check the H2 Db at `http://localhost/9000/console` and select `jdbc:h2:~/count` U:sa PW:
12. SwaggerAPI: Go to `http://localhost:9000/v2/api-docs` for basic api documentation, or `http://localhost:9000/swagger-ui.html` for pretty api documentation.

please email or call me if you have any problems with the above.  

Notes
- While this service is running, it persists all messages it processes into an H2 embedded DB.  This will be reset if the service is reset.  To permanently persist, add a story to the backlog to convert H2 to Postgres or some other DB.  

 ## Architectural Design explanation
The skeletal structure, in addition to your typical MVC, has its roots in Juval Lowy's iDesign architectural master class.  I have serendipitously worked with a number of architects who also happened to be alumni from Lowy's training.  He also speaks a great deal about actors and the actor model, and is where I studied that as well. 
- http://www.idesign.net/About

### Basic design rules
- Controllers only call managers or rarely directly to repositories
- Managers call repositories to get data from a database or save
- Managers call services to send the data to be processed
- Services do the common heavy lifting of business logic

## DB
#### Tables
- Totals: keeps track of totals of any type.  Right now we just have word count totals (#scalability)
- Phrases: keeps track of the phrases already processed.

## Actors
Juval Lowy has a talk about the actor model, I recently watched this past year.  
- https://www.youtube.com/watch?v=ukgjQaiZDYM
- So conceptually, I have at least some knowledge.  Though, I had not implemented anything outside of this project prior.  
- I took advantage of this project to experiment with the idea.
- The endpoint in WordCountController.java `/phrases/count/akka` calls into an Actor instead of the basic java service. 
- `curl -d '{"id":"1", "phrase":"1 2 3"}' -H "Content-Type: application/json" -X POST https://count-my-words.herokuapp.com//phrases/count/akka`

## TODO
1. Combine Phrase.java into a single class. There was a scoping issue with how actors work. So the Phrase.java jpa entity class couldn't be easily reused.  
1. Validate whether or not a string of chars not separated by a space are actually a word.
    - clearly this would be a significant undertaking beyond the scope of this exercise, but not completely impossible.
    - this could probably be done by hooking into some spell-check api
2. Migrate from H2 embedded DB to Postgres for true persistence.
3. Study the Actor model using akka and flesh out the implementation more.
4. Add GraphQl to endpoints
5. Offer Websocket connectivity for a running total (reactive endpoints)
7. Hook up graphQL over websocket.

