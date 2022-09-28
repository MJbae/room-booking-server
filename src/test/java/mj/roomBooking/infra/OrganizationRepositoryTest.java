package mj.roomBooking.infra;

import mj.roomBooking.domain.Member;
import mj.roomBooking.domain.Organization;
import mj.roomBooking.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("OrganizationRepository")
class OrganizationRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private OrganizationRepository repository;

    private final String MEMBER_NAME = "배만진";
    private final String ORGANIZATION_NAME = "OGQ";
    private final String TEAM_NAME = "모바일본부";
    private final String SECOND_TEAM_NAME = "플랫폼본부";
    private Member member;
    private List<Team> teams;
    private Team team;
    private Organization organization;
    private Organization organizationReturned;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        teamRepository.deleteAll();
        memberRepository.deleteAll();

        organization = new Organization(ORGANIZATION_NAME);
        team = new Team(TEAM_NAME, organization);
        member = new Member(MEMBER_NAME, team);
        organizationReturned = new Organization();

        organization = repository.save(organization);
        teams = organization.getTeams();
        teams.add(team);
    }

    @Nested
    @DisplayName("영속성 전이된 컬렉션의 요소의 참조를 제거한다면")
    class Describe_removing_reference_of_cascaded_collection_element {
        @BeforeEach
        void setUp() {
            teams.remove(team);
        }

        @Test
        @DisplayName("영속성 전이된 컬렉션의 요소가 삭제된다")
        void it_deletes_cascaded_collection_element() {
            organizationReturned = repository.findById(organization.getId()).get();
            assertThat(organizationReturned.getTeams()).isEmpty();
        }
    }

    @Nested
    @DisplayName("영속성 전이된 컬렉션의 요소의 참조를 추가한다면")
    class Describe_adding_reference_of_cascaded_collection_element {
        @BeforeEach
        void setUp() {
            Team secondTeam = new Team(SECOND_TEAM_NAME, organization);
            teams.add(secondTeam);
        }

        @Test
        @DisplayName("영속성 전이된 컬렉션의 요소가 추가된다")
        void it_saves_cascaded_collection_element() {
            organizationReturned = repository.findById(organization.getId()).get();
            assertThat(organizationReturned.getTeams().get(1).getName()).isEqualTo(SECOND_TEAM_NAME);
        }

    }
}
