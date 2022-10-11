# Fetch Rewards Coding Exercise - Backend Software Engineering

Rest service built using Java and Spring Boot to accepts HTTP requests and returns responses based on the conditions outlined.

# Benefits of choosing JAVA SpringBoot:

- It reduces lots of development time and increases productivity. 
- It is very easy to integrate Spring Boot Application with its Spring Ecosystem like Spring JDBC, Spring ORM, Spring Data, Spring Security.
- It provides Embedded HTTP servers like Tomcat, Jetty etc. to develop and test our web applications very easily.
- It provides lots of plugins to develop and test Spring Boot Applications very easily using Build Tools like Maven and Gradle as well as work with embedded and in-memory Databases very easily. 
- It helps to avoid all the manual work of writing boilerplate code, annotations, and complex XML configurations.
- Provides admin support – you can manage via remote access to the application.
- Offers flexibility in configuring XML configurations, Java Beans, and Database Transaction.

# Future Improvements:

- Create database connectivity to store Transaction points.
Creating payer and transaction database and instead of using in memory storage where user have to define transaction points every time server restarts.
- Using JpaRepository store and inject transaction/spend points. Use @Query("SELECT e FROM Payer e WHERE e.payer = :payer"). Easy selection of payer timestamp using query injection @Query("SELECT u FROM Transaction u ORDER BY u.timestamp ASC").

- Create basic frontend to add transaction points and show spend points instead of using Postman.

# Instruction to run project:

- Install JAVA JDK 11
- Install SpringBoot IDE like IntelliJ or Eclipse 
- Clone the repository gh repo clone MuditMJJoshi/FetchRewardExercise
- Open the project in the IDE
- Run \src\main\java\com\example\FetchRewardsWebService\FetchRewardsWebServiceApplication.java
- Run using command line(windows) or terminal install Maven and add it to your environment variable.
- To test API use PostMan

# Background:

- Tracks points per payer. Each transaction record contains: payer (string), points (integer), timestamp (date).
- Earning points it is easy to assign a payer, we know which actions earned the points. And thus, which payer should be paying for the points.
- Spends points, payer don't know or care where points come from. Rules for spend first point:
- Oldest points to be spent first (oldest based on transaction timestamp, not the order they’re received)
- No payer's points to go negative.
- This Web Service provides routes to:
- Add transactions for a specific payer and date.
- Spend points using the rules above and return a list of { "payer": , "points": } for each call.
- Return all payer point balances.

# API Usage:

- Add transaction to payer
POST http://localhost:8080/api/v1/FetchRewardServices/addPoints/1
Copy to PostMan import RawText
curl -L -X POST 'http://localhost:8080/api/v1/FetchRewardServices/addPoints/1' -H 'Content-Type: application/json' --data-raw '{ "payer": "DANNON", "points": 200, "timestamp": "2020-10-31T15:00:00Z" }'
Request body:
{ "payer": "DANNON", "points": 200, "timestamp": "2020-10-31T15:00:00Z" }
Response body:
200 OK “Points added successfully”

- Spend transaction points
POST http://localhost:8080/api/v1/FetchRewardServices/spendPoints/1
Copy to PostMan import RawText
curl -L -X POST 'http://localhost:8080/ api/v1/FetchRewardServices/spendPoints/1' -H 'Content-Type: application/json' --data-raw '{ "points": 500 }'
Request body:
{ "points": 500 }
Response body:
[
    {
        "points": -500,
        "payer": "DANNON"
    }
]

- Balance of payer
GET http://localhost:8080/api/v1/FetchRewardServices/balance/1
Copy to PostMan import RawText
curl -L -X GET 'http://localhost:8080/api/v1/FetchRewardServices/balance/1'
Response body:
{
    "DANNON": 700
}

# Postman Example

# Testing:
- I tested my API using Postman instead of creating test in test folder. However one can easily use @SpringBootTest and @Test Assertions.assertEquals(value1, value2 ) to check edge cases and proper working of API.
 
