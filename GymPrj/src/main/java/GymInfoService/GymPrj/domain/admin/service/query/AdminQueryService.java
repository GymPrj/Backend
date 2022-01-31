package GymInfoService.GymPrj.domain.admin.service.query;

import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.domain.admin.dto.FindGymPendingStatusResponse;
import GymInfoService.GymPrj.domain.admin.repository.query.AdminQueryRepository;
import GymInfoService.GymPrj.domain.gym.model.Gym;
import GymInfoService.GymPrj.domain.gym.repository.GymRepository;
import GymInfoService.GymPrj.domain.member.model.Member;
import GymInfoService.GymPrj.domain.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminQueryService {
    private final AdminQueryRepository adminQueryRepository;

    private final MemberRepository memberRepository;

    private final GymRepository gymRepository;

    public AdminQueryService(AdminQueryRepository adminQueryRepository, MemberRepository memberRepository, GymRepository gymRepository) {
        this.adminQueryRepository = adminQueryRepository;
        this.memberRepository = memberRepository;
        this.gymRepository = gymRepository;
    }

    public Page<FindGymPendingStatusResponse> findGymPendingStatus(Long memberId, Pageable pageable){

        checkMemberAdmin(memberId);
        return adminQueryRepository.findGymPendingStatus(pageable);

    }

    @Transactional
    public void acceptGym(Long memberId, Long gymId){
        checkMemberAdmin(memberId);

        Gym findGym = gymRepository.findById(gymId).orElseThrow(() -> new GymPrjException(ErrorCode.Gym_NOT_FOUND));

        if(!findGym.checkJoinStatus()){
            throw new GymPrjException(ErrorCode.GYM_NOT_PENDING_STATUS);
        }

        findGym.acceptGym();

    }

    private void checkMemberAdmin(Long memberId) {
        Member adminMember = memberRepository.findById(memberId).orElseThrow(() -> new GymPrjException(ErrorCode.MEMBER_NOT_FOUND));
        if(memberNotAdmin(adminMember)){
            throw new GymPrjException(ErrorCode.Member_NOT_ADMIN);
        }
    }

    private boolean memberNotAdmin(Member adminMember) {
        return adminMember.getMemberTypeId() != 3L;
    }

}
