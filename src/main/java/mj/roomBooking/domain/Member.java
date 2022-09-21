package mj.roomBooking.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Team.class)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
