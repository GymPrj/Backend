package GymInfoService.GymPrj.domain.gym.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.gym.dto.TrainerForm;
import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.model.Trainer;
import GymInfoService.GymPrj.domain.gym.repository.GymRepository;
import GymInfoService.GymPrj.domain.gym.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TrainerService {

    private final GymRepository gymRepository;

    private final TrainerRepository trainerRepository;

    public TrainerService(GymRepository gymRepository, TrainerRepository trainerRepository) {
        this.gymRepository = gymRepository;
        this.trainerRepository = trainerRepository;
    }

    @Transactional
    public Long registerTrainer(Long gymId, TrainerForm trainerForm){

        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new GymPrjException(ErrorCode.Gym_NOT_FOUND));
        if(gym.checkJoinStatus()){
            throw new GymPrjException(ErrorCode.GYM_PENDING_STATUS);
        }

        Trainer trainer = trainerForm.entity();
        trainer.mapGym(gym);

        return trainerRepository.save(trainer).id();

    }
}
