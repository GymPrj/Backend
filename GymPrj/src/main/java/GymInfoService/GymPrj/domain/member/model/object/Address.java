package GymInfoService.GymPrj.domain.member.model.object;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String town;

    public Address(String city, String town) {
        this.city = city;
        this.town = town;
    }
}
