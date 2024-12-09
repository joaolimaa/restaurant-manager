package fiap.restaurant_manager.adapters.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.restaurant_manager.application.usecase.RestaurantUseCase;
import fiap.restaurant_manager.domain.entity.restaurant.RestaurantEntity;
import fiap.restaurant_manager.domain.valueObject.Address;
import fiap.restaurant_manager.domain.valueObject.OperatingHours;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestaurantUseCase restaurantUseCase;

    @InjectMocks
    private RestaurantController restaurantController;

    private RestaurantEntity restaurantEntityMock;

    private String restaurantJSON;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);
        buildMockRestaurantEntity();
        restaurantJSON = formatRestaurantEntityToJSON(restaurantEntityMock);
    }

    /**
     * Os métodos abaixo representam os resultados possíveis de: FIND ALL RESTAURANT
     */

    @Test
    public void testFindAllRestaurants_Success() throws Exception {
        // Arrange: Simula a listagem de todos os restaurantes
        when(restaurantUseCase.findAllRestaurants()).thenReturn(Collections.singletonList(restaurantEntityMock));

        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())  // status da resposta: 200
                .andExpect(jsonPath("$[0].name").value("Restaurante FIAP"))
                .andExpect(jsonPath("$[0].kitchenType").value("Sanduicheria"));
    }

    @Test
    public void testFindAllRestaurants_InternalServerError() throws Exception {
        // Arrange: Simula que ocorre uma exceção durante a execução
        when(restaurantUseCase.findAllRestaurants()).thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isInternalServerError())  // status da resposta: 500
                .andExpect(content().string("Erro interno do servidor"));
    }


    /**
     * Os métodos abaixo representam os resultados possíveis de: FIND RESTAURANT BY ID
     */

    @Test
    public void testGetRestaurantById_Success() throws Exception {
        // Arrange: Simula o retorno de um restaurante com o id 1
        when(restaurantUseCase.findRestaurantById(1L)).thenReturn(restaurantEntityMock);

        mockMvc.perform(get("/restaurants/{id}", 1L))
                .andExpect(status().isOk())  // status da resposta: 200
                .andExpect(jsonPath("$.name").value("Restaurante FIAP"))
                .andExpect(jsonPath("$.kitchenType").value("Sanduicheria"));
    }

    @Test
    public void testGetRestaurantById_NotFound() throws Exception {
        // Arrange: Simula que o restaurante não é encontrado
        when(restaurantUseCase.findRestaurantById(1L)).thenReturn(null);

        mockMvc.perform(get("/restaurants/{id}", 1L))
                .andExpect(status().isNotFound())  // status da resposta: 404
                .andExpect(content().string("Restaurante não encontrado"));
    }

    @Test
    public void testGetRestaurantById_InternalServerError() throws Exception {
        // Arrange: Simula que ocorre uma exceção durante a execução
        when(restaurantUseCase.findRestaurantById(1L)).thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(get("/restaurants/{id}", 1L))
                .andExpect(status().isInternalServerError())  // status da resposta: 500
                .andExpect(content().string("Erro interno do servidor"));
    }


    /**
     * Os métodos abaixo representam os resultados possíveis de: CREATE RESTAURANT
     */

    @Test
    public void testCreateRestaurant_Success() throws Exception {
        // Arrange: Simula os dados de um restaurante válido e o retorno do use case
        when(restaurantUseCase.createRestaurant(any(RestaurantEntity.class))).thenReturn(restaurantEntityMock);

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restaurantJSON))
                .andExpect(status().isCreated())  // status da resposta: 201
                .andExpect(jsonPath("$.name").value("Restaurante FIAP"))
                .andExpect(jsonPath("$.kitchenType").value("Sanduicheria"));
    }

    @Test
    public void testCreateRestaurant_BadRequest() throws Exception {
        // Arrange: Simula dados inválidos
        val invalidJSON = formatRestaurantEntityToJSON(new RestaurantEntity());

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJSON))
                .andExpect(status().isBadRequest())  // status da resposta: 400
                .andExpect(content().string("Input inválido"));
    }

    @Test
    public void testCreateRestaurant_InternalServerError() throws Exception {
        // Arrange: Simula uma exceção interna durante a criação do restaurante
        when(restaurantUseCase.createRestaurant(any(RestaurantEntity.class)))
                .thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restaurantJSON))
                .andExpect(status().isInternalServerError())  // status da resposta: 500
                .andExpect(content().string("Erro interno do servidor"));
    }


    /**
     * Os métodos abaixo representam os resultados possíveis de: UPDATE RESTAURANT
     */

    @Test
    public void testUpdateRestaurant_Success() throws Exception {
        // Arrange: Simula o restaurante com id 1 sendo atualizado
        when(restaurantUseCase.updateRestaurant(anyLong(), any(RestaurantEntity.class))).thenReturn(restaurantEntityMock);

        mockMvc.perform(put("/restaurants/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restaurantJSON))
                .andExpect(status().isOk())  // status da resposta: 200
                .andExpect(jsonPath("$.name").value("Restaurante FIAP"))
                .andExpect(jsonPath("$.kitchenType").value("Sanduicheria"));
    }

    @Test
    public void testUpdateRestaurant_BadRequest() throws Exception {
        // Arrange: Simula um restaurante inválido com dados faltando ou incorretos
        val invalidJSON = formatRestaurantEntityToJSON(new RestaurantEntity());

        mockMvc.perform(put("/restaurants/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJSON))  // Passando dados inválidos
                .andExpect(status().isBadRequest())  // status da resposta: 400
                .andExpect(content().string("Input inválido"));
    }

    @Test
    public void testUpdateRestaurant_NotFound() throws Exception {
        // Arrange: Simula que o restaurante com id 1 não existe
        when(restaurantUseCase.updateRestaurant(anyLong(), any(RestaurantEntity.class))).thenReturn(null);

        mockMvc.perform(put("/restaurants/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restaurantJSON))
                .andExpect(status().isNotFound())  // status da resposta: 404
                .andExpect(content().string("Restaurante não encontrado"));
    }

    @Test
    public void testUpdateRestaurant_InternalServerError() throws Exception {
        // Arrange: Simula que ocorre uma exceção interna durante a execução
        when(restaurantUseCase.updateRestaurant(anyLong(), any(RestaurantEntity.class)))
                .thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(put("/restaurants/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restaurantJSON))
                .andExpect(status().isInternalServerError())  // status da resposta: 500
                .andExpect(content().string("Erro interno do servidor"));
    }


    /**
     * Os métodos abaixo representam os resultados possíveis de: DELETE RESTAURANT
     */

    @Test
    public void testDeleteRestaurant_Success() throws Exception {
        // Arrange: Simula a deleção bem-sucedida de um restaurante
        when(restaurantUseCase.deleteRestaurant(1L)).thenReturn(true);

        mockMvc.perform(delete("/restaurants/{id}", 1L))
                .andExpect(status().isNoContent()); // status da resposta: 204
    }

    @Test
    public void testDeleteRestaurant_NotFound() throws Exception {
        // Arrange: Simula que o restaurante não existe
        when(restaurantUseCase.deleteRestaurant(1L)).thenReturn(false);

        mockMvc.perform(delete("/restaurants/{id}", 1L))
                .andExpect(status().isNotFound())  // status da resposta: 404
                .andExpect(content().string("Restaurante não encontrado"));
    }

    @Test
    public void testDeleteRestaurant_InternalServerError() throws Exception {
        // Arrange: Simula que ocorre uma exceção durante a deleção
        when(restaurantUseCase.deleteRestaurant(1L)).thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(delete("/restaurants/{id}", 1L))
                .andExpect(status().isInternalServerError())  // status da resposta: 500
                .andExpect(content().string("Erro interno do servidor"));
    }

    private void buildMockRestaurantEntity() {
        restaurantEntityMock = new RestaurantEntity();
        restaurantEntityMock.setId(1L);
        restaurantEntityMock.setName("Restaurante FIAP");
        restaurantEntityMock.setAddress(buildMockAddress());
        restaurantEntityMock.setKitchenType("Sanduicheria");
        restaurantEntityMock.setCnpj("");
        restaurantEntityMock.setOperatingHours(Collections.singletonList(buildMockOperatingHours()));
        restaurantEntityMock.setCapacity(100);
    }

    private String formatRestaurantEntityToJSON(RestaurantEntity objectEntity) throws JsonProcessingException {
        val objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(objectEntity);
    }

    private Address buildMockAddress() {
        val address = new Address();
        address.setPostalCode("01153-000");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setNeighborhood("Centro");
        address.setStreet("Rua São Paulo");
        address.setNumber("45");
        return address;
    }

    private OperatingHours buildMockOperatingHours() {
        val operatingHours = new OperatingHours();
        operatingHours.setDayOfWeek(DayOfWeek.MONDAY);
        operatingHours.setStartTime(ZonedDateTime.parse("2024-11-27T09:00:00+00:00"));
        operatingHours.setEndTime(ZonedDateTime.parse("2024-11-27T17:00:00+00:00"));
        return operatingHours;
    }
}
