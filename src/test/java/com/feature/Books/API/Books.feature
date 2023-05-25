Feature: Books Api TestCase

  Background: 
    Given I am requesting "BaseURI"

  @ResourceName_Farhan @High @P1 @smoke
  Scenario: TC_001_Get_API
    Given Load Testdata form Specific Api sheet "BOOKS-API"
    Then I go to "/books" and "GET" Api Request Saved in Json File "GetbookDetailsOutput.json" without charset
    Then Save Final Response in "GetbookDetailsOutput.json" output File.
    Then I am validating object "name[0]" for string value "The Russian" For "BOOKS-API".
    Then I am validating object "type[0]" for string value "fiction" For "BOOKS-API".
    Then I am validating object "name[1]" for string value "Just as I Am" For "BOOKS-API".
    Then I am validating object "type[1]" for string value "non-fiction" For "BOOKS-API".
    Then I am validating object "name[2]" for string value "The Vanishing Half" For "BOOKS-API".
    Then I am validating object "type[2]" for string value "fiction" For "BOOKS-API".
    Then I am validating object "name[3]" for string value "The Midnight Library" For "BOOKS-API".
    Then I am validating object "type[3]" for string value "fiction" For "BOOKS-API".
    Then I am validating object "name[4]" for string value "Untamed" For "BOOKS-API".
    Then I am validating object "type[4]" for string value "non-fiction" For "BOOKS-API".

  @ResourceName_Farhan @High @P1 @smoke
  Scenario: TC_002_POST_API_With Token
    Given Load Testdata form Specific Api sheet "BOOKS-API"
    Then Create Input File for json from Excel Sheet
    Then I go to "/api-clients/" and "POST" Api Request Saved in Json File "PostTestData.json" without charset
    Then Save Final Response in "PostClientTokenResponse.json" output File.
    Then Get the Token Number From "accessToken" Response
    Then Create Input File for json from Excel Sheet
    Then I go to "/orders" and "POST" Api Request Saved in Json File "CreateOrderidTestData.json" with Barear Token
    Then Get the Order Number From "orderId" Response
    Then Save Final Response in "PostOrderIDResponse.json" output File.
    Then I go to "/orders/:" and "PATCH" Api Request Saved in Json File "PatchjasonTestData.json" with Barear Token
    Then Save Final Response in "PatchOrderResponse.json" output File.
    Then I am validating object "error" for string value "No order with id :." For "Order".
    Then I go to "/orders/:" and "DELETE" Api Request Saved in Json File "DeleteTestdataRequest.json" with Barear Token
    Then Save Final Response in "DeleteTestdataResponse.json" output File.
    Then I am validating object "error" for string value "No order with id :." For "Order".
