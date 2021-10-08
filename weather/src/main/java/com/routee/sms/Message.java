package com.route.sms;

public class Message {

    private final String phone;
    private final String text;

    private Message(MessageRequestBuilder builder) {
        this.phone = builder.phone;
        this.text = builder.text;
    }

    public String getPhone() {
        return phone;
    }

    public String getText() {
        return text;
    }

    public static MessageRequestBuilder builder() {
        return new MessageRequestBuilder();
    }

    public static final class MessageRequestBuilder {
        private String phone;
        private String text;

        public MessageRequestBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public MessageRequestBuilder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}