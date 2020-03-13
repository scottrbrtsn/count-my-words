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