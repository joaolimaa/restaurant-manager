package fiap.restaurant_manager.application.usecases;

import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;
import fiap.restaurant_manager.application.gateways.OperatingHoursGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperatingHoursUseCase {
    private final OperatingHoursGateway operatingHoursGateway;

    public OperatingHoursEntity findOperatingHoursById(Long id) {
        return operatingHoursGateway.findById(id);
    }

    public void createOperatingHours(OperatingHoursEntity operatingHoursEntity) {
        operatingHoursGateway.save(operatingHoursEntity);
    }

    public void updateOperatingHours(Long id, OperatingHoursEntity operatingHoursEntity) {
        findOperatingHoursById(id);
        operatingHoursGateway.save(operatingHoursEntity);
    }

    public void deleteOperatingHours(Long id) {
        operatingHoursGateway.deleteById(id);
    }
}
