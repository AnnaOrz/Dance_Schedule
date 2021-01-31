package annas.dance_schedule.services;

import annas.dance_schedule.model.Carnet;
import annas.dance_schedule.model.CarnetType;
import annas.dance_schedule.repository.CarnetRepository;
import annas.dance_schedule.repository.CarnetTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CarnetService {
    private final CarnetRepository carnetRepository;
    private final CarnetTypeRepository carnetTypeRepository;

    public CarnetService(CarnetRepository carnetRepository, CarnetTypeRepository carnetTypeRepository) {
        this.carnetRepository = carnetRepository;
        this.carnetTypeRepository = carnetTypeRepository;
    }
    @Transactional
    public void update(Carnet carnet) {
            Optional<Carnet> oldCarnet = carnetRepository.findById(carnet.getId());
            if(oldCarnet.isPresent()){
                carnetRepository.update(
                        carnet.getAccessNumber(),
                        carnet.getEntrances(),
                        carnet.getExpireDate(),
                        carnet.getPrice(),
                        carnet.getStartDate(),
                        carnet.getUser(),
                        carnet.getId());
            } else carnetRepository.save(carnet);
        }
    @Transactional
    public void update(CarnetType carnetType) {
        if(carnetRepository.findById(carnetType.getId()).isPresent()){
            carnetTypeRepository.update(
                    carnetType.getAccessNumber(),
                    carnetType.getEntrances(),
                    carnetType.getDescription(),
                    carnetType.getPrice(),
                    carnetType.isAvailable(),
                    carnetType.getId());

        } else carnetTypeRepository.save(carnetType);
    }
}
