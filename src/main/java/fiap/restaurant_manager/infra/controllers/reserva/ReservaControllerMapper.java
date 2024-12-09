package fiap.restaurant_manager.infra.controllers.reserva;


import fiap.restaurant_manager.domain.entities.reserva.Reserva;

public class ReservaControllerMapper {

    public Reserva toReserva(ReservaDTO reserva) {
        return new Reserva(
                reserva.restauranteId(),
                reserva.usuarioId(),
                reserva.dataHora(),
                reserva.quantidadePessoas(),
                reserva.status());
    }

    public ReservaDTO toDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getRestauranteId(),
                reserva.getUsuarioId(),
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus()
        );
    }


}
