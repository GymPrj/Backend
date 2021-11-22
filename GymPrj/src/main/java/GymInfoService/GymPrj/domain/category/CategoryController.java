package GymInfoService.GymPrj.domain.category;

import GymInfoService.GymPrj.domain.category.service.CityCategoryService;
import GymInfoService.GymPrj.domain.category.service.TownCategoryService;
import GymInfoService.GymPrj.domain.category.service.query.TownCategoryQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CityCategoryService cityCategoryService;

    private final TownCategoryQueryService townCategoryQueryService;

    public CategoryController(CityCategoryService cityCategoryService, TownCategoryQueryService townCategoryQueryService) {
        this.cityCategoryService = cityCategoryService;
        this.townCategoryQueryService = townCategoryQueryService;
    }

    @GetMapping("/city")
    public ResponseEntity<?> findCityCategory(){
        return ResponseEntity.ok(cityCategoryService.findAll());
    }

    @GetMapping("/city/{cityId}/town")
    public ResponseEntity<?> findTownCategoryByCityId(@PathVariable Long cityId){
        return ResponseEntity.ok(townCategoryQueryService.findTownCategoryByCityId(cityId));
    }
}
