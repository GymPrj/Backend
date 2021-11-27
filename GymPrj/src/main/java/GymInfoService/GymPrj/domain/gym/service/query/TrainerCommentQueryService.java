package GymInfoService.GymPrj.domain.gym.service.query;

import GymInfoService.GymPrj.domain.gym.dto.TrainerCommentResponse;
import GymInfoService.GymPrj.domain.gym.repository.query.TrainerCommentQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TrainerCommentQueryService {

    private final TrainerCommentQueryRepository trainerCommentQueryRepository;

    public TrainerCommentQueryService(TrainerCommentQueryRepository trainerCommentQueryRepository) {
        this.trainerCommentQueryRepository = trainerCommentQueryRepository;
    }

    public List<TrainerCommentResponse> findTrainerComment(Long trainerId){
        return trainerCommentQueryRepository.findTrainerComment(trainerId);
    }


}
