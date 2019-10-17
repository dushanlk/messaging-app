## Messaging App 

#### Technical stack

1. JDK 11
1. Maven
1. Spring boot
1. JMS
1. H2 Embedded Database

#### Prerequisites

1. JDK 11
1. Maven

#### How to build and run

* With test cases
    * `./build-run.sh`
* Without test cases
    * `./build-run.sh skip-tests`
    
#### How to send messages using JMeter

* Import `jms_publisher.jmx` file to JMeter application and click start button.
* It will send `100` concurrent messages.

#### Limitations

1. Test case might fail if undelivered messages remain in the Queue. 

    `Solution: Run without test cases to clear the queue and run again with test cases.`
   
#### Paid attention on followings

* should have good test coverage.

> Added test case `com.app.messaging.MessagingAppTest`

* the app should not lose message if the database connections goes down.

> Handled by publishing message back to the Queue in case of a failure. So the message won't lose and it will come back to the application.
> 
> `jmsTemplate.convertAndSend("cart", message);`
>
> This can be implemented by having in an in-memory data structure, but it is not good when considering the scalability

* The app should be able to auto create table if the table does not exist in database.

> Implemented with the support of Spring Data JPA.

* Self-healing from database issue without human intervention.

> Implemented by adding an Exception catch. So the application process won't terminate.

* Good fault tolerate.

> 2nd and 4th point helps to have good fault tolerance.