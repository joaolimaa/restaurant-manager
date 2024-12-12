package fiap.restaurant_manager.application.gateways;

import fiap.restaurant_manager.adapters.persistence.entities.OperatingHoursEntity;
import fiap.restaurant_manager.adapters.persistence.repository.OperatingHoursRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class OperatingHoursGateway {
    private final OperatingHoursRepository operatingHoursRepository;

    public Collection<OperatingHoursEntity> findAll() {
        return operatingHoursRepository.findAll();
    }

    public Optional<OperatingHoursEntity> findById(Long id){
        return operatingHoursRepository.findById(id);
    }

    public void save(OperatingHoursEntity operatingHoursEntity) {
        operatingHoursRepository.save(operatingHoursEntity);
    }

    public void deleteById(Long id) {
        operatingHoursRepository.deleteById(id);
    }
}
