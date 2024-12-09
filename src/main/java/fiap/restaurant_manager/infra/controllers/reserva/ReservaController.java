package fiap.restaurant_manager.infra.controllers.reserva;

import fiap.restaurant_manager.application.usecases.reserva.AtualizarReservaUseCase;
import fiap.restaurant_manager.application.usecases.reserva.AtualizarStatusReservaUseCase;
import fiap.restaurant_manager.application.usecases.reserva.EncontrarReservaPeloIdUseCase;
import fiap.restaurant_manager.application.usecases.reserva.ListarTodasReservasUseCase;
import fiap.restaurant_manager.domain.entities.reserva.Reserva;
import fiap.restaurant_manager.domain.enums.StatusReserva;
import fiap.restaurant_manager.infra.controllers.reserva.ReservaDTO;
import fiap.restaurant_manager.infra.controllers.reserva.dto.ReservaDTO;
import fiap.restaurant_manager.infra.controllers.reserva.mapper.ReservaControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/v1/api/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final AtualizarReservaUseCase atualizarReservaUseCase;
    private final ListarTodasReservasUseCase listarTodasReservasUseCase;
    private final ReservaControllerMapper mapper;
    private final AtualizarStatusReservaUseCase atualizarStatusReservaUseCase;
    private final EncontrarReservaPeloIdUseCase encontrarReservaPeloIdUseCase;

    @GetMapping
    public Collection<ReservaDTO> listarTodasAsReservas() {
        return listarTodasReservasUseCase.listaTodasAsReservas().stream().map(mapper::toDTO).toList();
    }

    @PutMapping("/{id}")
    public ReservaDTO update(@PathVariable String id, @Valid @RequestBody ReservaDTO request) {
        Reserva reserva = mapper.toReserva(request);
        return mapper.toDTO(atualizarReservaUseCase.atualizaReserva(reserva));
    }

    @PutMapping("/{id}/statusReserva")
    public ReservaDTO atualizarStatusReserva(@PathVariable Long id, @Valid @RequestBody StatusReserva statusReserva) {
        return mapper.toDTO(atualizarStatusReservaUseCase.atualizaStatusReserva(id, statusReserva));
    }

    @GetMapping("/{id}")
    public ReservaDTO encontrarReservaPeloId(@PathVariable Long id){
        return mapper.toDTO(encontrarReservaPeloIdUseCase.findById(id));
    }
}
