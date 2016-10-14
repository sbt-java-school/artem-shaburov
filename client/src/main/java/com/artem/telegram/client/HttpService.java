package com.artem.telegram.client;

import com.artem.telegram.core.Request;
import com.artem.telegram.core.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Класс, ответственный за пересылку данных по трубе от клиента к серверу.
 * После удачного получения сокета, передаем его сюда и здесь получаем ин и аут стримы.
 * Два основных метода класса: send(object) и get() возвращающий какой-то object.
 *
 * @author artem
 */
class HttpService {

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
     * @param request посылаемый объект
     */
    void send(Request request) throws IOException {
        // вызываем последовательно write и flush
        oos.writeObject(request);
        oos.flush();
    }

    /**
     * Принимает ответ от сервера
     *
     * @return объект респонс от сервера
     */
    <T> Response<T> get() throws IOException, ClassNotFoundException {
        @SuppressWarnings("unchecked")
        Response<T> response = (Response<T>) ois.readObject();
        return response;
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
