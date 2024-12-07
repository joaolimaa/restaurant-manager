package fiap.restaurant_manager.infra.controllers.reserva;

import fiap.restaurant_manager.application.usecases.reserva.AtualizarReservaUseCase;
import fiap.restaurant_manager.application.usecases.reserva.AtualizarStatusReservaUseCase;
import fiap.restaurant_manager.application.usecases.reserva.EncontrarReservaPeloIdUseCase;
import fiap.restaurant_manager.application.usecases.reserva.ListarTodasReservasUseCase;
import fiap.restaurant_manager.domain.entity.reserva.Reserva;
import fiap.restaurant_manager.domain.enums.StatusReserva;
import fiap.restaurant_manager.infra.controllers.reserva.dto.ReservaRequest;
import fiap.restaurant_manager.infra.controllers.reserva.dto.ReservaResponse;
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
    public Collection<ReservaResponse> listarTodasAsReservas() {
        return listarTodasReservasUseCase.listaTodasAsReservas().stream().map(mapper::toResponse).toList();
    }

    @PutMapping("/{id}")
    public ReservaResponse update(@PathVariable String id, @Valid @RequestBody ReservaRequest request) {
        Reserva reserva = mapper.toReserva(request);
        return mapper.toResponse(atualizarReservaUseCase.atualizaReserva(reserva));
    }

    @PutMapping("/{id}/statusReserva")
    public ReservaResponse atualizarStatusReserva(@PathVariable Long id, @Valid @RequestBody StatusReserva statusReserva) {
        return mapper.toResponse(atualizarStatusReservaUseCase.atualizaStatusReserva(id, statusReserva));
    }

    @GetMapping("/{id}")
    public ReservaResponse encontrarReservaPeloId(@PathVariable Long id){
        return mapper.toResponse(encontrarReservaPeloIdUseCase.findById(id));
    }
}
