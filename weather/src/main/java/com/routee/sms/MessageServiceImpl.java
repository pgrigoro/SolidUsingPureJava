package com.route.sms;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static java.lang.String.format;

public class MessageServiceImpl implements MessageService {

    private static final String API_URI = "https://connect.route.net/sms";
    private static final String AUTH_URI = "https://auth.route.net/oauth/token";
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    private final String accessToken;
    private final OkHttpClient client;

    public MessageServiceImpl(String accessToken){
        this.client = new OkHttpClient();
        this.authenticate(accessToken);
        this.accessToken = accessToken;
    }

    private void authenticate(String accessToken) {
        MediaType mediaType = MediaType.parse(APPLICATION_X_WWW_FORM_URLENCODED);
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
        Request request = new Request.Builder()
                .url(AUTH_URI)
                .post(body)
                .addHeader("authorization", "Basic " + accessToken)
                .addHeader("content-type", APPLICATION_X_WWW_FORM_URLENCODED)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new SmsException(format("Authentication error: %s", response.body().string()));
            }

        } catch (IOException e) {
            throw new SmsException("Authentication error", e);
        }

    }

    @Override
    public Response send(Message message) {
        MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        RequestBody body = RequestBody.create(mediaType,
                "{ \"body\": \"" + message.getText() + "\",\"to\" : \"" + message.getPhone() + "\",\"from\": \"amdTelecom\"}");
        Request request = new Request.Builder()
                .url(API_URI)
                .post(body)
                .addHeader("authorization", "Bearer " + accessToken)
                .addHeader("content-type", APPLICATION_JSON)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new SmsException("Error while sending SMS to phone " + message.getPhone(), e);
        }

        return response;
    }

}
