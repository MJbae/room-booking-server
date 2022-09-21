package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Place extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Meeting.class)
    @JoinColumn(name = "meeting_id", updatable = false, insertable = false)
    private Meeting meeting;
}
