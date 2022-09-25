package mj.roomBooking.infra;

import mj.roomBooking.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);

    void deleteByName(String name);
    void deleteById(Long Id);
}
