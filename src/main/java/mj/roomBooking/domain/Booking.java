package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Booking extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
