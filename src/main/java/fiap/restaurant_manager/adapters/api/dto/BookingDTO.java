package fiap.restaurant_manager.adapters.api.dto;

import fiap.restaurant_manager.domain.enums.StatusBooking;

import java.time.LocalDateTime;

public record BookingDTO(Long restauranteId,
                         Long usuarioId,
                         LocalDateTime dataHora,
                         Integer quantidadePessoas,
                         StatusBooking status) {}
