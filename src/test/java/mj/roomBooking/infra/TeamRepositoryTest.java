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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("TeamRepository")
class TeamRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository repository;
    @Autowired
    private GroupRepository grouprepository;

    private final String MEMBER_NAME = "배만진";
    private final String ORGANIZATION_NAME = "OGQ";
    private final String TEAM_NAME = "모바일본부";
    private Member member;
    private Member memberReturned;
    private Team team;
    private Organization organization;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        grouprepository.deleteAll();
        memberRepository.deleteAll();
        organization = new Organization(ORGANIZATION_NAME);
        team = new Team(TEAM_NAME, organization);
        member = new Member(MEMBER_NAME, team);
        memberReturned = new Member();
    }

    @Nested
    @DisplayName("delete 메소드는")
    class Describe_delete {
        @Nested
        @DisplayName("만약 조직 엔티티가 삭제된다면")
        class Context_with_organization_entity_deleted {
            private Long teamId;
            @BeforeEach
            void setUp() {
                memberReturned = memberRepository.save(member);
                teamId = memberReturned.getTeam().getId();
                repository.deleteById(teamId);
//                teamRepository.deleteById(memberReturned.getTeam().getId());
            }

            @Test
            @DisplayName("연관된 사용자 엔티티도 함께 삭제시킨다")
            void it_deletes_associated_member() {
                Long memberId = memberReturned.getId();
                Optional<Member> memberReturnedAgain = memberRepository.findById(memberId);
                assertThat(memberReturnedAgain).isEmpty();
            }
        }
    }
}