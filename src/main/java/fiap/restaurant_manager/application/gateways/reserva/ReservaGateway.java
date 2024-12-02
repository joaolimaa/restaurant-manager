package fiap.restaurant_manager.application.gateways.reserva;

import fiap.restaurant_manager.domain.entities.reserva.Reserva;

import java.util.Collection;

public interface ReservaGateway {

    Collection<Reserva> listarTodos();

    Reserva atualizaReserva(Reserva reserva);
}



