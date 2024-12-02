package fiap.restaurant_manager.infra.gateways.reserva;

import fiap.restaurant_manager.application.gateways.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entities.reserva.Reserva;
import fiap.restaurant_manager.infra.persistence.reserva.ReservaEntity;
import fiap.restaurant_manager.infra.persistence.reserva.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
public class ReservaRepositoryGateway implements ReservaGateway {

    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper mapper;

    @Override
    public Collection<Reserva> listarTodos() {
        return reservaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Transactional
    @Override
    public Reserva atualizaReserva(Reserva reserva) {
        ReservaEntity reservaEntity = mapper.toEntity(reserva);
        return mapper.toDomain(reservaRepository.save(reservaEntity));
    }


}
