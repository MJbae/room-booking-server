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
public class Team extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Group.class, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
