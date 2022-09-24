package mj.roomBooking.infra;

import mj.roomBooking.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Organization, Long> {
}
