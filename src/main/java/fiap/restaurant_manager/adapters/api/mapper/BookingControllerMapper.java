package fiap.restaurant_manager.adapters.api.mapper;


import fiap.restaurant_manager.adapters.api.dto.BookingDTO;
import fiap.restaurant_manager.domain.entities.Booking;

public class BookingControllerMapper {

    public Booking toReserva(BookingDTO reserva) {
        return new Booking(
                reserva.restauranteId(),
                reserva.usuarioId(),
                reserva.dataHora(),
                reserva.quantidadePessoas(),
                reserva.status());
    }

    public BookingDTO toDTO(Booking booking) {
        return new BookingDTO(
                booking.getRestauranteId(),
                booking.getUsuarioId(),
                booking.getDataHora(),
                booking.getQuantidadePessoas(),
                booking.getStatus()
        );
    }


}
