package GymInfoService.GymPrj.domain.category.repository;

import GymInfoService.GymPrj.domain.category.model.TownCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownCategoryRepository extends JpaRepository<TownCategory, Long> {
}
