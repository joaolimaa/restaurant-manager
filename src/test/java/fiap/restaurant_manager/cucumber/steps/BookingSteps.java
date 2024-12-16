package fiap.restaurant_manager.cucumber.steps;

import static fiap.restaurant_manager.cucumber.helper.StepsHelper.*;
import static fiap.restaurant_manager.cucumber.helper.StepsHelper.getRestaurantDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.restaurant_manager.adapters.api.dto.*;
import fiap.restaurant_manager.domain.enums.KitchenType;
import fiap.restaurant_manager.domain.enums.StatusBooking;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private ResponseEntity<BookingDTO> bookingDTOResponseEntity;
    private Map<String, Object> bookingPayload;

    private ResponseEntity<UserDTO> userDTOResponseEntity;
    private ResponseEntity<RestaurantDTO> restaurantDTOResponseEntity;

    @Given("the following user name: {string}, email: {string}, cpf: {string} and the restaurant")
    public void aValidBookingPayload(
        final String name,
        final String email,
        final String cpf,
        final io.cucumber.datatable.DataTable dataTable
    ){
        userDTOResponseEntity = restTemplate.postForEntity (
        "/api/users",
            new UserDTO (name, email, cpf),
            UserDTO.class
        );
        assertNotNull(userDTOResponseEntity.getBody());
        assertNotNull(userDTOResponseEntity);

        final List<List<String>> rows = dataTable.asLists(String.class);
        final List<String> details = rows.get(1);
        final AddressDTO address = buildAddress(details);
        final KitchenType kitchenType = parseKitchenType(details.get(7));
        final OperatingHoursDTO operatingHour = buildOperatingHours(details.get(10));

        final RestaurantDTO restaurantDTO = getRestaurantDto(details, address, kitchenType, operatingHour);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<RestaurantDTO> requestEntity = new HttpEntity<>(restaurantDTO, headers);

        restaurantDTOResponseEntity = restTemplate.exchange (
                "/api/restaurants",
                HttpMethod.POST,
                requestEntity,
                RestaurantDTO.class
        );

        assertNotNull(restaurantDTOResponseEntity.getBody());
        assertNotNull(restaurantDTOResponseEntity);

        System.out.println(userDTOResponseEntity);
        System.out.println(restaurantDTOResponseEntity);
    }

    @When("I send a POST request to {string} with the payload")
    public void iSendAPOSTRequestToWithThePayload(
        final String endpoint,
        final io.cucumber.datatable.DataTable dataTable
    ) {
        final Map<String, String> data = dataTable.asMap(String.class, String.class);
        bookingPayload = new HashMap<>();
        bookingPayload.put("restaurantId", Long.parseLong(data.get("restaurantId")));
        bookingPayload.put("userId", Long.parseLong(data.get("userId")));
        bookingPayload.put("bookingDate", LocalDateTime.parse(data.get("bookingDate")));
        bookingPayload.put("peopleQuantity", Integer.parseInt(data.get("peopleQuantity")));
        bookingPayload.put("status", StatusBooking.valueOf(data.get("status")));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(bookingPayload);
        bookingDTOResponseEntity = restTemplate.postForEntity(endpoint, request, BookingDTO.class);
        System.out.println(bookingDTOResponseEntity);
    }

    @Then("regarding to booking the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assertThat(bookingDTOResponseEntity.getStatusCode().value()).isEqualTo(expectedStatus);
    }

    @Then("the response body should contain the created booking details")
    public void theResponseBodyShouldContainTheCreatedBookingDetails() {
        assertNotNull(bookingDTOResponseEntity);
        assertNotNull(bookingDTOResponseEntity.getBody());

        BookingDTO createdBooking = bookingDTOResponseEntity.getBody();
        assertThat(createdBooking.restaurantId()).isEqualTo(bookingPayload.get("restaurantId"));
        assertThat(createdBooking.userId()).isEqualTo(bookingPayload.get("userId"));
        assertThat(createdBooking.bookingDate()).isEqualTo(bookingPayload.get("bookingDate"));
        assertThat(createdBooking.peopleQuantity()).isEqualTo(bookingPayload.get("peopleQuantity"));
        assertThat(createdBooking.status()).isEqualTo(bookingPayload.get("status"));
    }

    @Given("a booking with ID {int} exists")
    public void aBookingWithIDExists(int bookingId) {
        assertNotNull(userDTOResponseEntity);
        assertNotNull(restaurantDTOResponseEntity);
        assertNotNull(bookingDTOResponseEntity);
        assertNotNull(bookingDTOResponseEntity.getBody());
        //assertEquals(bookingDTOResponseEntity.getBody().id() + "", bookingId + "");
    }

    @Then("the response body should contain the booking details")
    public void theResponseBodyShouldContainTheBookingDetails() {
        BookingDTO booking = bookingDTOResponseEntity.getBody();
        assertThat(booking).isNotNull();
        assertThat(booking.id()).isNotNull();
        assertThat(booking.restaurantId()).isEqualTo(1L);
        assertThat(booking.userId()).isEqualTo(1L);
        assertThat(booking.peopleQuantity()).isEqualTo(4);
        assertThat(booking.status()).isEqualTo(StatusBooking.PENDING);
    }

    @When("regarding to booking I send a GET request to {string}")
    public void iSendAPOSTRequestToWithThePayload(final String endpoint)
    {
        bookingDTOResponseEntity = restTemplate.getForEntity(endpoint, BookingDTO.class);
        System.out.println(bookingDTOResponseEntity);
    }

    @Then("regarding to booking I send a DELETE request to {string}")
    public void delete(final String endPoint) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        try{
            var delete = restTemplate.exchange(endPoint, HttpMethod.DELETE, entity, Void.class);

            bookingDTOResponseEntity = new ResponseEntity<>(null, null, delete.getStatusCode());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(bookingDTOResponseEntity.getBody());
    }

    @And("the booking with ID {int} should no longer exist")
    public void noLongerExists(final int id)
    {
        var getResponse = restTemplate.getForEntity("/v1/api/reserva/" + id, UserDTO.class);

        assertNotNull(getResponse);
        System.out.println(getResponse.getBody());
    }
}
