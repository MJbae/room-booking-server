package mj.roomBooking.infra;

import mj.roomBooking.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query(value = "select o from Organization o " +
            "join fetch o.teams " +
            "where o.id = :id")
    Optional<Organization> findById(@Param("id") Long Id);

    void deleteById(Long Id);
}
