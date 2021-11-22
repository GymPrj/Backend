package GymInfoService.GymPrj.domain.category.service;

import GymInfoService.GymPrj.domain.category.dto.CityCategoryResponse;
import GymInfoService.GymPrj.domain.category.model.CityCategory;
import GymInfoService.GymPrj.domain.category.repository.CityCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityCategoryService {

    private final CityCategoryRepository cityCategoryRepository;

    public CityCategoryService(CityCategoryRepository cityCategoryRepository) {
        this.cityCategoryRepository = cityCategoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CityCategoryResponse> findAll(){

        List<CityCategory> cityCategory = cityCategoryRepository.findAll();

        List<CityCategoryResponse> cityCategoryList = cityCategory.stream().map(c -> new CityCategoryResponse(c)).collect(Collectors.toList());

        return cityCategoryList;
    }



}
