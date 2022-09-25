package mj.roomBooking.infra;

import mj.roomBooking.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findById(Long Id);
    void deleteById(Long Id);
}
