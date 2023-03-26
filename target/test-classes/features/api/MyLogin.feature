Feature: APIUS_001

  #When a POST body consisting of valid data is sent to the api/register endpoint,
  # it should be verified that the status code returned is 201 and
  # the response message information is "Successfully registered".

  Scenario: TC_01
    #* User takes the bearer token
    * User sets the post request body
    * User can see the status code 201 and response message information


  Scenario: TC_02
    # It should be verified by using the API connection,
    # whether the client created via the API by sending a post request is in the customer lists or not.
  #* User sends the post request
  * User can see what he added
  @API
    Scenario: TC_03
      #When a POST body consisting of valid data is sent to the api/register endpoint,
          # it should be verified that the data in the response body
          # (first_name, last_name, username, email, phone) is the same as the data sent in the request body.
      * User can see related data what he added
