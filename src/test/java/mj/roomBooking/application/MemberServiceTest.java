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


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("MemberService")
class MemberServiceTest {
    private MemberService service;
    private final MemberRepository memberRepository = mock(MemberRepository.class);

    private final String MEMBER_NAME = "배만진";
    private final String GROUP_NAME = "OGQ";
    private final String TEAM_NAME = "모바일본부";
    private Member member;
    private Team team;
    private Group group;

    @BeforeEach
    void setUp() {
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

    @Nested
    @DisplayName("loadAll 메소드는")
    class Describe_loadAll {
        @BeforeEach
        void setUp() {
            given(memberRepository.findAll()).willReturn(List.of());
        }

        @Test
        @DisplayName("등록된 사용자가 없으면 빈 리스트를 반환한다")
        void it_returns_empty_list() {
            List<Member> memberList = service.loadAll();
            assertThat(memberList).isEmpty();
        }

        @Nested
        @DisplayName("만약 사용자들이 등록했다면")
        class Context_with_users_enrolled {
            private List<Member> membersEnrolled;
            @BeforeEach
            void setUp() {
                Member firstMember = new Member(MEMBER_NAME, team);
                Member secondMember = new Member(MEMBER_NAME, team);
                membersEnrolled = List.of(firstMember, secondMember);
                given(memberRepository.findAll()).willReturn(membersEnrolled);
            }

            @Test
            @DisplayName("모든 사용자들이 반환된다")
            void it_returns_collection_containing_all_users() {
                List<Member> memberList = service.loadAll();
                assertThat(memberList.size()).isEqualTo(membersEnrolled.size());
            }
        }
    }
}
