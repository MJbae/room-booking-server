package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class BookingInDay extends Booking {
    private LocalDate bookingDate;
}