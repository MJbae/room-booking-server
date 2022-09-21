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
public class Member extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Team.class, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
