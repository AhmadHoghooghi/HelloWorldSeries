package ir.asta.training.contacts.util.exceptions;

public class ContactNotFoundException extends RuntimeException {
    private Long id;

    public ContactNotFoundException(Long id) {
        this.id = id;
    }

    public ContactNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public ContactNotFoundException(String message, Throwable cause, Long id) {
        super(message, cause);
        this.id = id;
    }

    public ContactNotFoundException(Throwable cause, Long id) {
        super(cause);
        this.id = id;
    }

    public ContactNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Long id) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.id = id;
    }
}
