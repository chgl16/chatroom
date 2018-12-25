package xyz.cglzwz.chatroom.domain;

import org.springframework.stereotype.Component;

@Component
public class Message {
    private String text;
    private String receiver;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
