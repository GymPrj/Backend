package GymInfoService.GymPrj.domain.gym.service.query;

import GymInfoService.GymPrj.domain.gym.dto.TrainerResponse;
import GymInfoService.GymPrj.domain.gym.repository.query.TrainerQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TrainerQueryService {

    private final TrainerQueryRepository trainerQueryRepository;

    public TrainerQueryService(TrainerQueryRepository trainerQueryRepository) {
        this.trainerQueryRepository = trainerQueryRepository;
    }

    public List<TrainerResponse> findTrainerByGymId(Long gymId){

        return trainerQueryRepository.findTrainerByGymId(gymId);
    }
}
