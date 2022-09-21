package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Meeting extends BaseEntity {

    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meeting")
    private List<MeetingMember> members;

    @OneToOne
    private MeetingContent content;

    @OneToMany
    @JoinColumn(name = "meeting_id")
    private List<Place> places;
}
