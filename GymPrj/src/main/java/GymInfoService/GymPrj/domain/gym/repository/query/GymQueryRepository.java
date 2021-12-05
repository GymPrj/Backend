package GymInfoService.GymPrj.domain.gym.repository.query;

import GymInfoService.GymPrj.domain.gym.dto.FindGymCondition;
import GymInfoService.GymPrj.domain.gym.dto.GymResponse;

import GymInfoService.GymPrj.domain.gym.model.object.JoinStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static GymInfoService.GymPrj.domain.gym.model.QGym.gym;
import static GymInfoService.GymPrj.domain.member.model.QMember.member;

@Repository
public class GymQueryRepository {

    private final JPAQueryFactory query;

    public GymQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public Page<GymResponse> findGymByCondition(FindGymCondition findGymCondition, Pageable pageable) {

        List<GymResponse> gymList = query.select(Projections.constructor(GymResponse.class,
                gym.id,
                gym.gymName,
                gym.ceoName,
                gym.tel,
                member.count(),
                gym.detailAddress
                )
        )
                .from(gym)
                .leftJoin(member)
                .on(gym.id.eq(member.gymId), member.deletedDateTime.isNull())
                .where(
                        gym.deletedDateTime.isNull(),
                        cityIdEq(findGymCondition.getCityId()),
                        townIdEq(findGymCondition.getTownId()),
                        gymNameContains(findGymCondition.getGymName()),
                        joinStatusCheck()
                )
                .groupBy(gym.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(gymList, pageable, gymList.size());


    }

    private BooleanExpression joinStatusCheck() {
        return gym.joinStatus.eq(JoinStatus.ACCEPT);
    }

    private BooleanExpression cityIdEq(Long cityId) {
        if (cityId == null) {
            return null;
        }

        return gym.cityId.eq(cityId);
    }

    private BooleanExpression townIdEq(Long townId) {
        if (townId == null) {
            return null;
        }

        return gym.townId.eq(townId);
    }

    private BooleanExpression gymNameContains(String gymName) {
        if (gymName == null || gymName.isBlank()) {
            return null;
        }

        return gym.gymName.contains(gymName);
    }

}
