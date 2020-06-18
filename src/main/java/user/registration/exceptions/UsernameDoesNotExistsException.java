package user.registration.exceptions;

public class UsernameDoesNotExistsException extends Exception {

    private String username;

    public UsernameDoesNotExistsException(String username) {
        super(String.format("An account with the username %s doesn not exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
