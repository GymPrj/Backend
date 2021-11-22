package GymInfoService.GymPrj.domain.category.repository.query;

import GymInfoService.GymPrj.domain.category.dto.TownCategoryResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static GymInfoService.GymPrj.domain.category.model.QTownCategory.townCategory;

@Repository
public class TownCategoryQueryRepository {

    private final JPAQueryFactory query;


    public TownCategoryQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public List<TownCategoryResponse> findTownCategoryByCityId(Long cityId){

        return query.select(Projections.constructor(TownCategoryResponse.class,
                townCategory.id,
                townCategory.townName))
                .from(townCategory)
                .where(
                        cityIdEq(cityId),
                        isNotDeleted()
                )
                .fetch();

    }

    private BooleanExpression cityIdEq(Long cityId) {
        return townCategory.category.id.eq(cityId);
    }

    private BooleanExpression isNotDeleted() {
        return townCategory.deletedDateTime.isNull();
    }
}
