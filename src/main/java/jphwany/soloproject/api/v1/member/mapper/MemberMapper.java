package jphwany.soloproject.api.v1.member.mapper;

import jphwany.soloproject.api.v1.member.dto.MemberPostDto;
import jphwany.soloproject.api.v1.member.dto.MemberResponseDto;
import jphwany.soloproject.api.v1.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

    MemberResponseDto memberToMemberResponse(Member member);
    List<MemberResponseDto> membersToMemberResponses(List<Member> members);

    Member memberPostDtoToMember(MemberPostDto postDto);
}
