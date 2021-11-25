package GymInfoService.GymPrj.domain.gym.service.query;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.gym.dto.TrainerCommentForm;
import GymInfoService.GymPrj.domain.gym.model.Trainer;
import GymInfoService.GymPrj.domain.gym.model.TrainerComment;
import GymInfoService.GymPrj.domain.gym.repository.TrainerCommentRepository;
import GymInfoService.GymPrj.domain.gym.repository.TrainerRepository;
import GymInfoService.GymPrj.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TrainerCommentService {

    private final TrainerCommentRepository trainerCommentRepository;

    private final MemberRepository memberRepository;

    private final TrainerRepository trainerRepository;

    public TrainerCommentService(TrainerCommentRepository trainerCommentRepository, MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.trainerCommentRepository = trainerCommentRepository;
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    @Transactional
    public Long registerTrainerComment(Long memberId, Long trainerId, TrainerCommentForm trainerCommentForm){

        memberRepository.findById(memberId).orElseThrow(() -> new GymPrjException(ErrorCode.MEMBER_NOT_FOUND));
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new GymPrjException(ErrorCode.TRAINER_NOT_FOUND));

        TrainerComment trainerComment = trainerCommentForm.entity();
        trainerComment.mapWriterId(memberId);
        trainerComment.mapTrainer(trainer);

        return trainerCommentRepository.save(trainerComment).id();
    }
}
