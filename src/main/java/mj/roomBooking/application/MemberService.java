package mj.roomBooking.application;

import mj.roomBooking.domain.Member;
import mj.roomBooking.domain.Team;
import mj.roomBooking.infra.MemberRepository;

import java.util.List;

public class MemberService {
    public MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member createWith() {
        return repository.save(new Member());
    }


    public void deleteBy(String name) {
        repository.deleteByName(name);
    }

    public List<Member> loadAll() {
        return repository.findAll();
    }
}
