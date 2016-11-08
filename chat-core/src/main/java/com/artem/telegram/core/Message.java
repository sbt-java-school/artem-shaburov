package com.artem.telegram.core;

import java.io.Serializable;

/**
 * @author artem
 */
public class Message implements Serializable {

    private static final String MESSAGE_TEMPLATE = "%s [from %s]";

    private String recipient;
    private String text;

    public Message(String recipient, String text) {
        this.recipient = recipient;
        this.text = text;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format(MESSAGE_TEMPLATE, text, recipient);
    }

}
