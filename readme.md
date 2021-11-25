Swagger was added to make calling the API a little bit easier please use localhost:8080/swagger-ui.html

More test should be created to handle negative cases some retry would be useful
As price should not be changing on the NBP side I would add @Cachable and configure some cache to hold the values that were already retrieved.
I was thinking about adding some React/Material-ui frontend to make this whole thing look better (in the end living doesnt award us enough time) 
I am doing some small POC with react and PHP (learning/refreshing knowledge on both)
My top react ability http://harmonogram-origin-poc.online/ 

Some older code can be seen https://exercism.org/profiles/drnoah