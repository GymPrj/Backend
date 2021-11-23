package GymInfoService.GymPrj.domain.gym.service.query;

import GymInfoService.GymPrj.domain.gym.dto.FindGymCondition;
import GymInfoService.GymPrj.domain.gym.dto.GymResponse;
import GymInfoService.GymPrj.domain.gym.repository.query.GymQueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GymQueryService {

    private final GymQueryRepository gymQueryRepository;

    public GymQueryService(GymQueryRepository gymQueryRepository) {
        this.gymQueryRepository = gymQueryRepository;
    }

    public Page<GymResponse> findGymByCondition(FindGymCondition findGymCondition, Pageable pageable){

        return gymQueryRepository.findGymByCondition(findGymCondition,pageable);
    }
}
