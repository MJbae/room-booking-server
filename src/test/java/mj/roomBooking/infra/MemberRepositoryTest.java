package mj.roomBooking.infra;

import mj.roomBooking.domain.Organization;
import mj.roomBooking.domain.Member;
import mj.roomBooking.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})

@DisplayName("MemberRepository")
class MemberRepositoryTest {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GroupRepository groupRepository;

    private final String MEMBER_NAME = "배만진";
    private final String ORGANIZATION_NAME = "OGQ";
    private final String TEAM_NAME = "모바일본부";
    private Member member;
    private Member memberReturned;
    private Team team;
    private Organization organization;

    @BeforeEach
    void setUp() {
        organization = new Organization(ORGANIZATION_NAME);
        team = new Team(TEAM_NAME, organization);
        member = new Member(MEMBER_NAME, team);
        memberReturned = new Member();
    }

    @Nested
    @DisplayName("save 메소드는")
    class Describe_save {
        @Nested
        @DisplayName("만약 사용자 엔티티가 저장된다면")
        class Context_with_member_entity_saved {
            @BeforeEach
            void setUp() {
                groupRepository.save(organization);
                teamRepository.save(team);
                memberReturned = repository.save(member);
            }

            @Test
            @DisplayName("팀 엔티티를 참조할 수 있는 사용자 엔티티를 반환한다")
            void it_returns_member_referencing_team() {
                assertThat(memberReturned.getTeam().getName()).isEqualTo(TEAM_NAME);
            }

            @Test
            @DisplayName("조직 엔티티를 참조할 수 있는 사용자 엔티티를 반환한다")
            void it_returns_member_referencing_organization() {
                assertThat(memberReturned.getTeam().getOrganization().getName()).isEqualTo(ORGANIZATION_NAME);
            }
        }
    }


}
