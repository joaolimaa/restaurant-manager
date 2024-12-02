package fiap.restaurant_manager.infra.controllers.reserva;

import fiap.restaurant_manager.application.usecases.reserva.AtualizarReservaUseCase;
import fiap.restaurant_manager.application.usecases.reserva.ListarTodasReservasUseCase;
import fiap.restaurant_manager.domain.entities.reserva.Reserva;
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

    @GetMapping
    public Collection<ReservaDTO> listarTodasAsReservas() {
        return listarTodasReservasUseCase.listaTodasAsReservas().stream().map(mapper::toDTO).toList();
    }

    @PutMapping("/{id}")
    public ReservaDTO update(@PathVariable String id, @Valid @RequestBody ReservaDTO request) {
        Reserva reserva = mapper.toReserva(request);
        return mapper.toDTO(atualizarReservaUseCase.atualizaReserva(reserva));
    }

}
