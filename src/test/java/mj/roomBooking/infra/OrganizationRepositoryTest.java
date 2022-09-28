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
    private Member member;
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
    }

    @Nested
    @DisplayName("delete 메소드는")
    class Describe_delete {
        @Nested
        @DisplayName("만약 조직 엔티티가 삭제된다면")
        class Context_with_organization_entity_deleted {
            private Long organizationId;
            private Long teamId;
            private Long memberId;

            @BeforeEach
            void setUp() {
                organization = repository.save(organization);
                team = teamRepository.save(team);
                member = memberRepository.save(member);

                memberId = member.getId();
                memberRepository.deleteById(memberId);

                teamId = team.getId();
                teamRepository.deleteById(teamId);

                organizationId = organization.getId();
                repository.deleteById(organizationId);
            }

            @Test
            @DisplayName("조직 엔티티는 조회 시 빈값을 반환한다")
            void it_returns_empty_result_when_finding_that_organization_entity() {
                Optional<Organization> organizationReturned = repository.findById(organizationId);
                assertThat(organizationReturned).isEmpty();
            }

            @Test
            @DisplayName("연관된 팀 엔티티도 함께 삭제시킨다")
            void it_deletes_associated_team() {
                Optional<Team> teamReturned = teamRepository.findById(teamId);
                assertThat(teamReturned).isEmpty();
            }

            @Test
            @DisplayName("연관된 사용자 엔티티도 함께 삭제시킨다")
            void it_deletes_associated_member() {
                Optional<Member> memberReturnedAgain = memberRepository.findById(memberId);
                assertThat(memberReturnedAgain).isEmpty();
            }
        }
    }

    @Nested
    @DisplayName("save 메소드는")
    class Describe_save {
        @Nested
        @DisplayName("만약 팀 컬렉션에 팀이 추가된다면")
        class Context_with_collection_contains_team {
            @BeforeEach
            void setUp() {
                organization = repository.save(organization);
                List<Team> teams = organization.getTeams();
                teams.add(team);

                organizationReturned = repository.findById(organization.getId()).get();
            }

            @Test
            @DisplayName("조직 엔티티를 저장된다")
            void it_saves_organization_entity() {
                assertThat(organizationReturned.getName()).isEqualTo(ORGANIZATION_NAME);
            }

            @Test
            @DisplayName("팀 엔티티를 저장된다")
            void it_saves_team_entity() {
                    assertThat(organizationReturned.getTeams().get(0).getName()).isEqualTo(TEAM_NAME);
            }


        }
    }
}
