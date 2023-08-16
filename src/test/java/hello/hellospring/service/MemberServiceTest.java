package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository;

    @BeforeEach //실행 전에 수행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  //실행 후에 수행
    public void afterEach(){    //메소드 실행 후 DB 값 비워주기
        memberRepository.clearStore();
    }

    @Test   //테스트 코드는 한글로 적어도 됨
    void 회원가입() {
        //give, when, then 패턴으로 작성하면 직관이 쉽다
        //give : 무언가 주어졌을 때
        Member member =  new Member();
        member.setName("hello");

        //when : 실행했을 때
        Long saveId = memberService.join(member);

        //then : 어떤 결과가 기대된다
        /*
          Optional<Member> one = memberService.findOne(saveId);
           -> 코드정리
         */
        Member findMember = memberService.findOne(saveId).get(); //get()을 사용해서 바로 받는다
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){

        //give : 무언가 주어졌을 때
            Member member1 = new Member();
            member1.setName("spring");

            Member member2 = new Member();
            member2.setName("spring");

        //when : 실행했을 때
            memberService.join(member1);
            /* 중복회원 체크 방법 1
                try {
                    memberService.join(member2);
                    fail();         //member2도 spring인데 정상로직으로 넘어가면 안되므로 fail()추가(fail:예외를 잡지 못한 경우에 강제로 에러를 발생)
                }catch (IllegalStateException e){
                    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
                }
             */
            // 중복회원 체크 방법 2 : memberService.join(member2) 실행하면 IllegalStateException.class 에러 발생해야한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then : 어떤 결과가 기대된다
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}