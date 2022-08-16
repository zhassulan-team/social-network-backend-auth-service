package kata.academy.auth.exception;

public class AccountIsNotEnabledException extends RuntimeException {

    public AccountIsNotEnabledException(String message) {
        super(message);
    }
}
