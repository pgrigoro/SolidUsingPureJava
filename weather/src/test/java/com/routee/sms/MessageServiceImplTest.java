package com.route.sms;

import okhttp3.Response;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * MessageServiceImpl Tester.
 */
public class MessageServiceImplTest {

    private static final String API_TOKEN = "NTc1NmE0MTFlNGIwNmEzM2Q1MDUxN2M3OnZiNlFwakNJT0c=";

    @Test
    @Ignore
    public void testAuthenticate() {
        MessageService service = new MessageServiceImpl(API_TOKEN);
        Assert.assertNotNull(service);
    }

    @Test
    @Ignore
    public void testSend() {
        Message message = Message.builder()
                .text("test message")
                .phone("+306940827881")
                .build();

        MessageService service = new MessageServiceImpl(API_TOKEN);
        Response response = service.send(message);
        Assert.assertEquals(true, response.isSuccessful());
    }

} 
