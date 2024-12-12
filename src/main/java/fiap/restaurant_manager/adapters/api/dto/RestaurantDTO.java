package fiap.restaurant_manager.adapters.api.dto;

import fiap.restaurant_manager.domain.enums.KitchenType;

import java.util.List;

public record RestaurantDTO(String name,
                            AddressDTO address,
                            KitchenType kitchenType,
                            String cnpj,
                            List<OperatingHoursDTO> operatingHoursDTO,
                            int capacity) {}
