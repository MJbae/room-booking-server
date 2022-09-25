package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Organization extends BaseEntity {

    private String name;


    @OneToMany(mappedBy = "organization", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Team> teams = new ArrayList<Team>();

    public Organization(String name){
        this.name = name;
    }
}
