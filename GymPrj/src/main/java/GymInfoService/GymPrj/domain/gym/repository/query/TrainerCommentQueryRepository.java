package GymInfoService.GymPrj.domain.gym.repository.query;

import GymInfoService.GymPrj.domain.gym.dto.TrainerCommentResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static GymInfoService.GymPrj.domain.gym.model.QTrainerComment.trainerComment;
import static GymInfoService.GymPrj.domain.member.model.QMember.member;

@Repository
public class TrainerCommentQueryRepository {

    private final JPAQueryFactory query;

    public TrainerCommentQueryRepository(JPAQueryFactory query) {
        this.query = query;
    }

    public List<TrainerCommentResponse> findTrainerComment(Long trainerId){
        return query.select(Projections.constructor(TrainerCommentResponse.class,
                member.name,
                trainerComment.content,
                trainerComment.createdDateTime))
                .from(trainerComment)
                .join(member)
                .on(trainerComment.writerId.eq(member.id))
                .where(
                        isNotDeleted(),
                        trainerIdEq(trainerId)
                )
                .orderBy(trainerComment.createdDateTime.desc())
                .fetch();
    }

    private BooleanExpression trainerIdEq(Long trainerId) {
        return trainerComment.trainer.id.eq(trainerId);
    }

    private BooleanExpression isNotDeleted() {
        return trainerComment.deletedDateTime.isNull();
    }
}
