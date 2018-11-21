package helloworldwebservices.features.message.exceptions;

public class MessageNotFoundException extends RuntimeException {
    private Long id;

    public MessageNotFoundException(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
