package jphwany.soloproject.api.v1.member.service;


import jphwany.soloproject.api.v1.exception.BusinessLogicException;
import jphwany.soloproject.api.v1.exception.ExceptionCode;
import jphwany.soloproject.api.v1.member.Repository.MemberRepository;
import jphwany.soloproject.api.v1.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findByFilter(String companyType, String companyLocation){
        List<Member> members = new ArrayList<>();
        if (companyType.length() == 3 && companyLocation.length() != 3){
            members = memberRepository.findByCompanyType(companyType);
        }else if (companyType.length() != 3 && companyLocation.length() == 3){
            members = memberRepository.findByCompanyLocation(companyLocation);
        }else if (companyType.length() == 3 && companyLocation.length() == 3){
            members = memberRepository.findByCompanyTypeAndCompanyLocation(companyType, companyLocation);
        }else throw new RuntimeException();
        return members;
    }

    public List<Member> findByCompanyLocation(String companyLocation) {
        return new ArrayList<>();
    }
    public Page<Member> findByMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    public Member createMember(Member member) {
        verifyExistsCompanyName(member.getCompanyName());
        return memberRepository.save(member);
    }

    private void verifyExistsCompanyName(String companyName) {
        Optional<Member> member = memberRepository.findByCompanyName(companyName);
        if(member.isPresent()){
//            throw new RuntimeException();
//            확장성을 위해 BusinessLogicException 을 따로 생성해보자
            throw new BusinessLogicException(ExceptionCode.COMPANY_NAME_EXISTS);
        }
    }
}
