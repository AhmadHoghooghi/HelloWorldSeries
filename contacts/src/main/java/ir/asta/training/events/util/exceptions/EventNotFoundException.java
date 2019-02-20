package ir.asta.training.events.util.exceptions;

public class EventNotFoundException extends RuntimeException{
    private Long id;

    public EventNotFoundException(Long id) {
        this.id = id;
    }

    public EventNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public EventNotFoundException(String message, Throwable cause, Long id) {
        super(message, cause);
        this.id = id;
    }

    public EventNotFoundException(Throwable cause, Long id) {
        super(cause);
        this.id = id;
    }

    public EventNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Long id) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
