package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 나와도 감싸서 프론트단에서 처리할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //자바 내장함수
                .findAny(); //하나라도 찾으면 반환 없으면 null반환 -> Optional이기 때문에 가능
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values() map의 모든 value 값을 Collection 형태로 반환
    }

    public void clearStore(){
        store.clear();
    }

}
