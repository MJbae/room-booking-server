package mj.roomBooking.application;

import mj.roomBooking.domain.Member;
import mj.roomBooking.domain.Team;
import mj.roomBooking.infra.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member createWith(String name, Team team) {
        return repository.save(new Member(name, team));
    }


    public void deleteBy(String name) {
        repository.deleteByName(name);
    }

    public List<Member> loadAll() {
        return repository.findAll();
    }
}
