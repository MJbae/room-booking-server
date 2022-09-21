package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Place extends BaseEntity{

    private String name;

    @ManyToOne
    @JoinColumn(name = "meeting_id", updatable = false, insertable = false)
    private Meeting meeting;
}
