package com.tools.entities;

public class BookingsCollection extends Booking {
    private Booking[] bookings;

    public Booking[] getBookings() {
        return bookings;
    }

    public void setBookings(Booking[] bookings) {
        this.bookings = bookings;
    }
}
