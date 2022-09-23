package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Group extends BaseEntity {

    private String name;

    public Group(String name){
        this.name = name;
    }
}
