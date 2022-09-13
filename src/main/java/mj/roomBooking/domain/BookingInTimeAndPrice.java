package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
public class BookingInTimeAndPrice extends BookingInTime {
    private BigDecimal price;
}