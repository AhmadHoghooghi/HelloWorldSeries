package helloworldwebservices.features;

import java.net.URI;
import java.net.URISyntaxException;

public class UriUtil {
    public static final String BaseURL = "http://localhost:8080";
    public static URI getAsUriUsigBase(String url){
        URI uri;
        try {
            uri = new URI(UriUtil.BaseURL + url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return uri;
    }
}
