package jphwany.soloproject.api.v1.member.service;


import jphwany.soloproject.api.v1.member.Repository.MemberRepository;
import jphwany.soloproject.api.v1.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findByCompanyType(String companyType) {
        return new ArrayList<>();
    }

    public List<Member> findByCompanyLocation(String companyLocation) {
        return new ArrayList<>();
    }
    public Page<Member> findByMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }
}
