package fiap.restaurant_manager.infra.gateways;

import fiap.restaurant_manager.application.gateway.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import fiap.restaurant_manager.infra.database.ReservaEntity;
import fiap.restaurant_manager.infra.gateways.mappers.ReservaMapper;
import fiap.restaurant_manager.infra.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
public class ReservaRepositoryGateway implements ReservaGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    @Transactional
    @Override
    public Reserva atualizaReserva(Reserva reserva) {
        ReservaEntity reservaEntity = reservaMapper.toEntity(reserva);

        return reservaMapper.toDomainObj(reservaRepository.save(reservaEntity));
    }

    @Override
    public Collection<Reserva> listarTodos() {
        return reservaRepository.findAll().stream().map(reservaMapper::toDomainObj).toList();
    }
}
