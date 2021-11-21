package GymInfoService.GymPrj.domain.category.repository;

import GymInfoService.GymPrj.domain.category.model.CityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityCategoryRepository extends JpaRepository<CityCategory, Long> {
}
