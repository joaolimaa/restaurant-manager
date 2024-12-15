package fiap.restaurant_manager.cucumber.steps;

import fiap.restaurant_manager.adapters.api.dto.UserDTO;
import fiap.restaurant_manager.domain.entities.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static org.junit.Assert.*;

@AutoConfigureMockMvc
public class UserSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private ResponseEntity<UserDTO> response;

    @Given("I have valid cpf: {string}")
    public void i_have_valid_user_data(final String cpf) {
        // If CPF is valid, we shouldn't have an exception
        new User("", "", cpf);
    }

    @When("I send a POST request to {string} with the following data : name: {string}, email: {string}, cpf: {string}")
    public void i_send_a_post_request_to_users_with_the_data(
        final String endPoint,
        final String name,
        final String email,
        final String cpf
    ){
        final UserDTO userData = new UserDTO (
            name,
            email,
            cpf
        );

        response = restTemplate.postForEntity(endPoint, userData, UserDTO.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().id());

        System.out.println(response.getBody().id());
    }

    @Then("I should receive a 201 Created response for users")
    public void i_should_receive_a_created_response_for_users() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Given("I have a valid API endpoint for users")
    public void i_have_a_valid_api_endpoint_for_users() {
        // API endpoint initialized
        System.out.println("API endpoint initialized " + true);
    }

    @When("I send a GET request to {string} expecting a collection")
    public void i_send_a_get_request_to_users_expecting_a_collection(final String endPoint) {
        final ResponseEntity<Collection<UserDTO>> collectionResponse = restTemplate.exchange(
            endPoint,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );
        assertNotNull(collectionResponse);
        assertNotNull(collectionResponse.getBody());
        assertFalse(collectionResponse.getBody().isEmpty());
        System.out.println(collectionResponse.getBody());

        final UserDTO userDTO = collectionResponse.getBody().stream().findFirst().orElseThrow();

        response = new ResponseEntity<>(userDTO, collectionResponse.getStatusCode());

        System.out.println(response.getBody());
    }

    @When("I send a GET request to {string} no collection")
    public void i_send_a_get_request_to_users_no_collection(final String endPoint) {

        response = restTemplate.getForEntity(endPoint, UserDTO.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Then("I should receive a 200 OK response")
    public void i_should_receive_a_ok_response() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Given("The user with id {string}, should be retrieve")
    public void user_exists(final String id) {}

    @And("the response body should contain the details of the user with ID {string}")
    public void user_details(final String id) {
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
        String bodyId = Optional.ofNullable(response.getBody().id()).orElseThrow() + "";
        assertEquals(bodyId, id);
    }

    @Given("a user with ID {string} exists in the system with the following data : name: {string}, email: {string}, cpf: {string}")
    public void aUserWithIDExistsInTheSystem(
            final String id,
            final String name,
            final String email,
            final String cpf
    ) {
        var getResponse = restTemplate.getForEntity("/api/users/1", UserDTO.class);
        assertNotNull(getResponse.getBody());
        assertEquals(getResponse.getBody().id()+"", id);
        assertEquals(getResponse.getBody().name(),  name);
        assertEquals(getResponse.getBody().email(), email);
        assertEquals(getResponse.getBody().cpf(),   cpf);
    }

   @When("I send a PUT request to {string} with the following data:")
   public void iSendAPUTRequestToWithTheFollowingData(final String endPoint, final Map<String, String> userData) throws Exception {
       // Convert the userData map to JSON
       String jsonContent = objectMapper.writeValueAsString(userData);

       // Set the headers
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);

       // Create the HTTP entity
       HttpEntity<String> entity = new HttpEntity<>(jsonContent, headers);

       // Perform the PUT request
       try{
            response = restTemplate.exchange(endPoint, HttpMethod.PUT, entity, UserDTO.class);
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
       System.out.println(response.getBody());
   }

   @Then("the response status should be {int}")
   public void theResponseStatusShouldBe(int expectedStatus) {
       // Verify the response status
       assertEquals(response.getStatusCodeValue(), expectedStatus);
   }

   @And("the response body should contain the updated user details as follows id: {string}, name: {string}, email: {string}, cpf: {string}")
   public void theResponseBodyShouldContainTheUpdatedUserDetails(
       final String id,
       final String name,
       final String email,
       final String cpf
   ){
       UserDTO actualUser = response.getBody();
       assertNotNull(actualUser);
       assertEquals(actualUser.id()+"", id);
       assertEquals(actualUser.name(),  name);
       assertEquals(actualUser.email(), email);
       assertEquals(actualUser.cpf(),   cpf);

   }

    @When("The user with id {string}, should exists")
    public void the_user_1_should_exists(final String id) {

        response = restTemplate.getForEntity("/api/users/" + id, UserDTO.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println(response.getBody());
    }

    @Then("I send a DELETE request to {string}")
    public void delete(final String endPoint) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        try{
            var delete = restTemplate.exchange(endPoint, HttpMethod.DELETE, entity, Void.class);

            response = new ResponseEntity<>(null, null, delete.getStatusCode());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(response.getBody());
    }
}

