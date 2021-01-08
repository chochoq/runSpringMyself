package helloIDSpring.helloSpring;

import helloIDSpring.helloSpring.repository.JdbcMemberRepository;
import helloIDSpring.helloSpring.repository.JdbcTemplateMemberRepository;
import helloIDSpring.helloSpring.repository.MemberRepository;
import helloIDSpring.helloSpring.repository.MemoryMemberRepository;
import helloIDSpring.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

        return new JdbcTemplateMemberRepository(dataSource);
    }
}
