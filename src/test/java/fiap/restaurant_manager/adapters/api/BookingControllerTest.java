package fiap.restaurant_manager.adapters.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.restaurant_manager.adapters.api.controllers.BookingController;
import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.application.usecases.BookingUseCase;
import fiap.restaurant_manager.domain.enums.StatusBooking;
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

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookingUseCase bookingUseCase;

    @InjectMocks
    private BookingController bookingController;

    private BookingDTO bookingMock;

    private String bookingJSON;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);
        bookingMock = new BookingDTO(1L, 2L, 3L, LocalDateTime.now().plusDays(1), 4,StatusBooking.CONFIRMED );
        bookingJSON = formatBookingToJSON(bookingMock);
    }

    @Test
    public void testFindBookingById_Success() throws Exception {
        when(bookingUseCase.findBookingById(1L)).thenReturn(bookingMock);

        mockMvc.perform(get("/v1/api/reserva/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Booking"));
    }

    @Test
    public void testFindBookingById_NotFound() throws Exception {
        when(bookingUseCase.findBookingById(1L)).thenReturn(null);

        mockMvc.perform(get("/v1/api/reserva/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllBooking_Success() throws Exception {
        when(bookingUseCase.findAllBooking()).thenReturn(Collections.singletonList(bookingMock));

        mockMvc.perform(get("/v1/api/reserva"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Booking"));
    }

    @Test
    public void testCreateBooking_Success() throws Exception {
        doNothing().when(bookingUseCase).createBooking(any(BookingDTO.class));

        mockMvc.perform(post("/v1/api/reserva")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Booking"));
    }

    @Test
    public void testCreateBooking_BadRequest() throws Exception {
        val invalidJSON = "{}"; // JSON inválido

        mockMvc.perform(post("/v1/api/reserva")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateBooking_Success() throws Exception {
        when(bookingUseCase.updateBooking(anyLong(), any(BookingDTO.class))).thenReturn(bookingMock);

        mockMvc.perform(put("/v1/api/reserva/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Booking"));
    }

    @Test
    public void testUpdateStatus_Success() throws Exception {
        when(bookingUseCase.updateStatus(anyLong(), any())).thenReturn(bookingMock);

        mockMvc.perform(put("/v1/api/reserva/muda-status/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"CONFIRMED\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Booking"));
    }

    @Test
    public void testDeleteBooking_Success() throws Exception {
        doNothing().when(bookingUseCase).deleteBooking(1L);

        mockMvc.perform(delete("/v1/api/reserva/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    private String formatBookingToJSON(BookingDTO bookingDTO) throws JsonProcessingException {
        val objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bookingDTO);
    }
}
