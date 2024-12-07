package fiap.restaurant_manager.application.gateway.reserva;

import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import fiap.restaurant_manager.domain.enums.StatusReserva;

import java.util.Collection;

public interface ReservaGateway {

    Reserva atualizaReserva(Reserva reserva);

    Collection<Reserva> listarTodos();

    Reserva findById(Long id);

    Reserva atualizarStatusReserva(Long id, StatusReserva statusReserva);

}
