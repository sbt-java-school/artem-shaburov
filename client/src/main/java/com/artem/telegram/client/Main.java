package com.artem.telegram.client;

import com.artem.telegram.client.exceptions.ConnectionException;
import com.artem.telegram.client.exceptions.UserNotFoundException;
import com.artem.telegram.core.Message;

import java.io.IOException;
import java.util.List;

/**
 * @author artem
 */
public class Main {

    public static void main(String[] args) {
        Messenger messenger = new MessengerClient("localhost", 8080);
        try {
            messenger.connectUser("artem");
            List<Message> newMessages = messenger.getNewMessages("all");
            for (Message newMessage : newMessages) {
                System.out.println(newMessage);
            }
            messenger.sendMessage("somebody", "hi!");
            messenger.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ConnectionException e) {
            System.out.println("Может сначала законнектиться?");
        } catch (UserNotFoundException e) {
            System.out.println("Юзера, которому ты пытаешься отправить сообщение, не найдено.");
        }
    }

}
