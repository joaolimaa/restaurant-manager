package fiap.restaurant_manager.domain.entities.reserva;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fiap.restaurant_manager.domain.enums.StatusReserva;

class ReservaTest {

    @Test
    void deveCriarReservaComDadosValidos() {
        assertDoesNotThrow(() -> new Reserva(
                1L,
                1L,
                LocalDateTime.now().plusDays(1),
                4,
                StatusReserva.CONFIRMADO
        ));
    }

    @Nested
    class ValidaRestauranteId {

        @Test
        void deveLancarExcecaoSeRestauranteIdForNulo() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    null,
                    1L,
                    LocalDateTime.now().plusDays(1),
                    4,
                    StatusReserva.CONFIRMADO
            ));
        }

        @Test
        void deveLancarExcecaoSeRestauranteIdForNegativo() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    -1L,
                    1L,
                    LocalDateTime.now().plusDays(1),
                    4,
                    StatusReserva.CONFIRMADO
            ));
        }
    }

    @Nested
    class ValidaUsuarioId {

        @Test
        void deveLancarExcecaoSeUsuarioIdForNulo() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    null,
                    LocalDateTime.now().plusDays(1),
                    4,
                    StatusReserva.CONFIRMADO
            ));
        }

        @Test
        void deveLancarExcecaoSeUsuarioIdForNegativo() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    -1L,
                    LocalDateTime.now().plusDays(1),
                    4,
                    StatusReserva.CONFIRMADO
            ));
        }
    }

    @Nested
    class ValidaDataHora {

        @Test
        void deveLancarExcecaoSeDataHoraForNula() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    1L,
                    null,
                    4,
                    StatusReserva.CONFIRMADO
            ));
        }

        @Test
        void deveLancarExcecaoSeDataHoraForNoPassado() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    1L,
                    LocalDateTime.now().minusDays(1),
                    4,
                    StatusReserva.CONFIRMADO
            ));
        }
    }

    @Nested
    class ValidaQuantidadePessoas {

        @Test
        void deveLancarExcecaoSeQuantidadeForNegativa() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    1L,
                    LocalDateTime.now().plusDays(1),
                    -1,
                    StatusReserva.CONFIRMADO
            ));
        }
    }

    @Nested
    class ValidaStatus {

        @Test
        void deveLancarExcecaoSeStatusForNulo() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    1L,
                    LocalDateTime.now().plusDays(1),
                    4,
                    null
            ));
        }

        @Test
        void deveLancarExcecaoSeStatusForInvalido() {
            assertThrows(IllegalArgumentException.class, () -> new Reserva(
                    1L,
                    1L,
                    LocalDateTime.now().plusDays(1),
                    4,
                    StatusReserva.valueOf("INEXISTENTE")
            ));
        }
    }
}