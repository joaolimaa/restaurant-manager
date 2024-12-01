package fiap.restaurant_manager.infra.gateways.mappers;


import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import fiap.restaurant_manager.infra.database.ReservaEntity;

public class ReservaMapper {

    public ReservaEntity toEntity(Reserva reservaDomain) {
        return new ReservaEntity(
                reservaDomain.id(),
                reservaDomain.restauranteId(),
                reservaDomain.usuarioId(),
                reservaDomain.dataHora(),
                reservaDomain.quantidadePessoas(),
                reservaDomain.status());
    }

    public Reserva toDomainObj(ReservaEntity reserva) {
        return new Reserva(reserva.getId(),
                reserva.getRestauranteId(),
                reserva.getUsuarioId(),
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus());
    }
}
