package com.artem.telegram.server;

import com.artem.telegram.core.Message;
import com.artem.telegram.core.Request;
import com.artem.telegram.core.Response;
import com.artem.telegram.core.ResponseMessage;
import com.artem.telegram.server.repository.SimpleUserDao;
import com.artem.telegram.server.repository.User;
import com.artem.telegram.server.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Обработка гет пост запросов в отдельном потоке
 *
 * @author artem
 */
class ClientTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(ClientTask.class);

    private HttpService httpService;
    private UserDao userDao = SimpleUserDao.getInstance();

    ClientTask(Socket socket) throws IOException {
        httpService = new HttpService(socket);
    }

    @Override
    public void run() {
        try {
            // ждем пока не придет запрос на аутентификацию
            Request request = httpService.get();
            while (!request.isAuthenticationRequest()) {
                logger.info("{} is not authentication request. Authenticate user first.", request.getRequestMethod());
            }
            // пришел запрос на авторизацию, достаем имя и авторизуем
            String username = (String) request.getBody();
            User user = authorizeUser(username);
            // меняем состояние юзера
            user.setConnected(true);
            // отправляем ответ
            simpleResponse(ResponseMessage.OK);
            // пока юзер приконнекчен, принимаем запросы на сообщения и на их отправку
            while (user.isConnected()) {
                Request userRequest = httpService.get();
                // если снова пришел запрос на аутентификацию, не обрабатываем его
                if (userRequest.isAuthenticationRequest()) {
                    continue;
                }
                if (userRequest.isGetRequest()) {
                    // get request интерпретируется как запрос на новые сообщения
                    List<Message> messages = user.getUnreadMessages();
                    Response<List<Message>> messagesResponse = new Response<>(messages);
                    httpService.send(messagesResponse);
                    user.clearMessagesHistory();
                } else if (userRequest.isPostRequest()) {
                    // post request интерпретируется как отправка сообщения на сервер
                    Message message = (Message) userRequest.getBody();
                    String recipientName = message.getRecipient();
                    User recipient = userDao.findUserByUsername(recipientName);
                    if (recipient == null) {
                        simpleResponse(ResponseMessage.USER_NOT_FOUND);
                        continue;
                    }
                    recipient.addUnreadMessage(message);
                    simpleResponse(ResponseMessage.OK);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error occurred with the next message:\n{}", e.getMessage());
        }
    }

    /**
     * Авторизует юзера по его имени
     *
     * @param username имя юзера
     * @return объект User
     */
    private User authorizeUser(String username) {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            // если не нашли такого юзера в дао, нужно его завести и сохранить
            user = new User(username);
            userDao.saveUser(user);
        }
        return user;
    }

    /**
     * Простой ответ OK, FAILED или USER_NOT_FOUND
     *
     * @param responseMessage OK, FAILED или USER_NOT_FOUND
     */
    private void simpleResponse(ResponseMessage responseMessage) throws IOException {
        Response<ResponseMessage> response = new Response<>(responseMessage);
        httpService.send(response);
    }

    /**
     * Закрывает все конекшны
     */
    public void destroy() throws IOException {
        httpService.disconnect();
    }

}
