package repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest { //test용으로 public으로 안해도 됨

    //MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository(); //MemoryMemberRepository에 있는 clearStore 사용하기 위해 변경

    @AfterEach  //콜백 개념으로 하나의 함수 종료마다 실행됨
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
            Member member = new Member();
            member.setName("spring");

            repository.save(member);
            Member result = repository.findById(member.getId()).get(); //반환 타입이 Optional일 때 get()으로 꺼내준다(Optional까서 꺼낼 수 있어 Member로 받을 수 있음)

            //검증확인 3가지
            //1. System.out.println("result = " + (result ==member));
            //2. Assertions.assertEquals(member, result);
            //3. Assertions.assertThat(member).isEqualTo(result);
            // 또는 Assertions을 static import로 올려서 사용해도 됨
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
