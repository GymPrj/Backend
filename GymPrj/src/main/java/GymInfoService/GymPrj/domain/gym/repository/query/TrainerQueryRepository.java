package GymInfoService.GymPrj.domain.gym.repository.query;

import GymInfoService.GymPrj.domain.gym.dto.TrainerResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static GymInfoService.GymPrj.domain.gym.model.QTrainer.trainer;

@Repository
public class TrainerQueryRepository {

    private final JPAQueryFactory query;

    public TrainerQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public List<TrainerResponse> findTrainerByGymId(Long gymId){

        return query.select(Projections.constructor(TrainerResponse.class,
                trainer.id,
                trainer.name,
                trainer.sex,
                trainer.age,
                trainer.career))
                .from(trainer)
                .where(
                        isNotDeleted(),
                        gymIdEq(gymId)
                )
                .fetch();


    }

    private BooleanExpression gymIdEq(Long gymId) {
        return trainer.gym.id.eq(gymId);
    }

    private BooleanExpression isNotDeleted() {
        return trainer.deletedDateTime.isNull();
    }
}
