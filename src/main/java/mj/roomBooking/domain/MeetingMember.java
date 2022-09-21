package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class MeetingMember extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member.class)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Meeting.class)
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    private Boolean isAttendee;

    private Boolean isReference;
}
