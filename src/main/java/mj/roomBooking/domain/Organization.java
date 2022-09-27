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


    @OneToMany(mappedBy = "organization")
    private List<Team> teams = new ArrayList<>();

    public Organization(String name){
        this.name = name;
    }
}
