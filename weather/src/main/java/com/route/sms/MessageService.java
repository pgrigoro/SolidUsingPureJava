package com.route.sms;

import okhttp3.Response;

public interface MessageService {

    Response send(Message message);

}
