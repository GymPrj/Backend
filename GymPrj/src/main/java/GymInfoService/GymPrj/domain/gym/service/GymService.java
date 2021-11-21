package GymInfoService.GymPrj.domain.gym.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.category.repository.CityCategoryRepository;
import GymInfoService.GymPrj.domain.category.repository.MemberTypeCategoryRepository;
import GymInfoService.GymPrj.domain.category.repository.TownCategoryRepository;
import GymInfoService.GymPrj.domain.gym.dto.GymForm;
import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.repository.GymRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GymService {

    private final MemberTypeCategoryRepository memberTypeCategoryRepository;

    private final CityCategoryRepository cityCategoryRepository;

    private final TownCategoryRepository townCategoryRepository;

    private final GymRepository gymRepository;

    private final GymValidator gymValidator;

    private final PasswordEncoder passwordEncoder;

    public GymService(MemberTypeCategoryRepository memberTypeCategoryRepository, CityCategoryRepository cityCategoryRepository, TownCategoryRepository townCategoryRepository, GymRepository gymRepository, GymValidator gymValidator, PasswordEncoder passwordEncoder) {
        this.memberTypeCategoryRepository = memberTypeCategoryRepository;
        this.cityCategoryRepository = cityCategoryRepository;
        this.townCategoryRepository = townCategoryRepository;
        this.gymRepository = gymRepository;
        this.gymValidator = gymValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void join(GymForm gymForm){

        Long cityId = gymForm.getCityId();
        Long townId = gymForm.getTownId();

        cityCategoryRepository.findById(cityId).orElseThrow(() -> new GymPrjException(ErrorCode.CITY_NOT_FOUNT));

        townCategoryRepository.findById(townId).orElseThrow(() -> new GymPrjException(ErrorCode.TOWN_NOT_FOUNT));

        Gym gym = gymForm.entity(gymValidator,passwordEncoder);

        gym.mapCity(cityId);
        gym.mapTown(townId);

        gymRepository.save(gym);


    }
}
