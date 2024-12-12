//package fiap.restaurant_manager.domain.entities;
//
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import java.time.LocalDateTime;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import fiap.restaurant_manager.domain.enums.StatusBooking;
//
//class BookingTest {
//
//    @Test
//    void deveCriarReservaComDadosValidos() {
//        assertDoesNotThrow(() -> new Booking(
//                1L,
//                1L,
//                LocalDateTime.now().plusDays(1),
//                4,
//                StatusBooking.CONFIRMADA
//        ));
//    }
//
//    @Nested
//    class ValidaRestauranteId {
//
//        @Test
//        void deveLancarExcecaoSeRestauranteIdForNulo() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    null,
//                    1L,
//                    LocalDateTime.now().plusDays(1),
//                    4,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//
//        @Test
//        void deveLancarExcecaoSeRestauranteIdForNegativo() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    -1L,
//                    1L,
//                    LocalDateTime.now().plusDays(1),
//                    4,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//    }
//
//    @Nested
//    class ValidaUsuarioId {
//
//        @Test
//        void deveLancarExcecaoSeUsuarioIdForNulo() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    null,
//                    LocalDateTime.now().plusDays(1),
//                    4,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//
//        @Test
//        void deveLancarExcecaoSeUsuarioIdForNegativo() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    -1L,
//                    LocalDateTime.now().plusDays(1),
//                    4,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//    }
//
//    @Nested
//    class ValidaDataHora {
//
//        @Test
//        void deveLancarExcecaoSeDataHoraForNula() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    1L,
//                    null,
//                    4,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//
//        @Test
//        void deveLancarExcecaoSeDataHoraForNoPassado() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    1L,
//                    LocalDateTime.now().minusDays(1),
//                    4,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//    }
//
//    @Nested
//    class ValidaQuantidadePessoas {
//
//        @Test
//        void deveLancarExcecaoSeQuantidadeForNegativa() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    1L,
//                    LocalDateTime.now().plusDays(1),
//                    -1,
//                    StatusBooking.CONFIRMADA
//            ));
//        }
//    }
//
//    @Nested
//    class ValidaStatus {
//
//        @Test
//        void deveLancarExcecaoSeStatusForNulo() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    1L,
//                    LocalDateTime.now().plusDays(1),
//                    4,
//                    null
//            ));
//        }
//
//        @Test
//        void deveLancarExcecaoSeStatusForInvalido() {
//            assertThrows(IllegalArgumentException.class, () -> new Booking(
//                    1L,
//                    1L,
//                    LocalDateTime.now().plusDays(1),
//                    4,
//                    StatusBooking.valueOf("INEXISTENTE")
//            ));
//        }
//    }
//}