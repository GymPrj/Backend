package GymInfoService.GymPrj.domain.gym.repository;

import GymInfoService.GymPrj.domain.gym.model.TrainerComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerCommentRepository extends JpaRepository<TrainerComment, Long> {
}
