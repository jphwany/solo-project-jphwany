package jphwany.soloproject.api.v1.member.controller;


import jphwany.soloproject.api.v1.dto.MultiResponseDto;
import jphwany.soloproject.api.v1.dto.SingleResponseDto;
import jphwany.soloproject.api.v1.member.dto.MemberPostDto;
import jphwany.soloproject.api.v1.member.entity.Member;
import jphwany.soloproject.api.v1.member.mapper.MemberMapper;
import jphwany.soloproject.api.v1.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Slf4j
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @GetMapping("/filter")
    public ResponseEntity getMembersByFilter(
            @RequestParam("companyType") String companyType,
            @RequestParam("companyLocation") String companyLocation){

        List<Member> memberList = memberService.findByFilter(companyType, companyLocation);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.membersToMemberResponses(memberList)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findByMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponses(members),
                        pageMembers),
                HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto postDto) {
        Member member = mapper.memberPostDtoToMember(postDto);
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity(new SingleResponseDto<>(mapper.memberToMemberResponse(createdMember)), HttpStatus.CREATED);
    }

}
