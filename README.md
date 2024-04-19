STEP 1: go to src-> main -> resources -> create application.properties file and paste following details


spring.datasource.url=jdbc:mysql://localhost:3306/crud08?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=test

spring.jpa.hibernate.ddl-auto=update

## App Properties
app.jwt-secret= JWTSecretKeyItCanBeAnyRandomAplhabatNumericEtcSpecialCharactersAreNotAllowed432312340098
app.jwt-expiration-milliseconds = 604800000




STEP 2:  go to target folder-> classes folder -> create a file application.properties file and paste following details 
          {Note- you will get all these details when you register on Twilio website.}


spring.datasource.url=jdbc:mysql://localhost:3306/crud08?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=test

spring.jpa.hibernate.ddl-auto=update

## App Properties
app.jwt-secret= JWTSecretKeyItCanBeAnyRandomAplhabatNumericEtcSpecialCharactersAreNotAllowed432312340098
app.jwt-expiration-milliseconds = 604800000

========================================================================================================================================================================================
                                                                                     JWT TOKEN NOTES
                                                                                  =====================


1.> Token has Three parts - header, Payload, Signature.
    Header - Contains metadata about the token, such as the type of token (JWT) and the hashing algorithm used to generate the signature. 
    Payload -Include information about the user, expiration time of token and other details.
    Signature -   Cryptographic hash ensuring token integrity and authenticity.
    
2.> without spring security JWT Token can't be implemented.

3.> Flow of JWT Token - There is a GMAIL login page enter username and password click on Submit, username and password goes to Backend, Backend Verifies username and       password If valid Generate a token and and give it to the client, Client now for all the further request do to the server along with the request it will send the       Token.
4.> Who is responsible for generation JWT secreat key -> Server
5.> Advantages of JWT Token
   a.> with the token we can perform authorization and authentication
   b.> stateless communication no overhead on server
   c.> once we have token we can provide it to intigrated the aplication with other services just like we used Token key of  payment        gateway, sms services to intigrate.
   d.> expiry time can be set for JWT Token

=============================================
STEP TO IMPLEMENT code of jwt token
----------------------------------
step1> JwtAuthenticationEntryPoint class
step2> JwtAuthenticationFilter class
step3> JwtTokenProvider class
step4> BlogAPIException class just create it you can leave it empty
step5> JWTAuthResponse class
step6> modify signin method under AuthController folder which will return token back to the client







Test Developed JWT Token in Postman
=====================================

because of .antMatchers configuration from SecurityConfig class is depricated   http://localhost:8080/api/auth/signin  link is not accessable by every use so use Basic Auth and Username=gyan and Password=4444,  under Body provide follwoing json data
                {
    "usernameOrEmail":"gyan99@gmail.com",
    "password":"4444"
		}

click on Send button you will receive following response and generated JWT Token 

{
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJneWFuOTlAZ21haWwuY29tIiwiaWF0IjoxNzEzMDg1MTgxLCJleHAiOjE3MTM2ODk5ODF9.t7GzjvN15Fl_AIpXpXtyjF5cGCV7Ij3Fu-mjbdWkXlTh9s4clXip-p9PvthkV7FS6lPoAEvDLKGzGFZTxXytOQ",
    "tokenType": "Bearer"
}

Now we will use this JWT Token to access the create post method which can only be used by ADMINS  and user_roles Table you can verify that gyan is ADMIN lets use generated token to access Post method and create new Department.

  link  POST    http://localhost:8080/api/Dep/save
  select Bearer Token under Authentiation Tab  and provide the data in json formate in Body
