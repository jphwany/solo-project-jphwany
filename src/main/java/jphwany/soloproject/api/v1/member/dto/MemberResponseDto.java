package jphwany.soloproject.api.v1.member.dto;

import jphwany.soloproject.api.v1.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponseDto {

    private Long memberId;
    private String name;
    private Member.Gender gender;
    private String companyName;
    private String companyType;
    private String companyLocation;

    public String getMemberGender(){
        return gender.getGender();
    }

}
