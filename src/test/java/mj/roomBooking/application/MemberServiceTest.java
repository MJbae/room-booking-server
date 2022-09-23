package mj.roomBooking.application;

import mj.roomBooking.domain.Group;
import mj.roomBooking.domain.Member;
import mj.roomBooking.domain.Team;
import mj.roomBooking.infra.GroupRepository;
import mj.roomBooking.infra.MemberRepository;
import mj.roomBooking.infra.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("MemberService")
class MemberServiceTest {
    private MemberService service;
    private final MemberRepository memberRepository = mock(MemberRepository.class);
    private final TeamRepository teamRepository = mock(TeamRepository.class);
    private final GroupRepository groupRepository = mock(GroupRepository.class);

    private final String MEMBER_NAME = "배만진";
    private final String GROUP_NAME = "OGQ";
    private final String TEAM_NAME = "모바일본부";
    private Member member;
    private Team team;
    private Group group;

    @BeforeEach
    void setUp(){
        service = new MemberService(memberRepository);
        group = new Group(GROUP_NAME);
        team = new Team(TEAM_NAME, group);
        member = new Member(MEMBER_NAME, team);
    }

    @Nested
    @DisplayName("createWith 메소드는")
    class Describe_createWith {
        @BeforeEach
        void setUp() {
            given(memberRepository.save(any())).willReturn(member);
        }
        @Test
        @DisplayName("정확한 사용자 이름을 반환한다")
        void it_returns_exact_name() {
            Member memberCreated = service.createWith(MEMBER_NAME, team);
            assertThat(memberCreated.getName()).isEqualTo(MEMBER_NAME);
        }

        @Test
        @DisplayName("정확한 Team Entity 반환한다")
        void it_returns_exact_team() {
            Member memberCreated = service.createWith(MEMBER_NAME, team);
            assertThat(memberCreated.getTeam()).isEqualTo(team);
        }

        @Test
        @DisplayName("정확한 Team, Group 이름을 반환한다")
        void it_returns_exact_team_name_and_group_name() {
            Member memberCreated = service.createWith(MEMBER_NAME, team);
            assertThat(memberCreated.getTeam().getName()).isEqualTo(TEAM_NAME);
            assertThat(memberCreated.getTeam().getGroup().getName()).isEqualTo(GROUP_NAME);
        }
    }
}