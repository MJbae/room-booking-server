package mj.roomBooking.infra;

import mj.roomBooking.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GroupRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(Long Id);

    void deleteById(Long Id);
}
