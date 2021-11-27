package GymInfoService.GymPrj.domain.gym.service;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.gym.dto.TrainerCommentForm;
import GymInfoService.GymPrj.domain.gym.model.Trainer;
import GymInfoService.GymPrj.domain.gym.model.TrainerComment;
import GymInfoService.GymPrj.domain.gym.repository.TrainerCommentRepository;
import GymInfoService.GymPrj.domain.gym.repository.TrainerRepository;
import GymInfoService.GymPrj.domain.member.model.Member;
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

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new GymPrjException(ErrorCode.MEMBER_NOT_FOUND));
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new GymPrjException(ErrorCode.TRAINER_NOT_FOUND));

        if(isBelongToTrainer(member, trainer)){
            throw new GymPrjException(ErrorCode.NOT_BELONG_TRAINER);
        }

        TrainerComment trainerComment = trainerCommentForm.entity();
        trainerComment.mapWriterId(memberId);
        trainerComment.mapTrainer(trainer);

        return trainerCommentRepository.save(trainerComment).id();
    }

    @Transactional
    public void updateTrainerComment(Long memeberId, Long trainerCommentId, TrainerCommentForm trainerCommentForm){

        TrainerComment trainerComment = trainerCommentRepository.findById(trainerCommentId).orElseThrow(() -> new GymPrjException(ErrorCode.COMMENT_NOT_FOUND));
        trainerComment.checkWriterId(memeberId);
        trainerComment.updateContent(trainerCommentForm.getContent());

    }

    @Transactional
    public void deleteTrainerComment(Long memeberId, Long trainerCommentId){

        TrainerComment trainerComment = trainerCommentRepository.findById(trainerCommentId).orElseThrow(() -> new GymPrjException(ErrorCode.COMMENT_NOT_FOUND));
        trainerComment.checkWriterId(memeberId);
        trainerComment.delete();

    }

    private boolean isBelongToTrainer(Member member, Trainer trainer) {
        return member.getTrainerId() != trainer.id();
    }
}
