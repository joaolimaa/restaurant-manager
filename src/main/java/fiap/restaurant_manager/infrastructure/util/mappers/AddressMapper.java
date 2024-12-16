package fiap.restaurant_manager.infrastructure.util.mappers;

import fiap.restaurant_manager.adapters.api.dto.AddressDTO;
import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.domain.entities.Address;

public class AddressMapper {

    public Address toAddressDomain(AddressDTO address) {
        return new Address(
                address.postalCode(),
                address.city(),
                address.state(),
                address.neighborhood(),
                address.street(),
                address.number());
    }

    public Address toAddressDomain(AddressEntity address) {
        return new Address(
                address.getPostalCode(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber());
    }

    public AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(
                address.getPostalCode(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber());
    }

    public AddressDTO toAddressDTO(AddressEntity address) {
        return new AddressDTO(
                address.getPostalCode(),
                address.getCity(),
                address.getState(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber());
    }
}
