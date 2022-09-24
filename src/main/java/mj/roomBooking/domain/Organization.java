package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Organization extends BaseEntity {

    private String name;

    public Organization(String name){
        this.name = name;
    }
}
