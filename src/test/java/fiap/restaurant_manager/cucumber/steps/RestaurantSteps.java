package fiap.restaurant_manager.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.restaurant_manager.adapters.api.dto.AddressDTO;
import fiap.restaurant_manager.adapters.api.dto.OperatingHoursDTO;
import fiap.restaurant_manager.adapters.api.dto.RestaurantDTO;
import fiap.restaurant_manager.domain.enums.KitchenType;
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

import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@AutoConfigureMockMvc
public class RestaurantSteps {

    private RestaurantDTO restaurantDTO;
    private int statusCode;
    private RestaurantDTO createdRestaurant;

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

    @Then("I should receive a {int} status code")
    public void i_should_receive_a_status_code(int expectedStatusCode) {
        assertThat(statusCode, equalTo(expectedStatusCode));
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
}
