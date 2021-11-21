package GymInfoService.GymPrj.domain.gym.repository;

import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {

    Optional<Gym> findByEmail(String emailId);

    Long countByEmail(String email);
}
