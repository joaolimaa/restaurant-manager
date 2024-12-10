package fiap.restaurant_manager.domain.entities;

import fiap.restaurant_manager.domain.enums.StatusBooking;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private Long restauranteId;
    private Long usuarioId;
    private LocalDateTime dataHora;
    private Integer quantidadePessoas;
    private StatusBooking status;

    public Booking(Long restauranteId, Long usuarioId, LocalDateTime dataHora, Integer quantidadePessoas, StatusBooking status) {

        realizaValidacoes(restauranteId, usuarioId, dataHora, quantidadePessoas, status);

        this.restauranteId = restauranteId;
        this.usuarioId = usuarioId;
        this.dataHora = dataHora;
        this.quantidadePessoas = quantidadePessoas;
        this.status = status;
    }

    private void realizaValidacoes(Long restauranteId, Long usuarioId, LocalDateTime dataHora, Integer quantidadePessoas, StatusBooking status) {
        //Valida se restaurante esta vazio
        validaRestauranteIdReserva(restauranteId);

        //Valida se usuario esta vazio
        validaUsuarioIdReserva(usuarioId);

        //Valida se a hora é valida
        validaDataHoraReserva(dataHora);

        //Realiza a validação da quantidade de pessoas
        validaQuantidadePessoasReserva(quantidadePessoas);

        //Valida se o status é válido
        validaStatusReserva(String.valueOf(status));
    }

    private void validaRestauranteIdReserva(Long restauranteId) {
        if (restauranteId == null || restauranteId <= 0) {
            throw new IllegalArgumentException("O ID do restaurante deve ser um número válido e maior que zero.");
        }
    }

    private void validaUsuarioIdReserva(Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new IllegalArgumentException("O ID do usuário deve ser um número válido e maior que zero.");
        }
    }

    private void validaStatusReserva(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("O status não pode ser nulo.");
        }

        try {
            StatusBooking.valueOf(status);
        } catch (Exception e) {
            throw new IllegalArgumentException("Valor não é válido para o status.");
        }
    }

    private void validaQuantidadePessoasReserva(Integer quantidadePessoas) {
        if (quantidadePessoas < 0) {
            throw new IllegalArgumentException("A quantidade de pessoa tem que ser maior que 0, para realizar a reserva.");
        }
    }

    private void validaDataHoraReserva(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da reserva não podem ser nulas.");
        }

        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data e hora da reserva devem ser no futuro.");
        }
    }

}
