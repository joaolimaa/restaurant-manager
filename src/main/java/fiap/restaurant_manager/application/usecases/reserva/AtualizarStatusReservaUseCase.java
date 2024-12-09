package fiap.restaurant_manager.application.usecases.reserva;

import fiap.restaurant_manager.application.gateways.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entities.reserva.Reserva;
import fiap.restaurant_manager.domain.enums.StatusReserva;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AtualizarStatusReservaUseCase {

    private final ReservaGateway reservaGateway;

    public Reserva atualizaStatusReserva(Long id, StatusReserva statusReserva) {
        return reservaGateway.atualizarStatusReserva(id, statusReserva);
    }
}
