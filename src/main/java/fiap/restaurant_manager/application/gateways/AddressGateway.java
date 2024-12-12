package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.adapters.persistence.repository.AddressRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class AddressGateway {
    private final AddressRepository addressRepository;

    public Collection<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    public Optional<AddressEntity> findById(Long id){
        return addressRepository.findById(id);
    }

    public void save(AddressEntity addressEntity) {
        addressRepository.save(addressEntity);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
