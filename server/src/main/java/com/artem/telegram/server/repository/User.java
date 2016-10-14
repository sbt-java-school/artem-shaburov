package com.artem.telegram.server.repository;

import com.artem.telegram.core.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author artem
 */
public class User {

    private String username;
    private boolean connected;
    private List<Message> unreadMessages = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public void addUnreadMessage(Message message) {
        if (message == null) {
            throw new NullPointerException("Message can't be null");
        }
        unreadMessages.add(message);
    }

    public void clearMessagesHistory() {
        unreadMessages.clear();
    }

    public String getUsername() {
        return username;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public List<Message> getUnreadMessages() {
        return unreadMessages;
    }

}
