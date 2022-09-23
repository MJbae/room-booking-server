package mj.roomBooking.infra;

import mj.roomBooking.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
}
