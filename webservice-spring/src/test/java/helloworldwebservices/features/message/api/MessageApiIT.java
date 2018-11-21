package helloworldwebservices.features.message.api;

import com.sun.jndi.toolkit.url.Uri;
import helloworldwebservices.features.UriUtil;
import helloworldwebservices.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MessageApiIT {

    @Before
    public void clearDB() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(UriUtil.BaseURL + "/messages");
    }

    @Test
    public void postAMessage_TestWithExchange() {
        //create request entity
        Message message = new Message("a message to post");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        URI uri = UriUtil.getAsUriUsigBase("/messages");
        RequestEntity<Message> requestEntity = new RequestEntity<Message>(message, requestHeaders, HttpMethod.POST, uri);
        //get response entity
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Message> responseEntity = restTemplate.exchange(requestEntity, Message.class);
        Message returnedMessage = responseEntity.getBody();


    }

    @Test
    public void pestAMessage_TestWithPost() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriUtil.getAsUriUsigBase("/messages");
        Message message = new Message("a post message");
        ResponseEntity<Message> responseEntity = restTemplate.postForEntity(uri, message, Message.class);

        //assert results
        Message returnedMessage = responseEntity.getBody();
        assertThat(returnedMessage, is(notNullValue()));
        assertThat(returnedMessage.getId(), is(not(equalTo(0L))));

        URI location = responseEntity.getHeaders().getLocation();
        assertThat(location, is(notNullValue()));
    }

    @Test
    public void testGetMessage() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(UriUtil.getAsUriUsigBase("/populatedb"), Void.class);
        ResponseEntity<Message> responseEntity = restTemplate.getForEntity(UriUtil.BaseURL + "/message/{id}", Message.class, 1L);
        HttpStatus statusCode = responseEntity.getStatusCode();
        assertThat(statusCode, is(equalTo(HttpStatus.OK)));
    }

    @Test
    public void testPut() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(UriUtil.getAsUriUsigBase("/populatedb"), Void.class);
        final String editedMessageContent = "EditedMessage";
        Message newMessage = new Message(editedMessageContent);
        String url = UriUtil.BaseURL + "/message/1";
        restTemplate.put(url, newMessage);
        ResponseEntity<Message> returnedMessage = restTemplate.getForEntity(url, Message.class);
        assertThat(returnedMessage.getBody().getContent(), is(equalTo(editedMessageContent)));
    }

    @Test
    public void deleteMessage() {
        RestTemplate restTemplate = new RestTemplate();
        Message postMessage = new Message("a message to delete");
        ResponseEntity<Message> postResponseEntity = restTemplate.postForEntity(UriUtil.getAsUriUsigBase("/messages"), postMessage, Message.class);
        assertThat(postResponseEntity.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
        restTemplate.delete(UriUtil.BaseURL + "/message/{id}", 1);
        ResponseEntity<List> all = restTemplate.getForEntity(UriUtil.getAsUriUsigBase("/messages"), List.class);
        assertThat(all.getBody().size(), equalTo(0));
    }

}