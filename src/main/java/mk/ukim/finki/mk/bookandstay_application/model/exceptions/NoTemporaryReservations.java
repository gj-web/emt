package mk.ukim.finki.mk.bookandstay_application.model.exceptions;

public class NoTemporaryReservations extends RuntimeException{
    public NoTemporaryReservations() {
        super("No temporary reservations");
    }
}