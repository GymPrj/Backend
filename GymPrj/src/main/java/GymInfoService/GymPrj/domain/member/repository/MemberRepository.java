package GymInfoService.GymPrj.domain.member.repository;

import GymInfoService.GymPrj.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByEmailId(String emailId);
}
