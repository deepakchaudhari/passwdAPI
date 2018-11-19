# passwd API

This is a minimal HTTP service that exposes the user and group information on
a UNIX-like system that is usually locked away in the UNIX /etc/passwd and /etc/groups files

## Development

Run the following commands in terminals to create a development experience

    mvn spring-boot:run

    To ensure everything worked, run:

    java -jar target/*.war

    Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Building for production

Application for production, run:

    mvn spring-boot:run

To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.


## Testing

To launch your application's tests, run:

    mvn clean test
