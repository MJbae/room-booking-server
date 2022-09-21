package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Meeting extends BaseEntity {

    private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "meeting")
    private List<MeetingMember> members;

    @OneToOne
    @JoinColumn(name = "meeting_content_id")
    private MeetingContent content;

    @OneToMany
    @JoinColumn(name = "meeting_id")
    private List<Place> places;
}
