package mk.ukim.finki.mk.bookandstay_application.model.exceptions;

public class HousingNotFound extends RuntimeException{
    public HousingNotFound() {
        super("Accommodation not found");
    }
}