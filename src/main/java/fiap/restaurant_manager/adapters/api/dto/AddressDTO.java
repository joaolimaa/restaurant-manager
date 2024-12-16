package fiap.restaurant_manager.adapters.api.dto;

public record AddressDTO(String postalCode,
                         String city,
                         String state,
                         String neighborhood,
                         String street,
                         String number) {}
