package GymInfoService.GymPrj.domain.gym.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.repository.GymRepository;
import org.springframework.stereotype.Component;

@Component
public class GymValidator {

    private final GymRepository gymRepository;

    public GymValidator(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public void validateEmail(String email){
        Long count = gymRepository.countByEmail(email);

        if(isPresent(count)){
            Gym gym = gymRepository.findByEmail(email).get();
            if(isPending(gym)){
                throw new GymPrjException(ErrorCode.GYM_PENDING_STATUS);
            }else{
                throw new GymPrjException(ErrorCode.DUPLICATE_EMAIL);
            }
        }
    }

    private boolean isPending(Gym gym) {
        return gym.checkJoinStatus();
    }

    private boolean isPresent(Long count) {
        return count > 0;
    }
}
