package fiap.restaurant_manager.application.usecases.reserva;

import fiap.restaurant_manager.application.gateway.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EncontrarReservaPeloIdUseCase {

    private final ReservaGateway reservaGateway;

    public Reserva findById(Long id) {
        return reservaGateway.findById(id);
    }
}
