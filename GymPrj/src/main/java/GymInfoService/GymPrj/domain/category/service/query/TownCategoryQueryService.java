package GymInfoService.GymPrj.domain.category.service.query;

import GymInfoService.GymPrj.domain.category.dto.TownCategoryResponse;
import GymInfoService.GymPrj.domain.category.repository.query.TownCategoryQueryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TownCategoryQueryService {

    private final TownCategoryQueryRepository townCategoryQueryRepository;

    public TownCategoryQueryService(TownCategoryQueryRepository townCategoryQueryRepository) {
        this.townCategoryQueryRepository = townCategoryQueryRepository;
    }

    public List<TownCategoryResponse> findTownCategoryByCityId (Long cityId){
        return townCategoryQueryRepository.findTownCategoryByCityId(cityId);
    }
}
