package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private MemberRepository memberRepository;

   // @Autowired
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public MemberService() {
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //ifPresent은 Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴으로 true일 때 아래 수행
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        /* 코드정리 1
        memberRepository.findByName(member.getName())
                .ifPresent((m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

         */
        //코드정리 2 -> 위와 같은 로직으로 나온다면 함수로 빼준다
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent((m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        }));
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberid){
        return memberRepository.findById(memberid);
    }


}
