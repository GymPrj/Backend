package GymInfoService.GymPrj.domain.admin.repository.query;

import GymInfoService.GymPrj.domain.admin.dto.FindGymPendingStatusResponse;
import GymInfoService.GymPrj.domain.gym.model.object.JoinStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static GymInfoService.GymPrj.domain.category.model.QCityCategory.cityCategory;
import static GymInfoService.GymPrj.domain.category.model.QTownCategory.townCategory;
import static GymInfoService.GymPrj.domain.gym.model.QGym.gym;

@Repository
public class AdminQueryRepository {

    private final JPAQueryFactory query;

    public AdminQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Page<FindGymPendingStatusResponse> findGymPendingStatus(Pageable pageable){

        List<FindGymPendingStatusResponse> response = query.select(Projections.constructor(FindGymPendingStatusResponse.class,
                cityCategory.cityName,
                townCategory.townName,
                gym.detailAddress,
                gym.gymName,
                gym.ceoName,
                gym.businessNumber,
                gym.tel))
                .from(gym)
                .join(cityCategory).on(gym.cityId.eq(cityCategory.id)).fetchJoin()
                .join(townCategory).on(gym.townId.eq(townCategory.id)).fetchJoin()
                .where(
                        gymStatusPendingEq(),
                        isNotDeleted()
                )
                .fetch();

        return new PageImpl<>(response, pageable, response.size());


    }

    private BooleanExpression isNotDeleted() {
        return gym.deletedDateTime.isNull();
    }

    private BooleanExpression gymStatusPendingEq() {
        return gym.joinStatus.eq(JoinStatus.PENDING);
    }

}
