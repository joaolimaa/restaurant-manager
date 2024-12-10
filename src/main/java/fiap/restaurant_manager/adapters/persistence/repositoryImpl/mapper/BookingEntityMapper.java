package fiap.restaurant_manager.adapters.persistence.repositoryImpl.mapper;


import fiap.restaurant_manager.domain.entities.Booking;
import fiap.restaurant_manager.adapters.persistence.entities.BookingEntity;

public class BookingEntityMapper {

    public BookingEntity toEntity(Booking bookingDomain) {
        return new BookingEntity(
                bookingDomain.getRestauranteId(),
                bookingDomain.getUsuarioId(),
                bookingDomain.getDataHora(),
                bookingDomain.getQuantidadePessoas(),
                bookingDomain.getStatus());
    }

    public Booking toDomain(BookingEntity reserva) {
        return new Booking(reserva.getRestauranteId(),
                reserva.getUsuarioId(),
                reserva.getDataHora(),
                reserva.getQuantidadePessoas(),
                reserva.getStatus());
    }
}
