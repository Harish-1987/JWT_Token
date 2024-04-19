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
