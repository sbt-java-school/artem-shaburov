package com.artem.telegram.server;

import com.artem.telegram.core.Request;
import com.artem.telegram.core.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author artem
 */
public class HttpService {

    /**
     * Сокет, через который получаем стримы
     */
    private Socket socket;

    /**
     * Входящий поток объектов
     */
    private ObjectInputStream ois;

    /**
     * Исходящий поток объектов
     */
    private ObjectOutputStream oos;

    /**
     * Через конструктор передаем socket
     *
     * @param socket сокет, нельзя передавать null
     */
    HttpService(Socket socket) throws IOException {
        if (socket == null) {
            throw new NullPointerException("Socket can't be null.");
        }
        this.socket = socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Посылает объект серверу
     *
     * @param response посылаемый объект
     */
    void send(Response response) throws IOException {
        // вызываем последовательно write и flush
        oos.writeObject(response);
        oos.flush();
    }

    /**
     * Принимает ответ от сервера
     *
     * @return объект респонс от сервера
     */
    Request get() throws IOException, ClassNotFoundException {
        @SuppressWarnings("unchecked")
        Request request = (Request) ois.readObject();
        return request;
    }

    /**
     * Закрывает все соединения
     */
    void disconnect() throws IOException {
        socket.close();
        ois.close();
        oos.close();
    }

}
