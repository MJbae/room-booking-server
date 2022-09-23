package mj.roomBooking.infra;

import mj.roomBooking.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team, Long> {
}
