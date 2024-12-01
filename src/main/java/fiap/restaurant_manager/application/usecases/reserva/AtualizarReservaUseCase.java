package fiap.restaurant_manager.application.usecases.reserva;

import fiap.restaurant_manager.application.gateway.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AtualizarReservaUseCase {

    private final ReservaGateway reservaGateway;

    public Reserva atualizaReserva(Reserva reserva) {
        return reservaGateway.atualizaReserva(reserva);
    }
}
