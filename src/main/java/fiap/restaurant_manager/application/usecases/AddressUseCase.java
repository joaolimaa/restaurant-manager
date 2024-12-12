package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.application.gateways.AddressGateway;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class AddressUseCase {
    private final AddressGateway addressGateway;

    public Collection<AddressEntity> findAllBooking() {
        return addressGateway.findAll();
    }

    public void createAddress(AddressEntity addressEntity) {
        addressGateway.save(addressEntity);
    }

    public void updateAddress(Long id, AddressEntity addressEntity) {
        findAddressById(id);
        addressGateway.save(addressEntity);
    }

    public void deleteAddress(Long id) {
        addressGateway.deleteById(id);
    }

    public Optional<AddressEntity> findAddressById(Long id) {
        return addressGateway.findById(id);
    }
}
