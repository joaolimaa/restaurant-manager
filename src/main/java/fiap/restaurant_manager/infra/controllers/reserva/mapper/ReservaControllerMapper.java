package fiap.restaurant_manager.infra.controllers.reserva.mapper;


import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import fiap.restaurant_manager.infra.controllers.reserva.dto.ReservaRequest;
import fiap.restaurant_manager.infra.controllers.reserva.dto.ReservaResponse;

public class ReservaControllerMapper {

    public ReservaResponse toResponse(Reserva reservaDomain) {
        return new ReservaResponse(
                reservaDomain.restauranteId(),
                reservaDomain.usuarioId(),
                reservaDomain.dataHora(),
                reservaDomain.quantidadePessoas(),
                reservaDomain.status());
    }

    public Reserva toReserva(ReservaRequest reserva) {
        return new Reserva(
                0L,
                reserva.restauranteId(),
                reserva.usuarioId(),
                reserva.dataHora(),
                reserva.quantidadePessoas(),
                reserva.status());
    }


}
