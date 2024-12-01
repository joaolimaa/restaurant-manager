package fiap.restaurant_manager.application.usecases.reserva;

import fiap.restaurant_manager.application.gateway.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public class ListarTodasReservasUseCase {

    private final ReservaGateway reservaGateway;

    public Collection<Reserva> listaTodasAsReservas() {
        return reservaGateway.listarTodos();
    }
}
