package mj.roomBooking.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Team extends BaseEntity{

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    public Team(String name, Organization organization){
        this.name = name;
        this.organization = organization;
    }
}
