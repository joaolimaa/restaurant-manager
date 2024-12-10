package fiap.restaurant_manager.adapters.api.dto;

import fiap.restaurant_manager.adapters.persistence.entities.AddressEntity;
import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;

import java.util.List;

public record RestaurantDTO(String name,
                            AddressEntity addressEntity,
                            String kitchenType,
                            String cnpj,
                            List<OperatingHoursEntity> operatingHourEntities,
                            int capacity) {}
