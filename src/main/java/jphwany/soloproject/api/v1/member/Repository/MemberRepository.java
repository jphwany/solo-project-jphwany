package jphwany.soloproject.api.v1.member.Repository;

import jphwany.soloproject.api.v1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByCompanyType(String companyType);

    List<Member> findByCompanyLocation(String companyLocation);

    List<Member> findByCompanyTypeAndCompanyLocation(String companyType, String companyLocation);

    Optional<Member> findByCompanyName(String companyName);
}
