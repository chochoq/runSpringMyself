package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장소에 저장
    //저장소에 find를 통해 찾아올수있
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //지금까지 저장된 모든 리스트를 받아올수있다
    List<Member> findAll();

}
