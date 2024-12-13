package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.adapters.persistence.repository.AddressRepository;
import fiap.restaurant_manager.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class AddressGateway {
    private final AddressRepository addressRepository;

    public AddressEntity findById(Long id){
        return addressRepository.findById(id).orElseThrow(() -> null);
    }

    public AddressEntity save(AddressEntity addressEntity) {
       return addressRepository.save(addressEntity);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
