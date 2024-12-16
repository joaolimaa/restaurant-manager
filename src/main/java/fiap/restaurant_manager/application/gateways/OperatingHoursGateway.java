package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;
import fiap.restaurant_manager.adapters.persistence.repository.OperatingHoursRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OperatingHoursGateway {

    private final OperatingHoursRepository operatingHoursRepository;


    public OperatingHoursEntity findById(Long id){
        return operatingHoursRepository.findById(id).orElseThrow(() -> null);
    }

    public OperatingHoursEntity save(OperatingHoursEntity operatingHoursEntity) {
        return operatingHoursRepository.save(operatingHoursEntity);
    }

    public List<OperatingHoursEntity> saveAll(List<OperatingHoursEntity> operatingHoursList) {
        return operatingHoursRepository.saveAll(operatingHoursList);
    }

    public void deleteById(Long id) {
        operatingHoursRepository.deleteById(id);
    }
}
