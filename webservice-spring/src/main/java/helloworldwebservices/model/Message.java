package helloworldwebservices.model;

public class Message {

    private long id;
    private String content;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
