package GymInfoService.GymPrj.common.jwt;

import lombok.Data;

@Data
public class MemberGymPayload {

    private Long id;
    private String email;

    protected MemberGymPayload() {
    }

    public MemberGymPayload(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
