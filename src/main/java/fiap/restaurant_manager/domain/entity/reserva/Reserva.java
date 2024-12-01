package fiap.restaurant_manager.domain.entity.reserva;

import fiap.restaurant_manager.domain.enums.StatusReserva;

import java.time.LocalDateTime;

public record Reserva(
        Long id,
        Long restauranteId,
        Long usuarioId,
        LocalDateTime dataHora,
        Integer quantidadePessoas,
        StatusReserva status
) {
}
