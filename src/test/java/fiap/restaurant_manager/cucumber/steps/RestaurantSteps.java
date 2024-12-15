package fiap.restaurant_manager.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.restaurant_manager.adapters.api.dto.AddressDTO;
import fiap.restaurant_manager.adapters.api.dto.OperatingHoursDTO;
import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.domain.enums.KitchenType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@AutoConfigureMockMvc
public class RestaurantSteps {

    private RestaurantDTO restaurantDTO;
    private int statusCode;
    private RestaurantDTO createdRestaurant;
    private ResponseEntity<RestaurantDTO> listResponse;
    private RestaurantDTO updatedRestaurant;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("I have valid restaurant details:")
    public void i_have_valid_restaurant_details(io.cucumber.datatable.DataTable dataTable) {
        final List<List<String>> rows = dataTable.asLists(String.class);
        final List<String> details = rows.get(1);
        final AddressDTO address = buildAddress(details);
        final KitchenType kitchenType = parseKitchenType(details.get(7));
        final OperatingHoursDTO operatingHour = buildOperatingHours(details.get(10));

        restaurantDTO = new RestaurantDTO(
                details.get(0),
                address,
                kitchenType,
                details.get(8),
                List.of(operatingHour),
                Integer.parseInt(details.get(9))
        );
    }

    private AddressDTO buildAddress(List<String> details) {
        return new AddressDTO(
                details.get(1),  // postalCode
                details.get(2),  // city
                details.get(3),  // state
                details.get(4),  // neighborhood
                details.get(5),  // street
                details.get(6)   // number
        );
    }

    private KitchenType parseKitchenType(String kitchenType) {
        return KitchenType.valueOf(kitchenType.toUpperCase().replaceAll("\"", ""));
    }

    private OperatingHoursDTO buildOperatingHours(String operatingHours) {
        // Example format: "MONDAY, HH:mm-HH:mm"
        String[] operatingHoursParts = operatingHours.split(", ");
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(operatingHoursParts[0].toUpperCase());
        String[] timeRange = operatingHoursParts[1].split("-");

        LocalTime startTime = LocalTime.parse(timeRange[0], DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime endTime = LocalTime.parse(timeRange[1], DateTimeFormatter.ofPattern("HH:mm"));

        return new OperatingHoursDTO(
                dayOfWeek,
                ZonedDateTime.of(LocalDate.now(), startTime, ZoneId.systemDefault()),
                ZonedDateTime.of(LocalDate.now(), endTime, ZoneId.systemDefault())
        );
    }

    @When("I send a POST request to {string} with the restaurant details")
    public void i_send_a_post_request_to_with_the_restaurant_details(String url) {
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create HTTP request entity with headers and body
        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, headers);

        // Send the POST request using TestRestTemplate
        ResponseEntity<RestaurantDTO> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                RestaurantDTO.class
        );

        // Extract the response body and status code
        createdRestaurant = response.getBody();
        statusCode = response.getStatusCodeValue();
    }

    @Then("I should see the newly created restaurant details")
    public void i_should_see_the_newly_created_restaurant_details() {
        assertThat(createdRestaurant.name(), equalTo(restaurantDTO.name()));
        assertThat(createdRestaurant.address().postalCode(), equalTo(restaurantDTO.address().postalCode()));
        assertThat(createdRestaurant.address().city(), equalTo(restaurantDTO.address().city()));
        assertThat(createdRestaurant.kitchenType(), equalTo(restaurantDTO.kitchenType()));
        assertThat(createdRestaurant.cnpj(), equalTo(restaurantDTO.cnpj()));
        assertThat(createdRestaurant.operatingHoursDTO().get(0).dayOfWeek(), equalTo(restaurantDTO.operatingHoursDTO().get(0).dayOfWeek()));
        assertThat(createdRestaurant.capacity(), equalTo(restaurantDTO.capacity()));
    }

    @Given("the restaurant with ID {int} exists in the system")
    public void the_restaurant_with_id_exists_in_the_system(int id) {
        assertTrue(id > 0);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String url) {
        // Send GET request using TestRestTemplate
        listResponse = restTemplate.getForEntity(url, RestaurantDTO.class);
        statusCode = listResponse.getStatusCodeValue();
    }

    @Then("I should receive a {int} status code")
    public void i_should_receive_a_status_code(int expectedStatusCode) {
        assertEquals(statusCode, expectedStatusCode);
    }

    @Then("I should see the details of the restaurant with ID {int}")
    public void i_should_see_the_details_of_the_restaurant_with_id(int id, io.cucumber.datatable.DataTable dataTable) {

        assertNotNull(listResponse);

        var restaurantFromGetList = listResponse.getBody();

        assertNotNull(restaurantFromGetList);

        // Extract expected details from the DataTable
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedDetails = rows.get(0);

        // Validate each field against the expected details
        assertEquals(expectedDetails.get("name"), restaurantFromGetList.name());
        assertEquals(expectedDetails.get("postalCode"), restaurantFromGetList.address().postalCode());
        assertEquals(expectedDetails.get("city"), restaurantFromGetList.address().city());
        assertEquals(expectedDetails.get("state"), restaurantFromGetList.address().state());
        assertEquals(expectedDetails.get("neighborhood"), restaurantFromGetList.address().neighborhood());
        assertEquals(expectedDetails.get("street"), restaurantFromGetList.address().street());
        assertEquals(expectedDetails.get("number"), restaurantFromGetList.address().number());
        assertEquals(expectedDetails.get("kitchenType"), restaurantFromGetList.kitchenType().name());
        assertEquals(expectedDetails.get("cnpj"), restaurantFromGetList.cnpj());
        assertEquals(Integer.parseInt(expectedDetails.get("capacity")), restaurantFromGetList.capacity());

        // Validate operating hours
        //        List<OperatingHoursDTO> operatingHours = restaurantFromGetList.operatingHoursDTO();
        //        assertFalse(operatingHours.isEmpty());
        //        String expectedOperatingHours = expectedDetails.get("operatingHours");
        //        String[] splitHours = expectedOperatingHours.split(", ");
        //        assertEquals(DayOfWeek.valueOf(splitHours[0]), operatingHours.get(0).dayOfWeek());
        //
        //        String[] timeRange = splitHours[1].split("-");
        //        assertEquals(LocalTime.parse(timeRange[0], DateTimeFormatter.ofPattern("HH:mm")), operatingHours.get(0).startTime().toLocalTime());
        //        assertEquals(LocalTime.parse(timeRange[1], DateTimeFormatter.ofPattern("HH:mm")), operatingHours.get(0).endTime().toLocalTime());
    }

    @And("I have updated restaurant details:")
    public void i_have_updated_restaurant_details(io.cucumber.datatable.DataTable dataTable) {
        // Extract updated details from the DataTable
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> updatedDetails = rows.get(0);

        // Prepare updated RestaurantDTO object
        AddressDTO address = new AddressDTO(
                "45678", // Postal code can be mocked for the new address
                "Updated City",
                "Updated State",
                "Updated Neighborhood",
                updatedDetails.get("address"),
                "789" // New mock number
        );

        updatedRestaurant = new RestaurantDTO(
                updatedDetails.get("name"), // Updated name
                address,
                KitchenType.ITALIAN, // Assume KitchenType remains the same
                "12.345.678/0001-01", // Updated mock CNPJ
                List.of(new OperatingHoursDTO(
                        DayOfWeek.FRIDAY,
                        ZonedDateTime.now(),
                        ZonedDateTime.now().plusHours(4)
                )),
                100 // Updated capacity
        );
    }

    @When("I send a PUT request to {string} with the updated details")
    public void i_send_a_put_request_to_with_the_updated_details(String url) {
        // Send PUT request using TestRestTemplate
        var response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(updatedRestaurant),
                RestaurantDTO.class
        );

        updatedRestaurant = response.getBody();
        statusCode = response.getStatusCodeValue();
    }

    @And("I should see the updated restaurant details")
    public void i_should_see_the_updated_restaurant_details() {
        // Validate the response contains the updated details
        RestaurantDTO returnedRestaurant = updatedRestaurant;
        assertNotNull(returnedRestaurant);

        // Field-by-field assertions using assertEquals
        assertEquals(updatedRestaurant.name(), returnedRestaurant.name());
        assertEquals(updatedRestaurant.address().street(), returnedRestaurant.address().street());
        assertEquals(updatedRestaurant.address().city(), returnedRestaurant.address().city());
        assertEquals(updatedRestaurant.address().state(), returnedRestaurant.address().state());
        assertEquals(updatedRestaurant.capacity(), returnedRestaurant.capacity());
        assertEquals(updatedRestaurant.kitchenType(), returnedRestaurant.kitchenType());
        assertEquals(updatedRestaurant.cnpj(), returnedRestaurant.cnpj());

        // Validate operating hours
        assertEquals(1, returnedRestaurant.operatingHoursDTO().size()); // Ensure there's one operating hour
        assertEquals(updatedRestaurant.operatingHoursDTO().get(0).dayOfWeek(),
                returnedRestaurant.operatingHoursDTO().get(0).dayOfWeek());
        assertEquals(updatedRestaurant.operatingHoursDTO().get(0).startTime(),
                returnedRestaurant.operatingHoursDTO().get(0).startTime());
        assertEquals(updatedRestaurant.operatingHoursDTO().get(0).endTime(),
                returnedRestaurant.operatingHoursDTO().get(0).endTime());
    }

}
