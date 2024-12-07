package fiap.restaurant_manager.infra.gateways;

import fiap.restaurant_manager.application.gateway.reserva.ReservaGateway;
import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import fiap.restaurant_manager.domain.enums.StatusReserva;
import fiap.restaurant_manager.domain.exception.ExpectationFailedException;
import fiap.restaurant_manager.domain.exception.NotFoundException;
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

    @Transactional
    @Override
    public Reserva findById(Long id){

        ReservaEntity reservaEntity = reservaRepository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar a zona com o ID: " + id + "."));

        return reservaMapper.toDomainObj(reservaEntity);
    }

    @Transactional
    @Override
    public Reserva atualizarStatusReserva(Long id, StatusReserva statusReserva){
        Reserva reserva = findById(id);
        StatusReserva statusReservaAtual = reserva.status();
        ReservaEntity reservaEntity = reservaMapper.toEntity(reserva);

        if(statusReservaAtual.equals(StatusReserva.CONFIRMADA) || statusReservaAtual.equals(StatusReserva.PENDENTE)){

            reservaEntity.setStatus(statusReserva);

            validaStatus(reservaEntity);

            return reservaMapper.toDomainObj(reservaRepository.save(reservaEntity));
        }
        throw new ExpectationFailedException("Essa reserva não pode ser atualizada, pois já está cancelada.");
    }

    private void validaStatus(ReservaEntity reserva) {
        try {
            StatusReserva.valueOf(String.valueOf(reserva.getStatus()));
        } catch (Exception e) {
            throw new ExpectationFailedException("Valor diferente de CONFIRMADA ou CANCELADA.");
        }
    }
}
