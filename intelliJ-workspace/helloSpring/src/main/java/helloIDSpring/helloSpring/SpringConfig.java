package helloIDSpring.helloSpring;

import helloIDSpring.helloSpring.repository.MemberRepository;
import helloIDSpring.helloSpring.repository.MemoryMemberRepository;
import helloIDSpring.helloSpring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
