package com.artem.telegram.client;

import com.artem.telegram.client.exceptions.ConnectionException;
import com.artem.telegram.client.exceptions.UserNotFoundException;
import com.artem.telegram.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Клиент, с которым производится основная работа. Только он и его методы торчат наружу.
 *
 * @author artem
 */
public class MessengerClient implements Messenger {

    // Для логов
    private Logger logger = LoggerFactory.getLogger(MessengerClient.class);

    /**
     * Состояние подключения
     */
    private boolean connected;

    /**
     * Сервис, через который посылаются все запросы и принимаются ответы
     */
    private HttpService httpService;

    /**
     * Хост, к которому совершается попытка соединения
     */
    private String host;

    /**
     * Порт на данном хосте, к которому совершается попытка соединения
     */
    private int port;

    /**
     * Конструктору передается порт и хост
     *
     * @param host хост
     * @param port порт
     */
    public MessengerClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * @param username имя пользователя
     */
    public void connectUser(String username) throws IOException {
        // создаем соединение
        try {
            // не создаем как автоклоузэбл, т.к. дальше будем работать с этим сокетом
            Socket socket = new Socket(host, port);
            httpService = new HttpService(socket);
            // запрос на аутентификацию
            Request request = new Request(RequestMethod.AUTH, username);
            httpService.send(request);
            Response<ResponseMessage> response = httpService.get();
            if (response.getBody().equals(ResponseMessage.OK)) {
                // меняем состояние соединения
                connected = true;
            }
        } catch (ClassNotFoundException e) {
            logger.error("No such class found. Message:\n{}", e.getMessage());
        }
    }

    /**
     * @param query число сообщений, которые хотим получить
     * @return непрочитанные сообщения
     */
    public List<Message> getNewMessages(String query) throws IOException, ConnectionException {
        List<Message> messages = new ArrayList<>();
        if (!connected) {
            throw new ConnectionException("Try to connect first.");
        }
        Request request = new Request(RequestMethod.GET, query);
        httpService.send(request);
        try {
            Response<List<Message>> response = httpService.get();
            List<Message> responseMessages = response.getBody();
            if (responseMessages != null) {
                return responseMessages;
            }
        } catch (ClassNotFoundException e) {
            logger.error("No such class found. Message:\n{}", e.getMessage());
        }
        return messages;
    }

    /**
     * @param recipient получатель сообщения
     * @param text      сообщение
     */
    public void sendMessage(String recipient, String text) throws IOException, ConnectionException, UserNotFoundException {
        if (!connected) {
            throw new ConnectionException("Try to connect first.");
        }
        Message message = new Message(recipient, text);
        Request request = new Request(RequestMethod.POST, message);
        httpService.send(request);
        try {
            // если пришел ответ USER_NOT_FOUND, бросаем эксэпшн выше
            Response<ResponseMessage> response = httpService.get();
            if (response.getBody().equals(ResponseMessage.USER_NOT_FOUND)) {
                throw new UserNotFoundException("No such recipient presented on the server at the moment.");
            }
        } catch (ClassNotFoundException e) {
            logger.error("No such class found. Message:\n{}", e.getMessage());
        }
    }

    /**
     * Закрытие соединения
     */
    public void disconnect() throws IOException {
        connected = false;
        httpService.disconnect();
    }

}
