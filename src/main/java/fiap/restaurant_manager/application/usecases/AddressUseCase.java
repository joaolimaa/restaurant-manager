package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.application.gateways.AddressGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressUseCase {
    private final AddressGateway addressGateway;

    public AddressEntity findAddressById(Long id) {
        return addressGateway.findById(id);
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
}
