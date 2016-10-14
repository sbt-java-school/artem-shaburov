package com.artem.telegram.client;

import com.artem.telegram.client.exceptions.ConnectionException;
import com.artem.telegram.client.exceptions.UserNotFoundException;
import com.artem.telegram.core.Message;

import java.io.IOException;
import java.util.List;

/**
 * Основной интерфейс мессенджера.
 *
 * @author artem
 */
interface Messenger {

    /**
     * Установление соединения с пользователем
     *
     * @param username имя пользователя
     */
    void connectUser(String username) throws IOException;

    /**
     * Получение еще непрочитанных сообщений для присоединенного пользователя
     *
     * @param query число сообщений, которые хотим получить
     * @return непрочитанные сообщения
     */
    List<Message> getNewMessages(String query) throws IOException, ConnectionException;

    /**
     * Отправка сообщения
     *
     * @param recipient получатель сообщения
     * @param text      сообщение
     */
    void sendMessage(String recipient, String text) throws IOException, ConnectionException, UserNotFoundException;

    /**
     * Закрытие соединения
     */
    void disconnect() throws IOException;

}
