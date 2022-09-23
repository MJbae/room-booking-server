package mj.roomBooking.application;

import antlr.ASTNULLType;
import mj.roomBooking.domain.Booking;
import mj.roomBooking.domain.BookingInTime;
import mj.roomBooking.infra.BookingRepository;

import java.util.List;

public class BookingService {

    public BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking create() {
        return repository.save(new BookingInTime());
    }

    public List<Booking> loadAll() {
        return repository.findAll();
    }

    public void delete() {
        BookingInTime bookingDeleting = new BookingInTime();
        repository.delete(bookingDeleting);
    }
}
