package GymInfoService.GymPrj.domain.gym.model;

import GymInfoService.GymPrj.common.base.Base;
import GymInfoService.GymPrj.common.exception.ErrorCode;
import GymInfoService.GymPrj.common.exception.GymPrjException;
import GymInfoService.GymPrj.common.jwt.MemberGymPayload;
import GymInfoService.GymPrj.domain.gym.model.object.JoinStatus;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Gym extends Base {

    @Id
    @GeneratedValue
    @Column(name = "gym_id")
    private Long id;

    private Long memberTypeId;

    private Long cityId;

    private Long townId;

    private String detailAddress;

    private String email;

    private String password;

    private String gymName;

    private String ceoName;

    private String businessNumber;

    private String tel;

    @Enumerated(STRING)
    private JoinStatus joinStatus;

    @Builder
    public Gym(Long id, String email, String password, String gymName, String ceoName, String businessNumber, String tel, Long cityId, Long townId, String detailAddress ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gymName = gymName;
        this.ceoName = ceoName;
        this.businessNumber = businessNumber;
        this.tel = tel;
        this.joinStatus = JoinStatus.PENDING;
        this.memberTypeId = 2L;
        this.cityId = cityId;
        this.townId = townId;
        this.detailAddress = detailAddress;
    }

    public void mapCity(Long cityId){
        this.cityId = cityId;
    }

    public void mapTown(Long townId){
        this.townId = townId;
    }

    public boolean checkJoinStatus(){
        if (this.joinStatus == JoinStatus.PENDING){
            return true;
        }else{
            return false;
        }
    }

    public void checkPassword(String password, PasswordEncoder passwordEncoder){
        if(isNotEqualsPassword(password, passwordEncoder)){
            throw new GymPrjException(ErrorCode.NOT_EQUAL_PASSWORD);
        }
    }

    public void acceptGym(){
        this.joinStatus = JoinStatus.ACCEPT;
    }
    private boolean isNotEqualsPassword(String password, PasswordEncoder passwordEncoder) {
        return !passwordEncoder.matches(password, this.password);
    }

    public MemberGymPayload createPayload(){return new MemberGymPayload(id, email);}



}
