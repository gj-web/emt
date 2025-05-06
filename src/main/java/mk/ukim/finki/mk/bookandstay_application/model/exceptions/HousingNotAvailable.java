package mk.ukim.finki.mk.bookandstay_application.model.exceptions;

public class HousingNotAvailable extends RuntimeException{
    public HousingNotAvailable() {
        super("Accommodation is not available");
    }
}