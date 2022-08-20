package jphwany.soloproject.api.v1.member.dto;


import jphwany.soloproject.api.v1.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MemberPostDto {

    @NotBlank(message = "이름은 공백일 수 없습니다")
    private String name;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[FM]$")
    private Member.Gender gender;

    @NotBlank(message = "회사 이름은 공백일 수 없습니다")
    private String companyName;

    @Pattern(regexp = "^[0-9]{3}$")
    private String companyType;

    @Pattern(regexp = "^\\d{3}$")
    private String companyLocation;

    // "^[0-9]{3}$" == "^\\d{3}$"
    // 3자리 수
}
