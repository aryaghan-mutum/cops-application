### Vedic Knowledge Systems (VKS)

#### Tools Used
```properties
Testing framework  -> Junit 5
Build Management   -> Maven
Rest API           -> Springframeworks WebClient
Method Annotations -> Spring Framework and Lombok
```

#### How to run tests

#### How to generate Code Coverage 
- `mvn clean test`
- `mvn jacoco:report`
- go to `target/site/jacoco/index.html`

#### How to generate test reports

#### How to generate Contract test
- the client will use a mock provider
- the provider will use a mock client
- Effectively, the tests are done against the contract.

The @PactTestFor annotation takes care of starting the HTTP service, and can be put either on the test class, or on the test method.

#### Code coverage

#### Authors
- [Anurag Muthyam (anu.drumcoder@gmail.com)](https://github.com/aryaghan-mutum)



