package GymInfoService.GymPrj.domain.gym.repository;

import GymInfoService.GymPrj.domain.gym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
