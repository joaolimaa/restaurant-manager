package fiap.restaurant_manager.infra.gateways.reserva;


import fiap.restaurant_manager.domain.entities.reserva.Reserva;
import fiap.restaurant_manager.infra.persistence.reserva.ReservaEntity;

public class ReservaEntityMapper {

    public ReservaEntity toEntity(Reserva reservaDomain) {
        return new ReservaEntity(
                reservaDomain.getRestauranteId(),
                reservaDomain.getUsuarioId(),
                reservaDomain.getDataHora(),
                reservaDomain.getQuantidadePessoas(),
                reservaDomain.getStatus());
    }

    public Reserva toDomain(ReservaEntity reserva) {
        return new Reserva(reserva.getRestauranteId(),
                reserva.getUsuarioId(),
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus());
    }
}
