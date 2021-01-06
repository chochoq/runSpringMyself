package helloIDSpring.helloSpring.repository;

import helloIDSpring.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //저장
    Member save(Member member);
    //id  검색
    Optional<Member> findById(Long id);
    //name 검색
    Optional<Member> findByName(String name);
    //모두 검색
    List<Member> findAll();
}
