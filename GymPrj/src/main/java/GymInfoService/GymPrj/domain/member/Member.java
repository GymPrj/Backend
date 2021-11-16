package GymInfoService.GymPrj.domain.member;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String password;

    protected Member() {
    }

    @Builder
    public Member(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
