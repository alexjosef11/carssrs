package user.registration.exceptions;

public class FieldNotCompletedException extends Exception {

    public FieldNotCompletedException() {
        super("Please complete all necessary fields!");
    }
}
