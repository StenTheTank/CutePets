## Running the application

### Backend
The backend is configured to run on localhost:8080
You can run the backend by executing bootRun for the springboot application inside backend or by running the executable file 
called "Backend-0.0.1-SNAPSHOT.jar" If you run the .jar file, then afterwards to shut down the backend you can use
taskmanager.


### Frontend
The Frontend is configured to run on localhost:4200
To run the frontend, navigate to the frontend directory and use the command ng serve.
If you do not have npm or angularCLI, then run the following commands first (all inside the frontend directory).



```bash
npm install
npm install -g @angular/cli
```
and then afterwards
```bash
ng serve
```

# Summary

My assignent is divided into 2 parts that work together.
There is an angular frontend and a springboot backend. The application has a login page, with introductory info and accounts that are saved to the database. Afer logging in a user can see all the pets they have added. A user can add more pets. A pet has a name, IDcode, furColor, type and country. A user can also sort the table by every category.




| Tasks                   | Answer                                                                                                                                                                                                                                                           |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Time  spent (h)         | ~8h                                                                                                                                                                                                                                                              |
| SpringBoot  | I Made a springBoot backend, that initialises a database, fills it with some data and implemets a RESTful API, where you can make GET,POST, DELETE requests.                                                                                                     |
| liquibase | I had previously not heard of liquibase so I had to learn it for this assignment. I use it to create the database and fill it with defaultdata.                                                                                                                  |
| Angular     | I had experience with only react and vue up until this point so I so I had to watch some tutorials. I added routing and made a login page, where there is a short description of the application, aswell as usernames and password that are saved in the database. |
| Bootstrap               | I added some bootstrap styling for my applications buttons and invalid messages.                                                                                                             |
 
Right now my application uses POST requests as a PUT/PATCH request. 
This was not my intention but since right now it works, I did not fix it. 
If I had more time to work on this project, then I would have made a separate Edit and Add system.
The logging in system and only seeing your own pets is not currently implemented very efficiently. Right now it just compares the username used to log into the page to every pets "author" field. Furthemore there is no encryption of any kind right now.
Right now there is also no registration system and no system for logging out. 

### Candite name:
#### Sten Marcus Nelson
#### stenmarcusnelson@gmail.com