package user.registration.exceptions;

public class WeekPasswordException extends Exception {

    private String problem;
    public WeekPasswordException(String problem) {
        super(String.format("Password does not contain at least %s !", problem));
        this.problem = problem;
    }
    }
