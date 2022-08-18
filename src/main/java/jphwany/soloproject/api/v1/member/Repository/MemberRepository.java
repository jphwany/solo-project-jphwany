package jphwany.soloproject.api.v1.member.Repository;

import jphwany.soloproject.api.v1.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
}
