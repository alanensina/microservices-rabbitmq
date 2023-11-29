
# Microservice with CloudAMQP and SMTP

![alt text](https://i.imgur.com/G4Ph28Y.jpg)

This is a simple project replicating the [class](https://youtu.be/ZnECi2gatMs?si=gyCItdZQn80QmDOv) given by [@MichelliBrito](https://github.com/MichelliBrito) of a simple microservice that uses Spring Boot, Spring Web, Spring Data, Spring Validation, RabbitMQ (CloudAMQP), PostgreSQL and Gmail SMTP.

The project consists in create an User and then, send an email with a welcome message.

## How to run  the project
- Run the Docker compose in your machine
- Set up the application.properties in both projects (User and Email)
    - Set the [AMQP address](https://www.cloudamqp.com/)
    - Set the username and password of your [Gmail SMTP](https://support.google.com/accounts/answer/185833)

## Routes
After set and run both projects (User and Email), there's a CRUD available to manage an User.

### Create an user
This method will create an user and will send an email with a welcome message
```
POST /users

{
    "email": "test@gmail.com",
    "name": "Alan Ensina"
}
```

### Update the user
This method will update the user. If the ID is not found, an error message will be thrown
```
PUT /users

{
    "id": "a6206a12-3247-48b4-865d-cadc68aca9e3",
    "name": "Alan Ensina",
    "email": "test@gmail.com"
}
```
### List all users
This method will return all the users from the database
```
GET /users
```

### Get an user
This method will return an specific user from the database by his ID.
```
GET /users/{id}
```

### Delete the user
This method will delete an specific user from the database by his ID.
```
DELETE /users/{id}
```