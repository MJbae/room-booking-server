package mj.roomBooking.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Team extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Group.class)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    public Team(String name, Group group){
        this.name = name;
        this.group = group;
    }
}
