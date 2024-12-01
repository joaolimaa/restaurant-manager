package fiap.restaurant_manager.infra.controllers.reserva.dto;

import fiap.restaurant_manager.domain.enums.StatusReserva;

import java.time.LocalDateTime;

public record ReservaResponse(Long restauranteId,
                              Long usuarioId,
                              LocalDateTime dataHora,
                              Integer quantidadePessoas,
                              StatusReserva status) {
}
