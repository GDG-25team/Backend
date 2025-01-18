package gdg.team25.domain.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private String id;

    private Long age;

    private String name;

    private String email;

    private String password;

    private String gender;

    private String phoneNumber;

    private String address;

    @Builder
    public Member(Long age, String password, String gender, String phoneNumber, String email) {
        this.age = age;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = "";
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
