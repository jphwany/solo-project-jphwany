package jphwany.soloproject.api.v1.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false, updatable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private String companyType;

    @Column(nullable = false)
    private String companyLocation;


    public enum Gender {
        Male("M"),
        Female("F");

        @Getter
        private String gender;

        Gender(String gender){
            this.gender = gender;
        }
    }

}
