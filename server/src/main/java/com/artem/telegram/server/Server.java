package com.artem.telegram.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Простой сервер
 *
 * @author artem
 */
public class Server {

    // Для логов
    private Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * Наибольшее количество одновременно выполняемых задач
     */
    private static final int MAX_NUMBER_OF_THREADS = 20;

    /**
     * Трэд пул экзекьютор с ограничением на 20 конекшнов
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUMBER_OF_THREADS);

    /**
     * Порт, на котором крутится наше приложение
     */
    private int port;

    /**
     * Конструктор принимает порт
     *
     * @param port порт, на котором крутится наше приложение
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * Метод Server.start(); запускает сервер. Сервер начинает слушать соединения
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // пытаемся соединить клиента
                try {
                    Socket client = serverSocket.accept();
                    ClientTask clientTask = new ClientTask(client);
                    executorService.submit(clientTask);
                } catch (IOException e) {
                    logger.error("Error occurred when trying to connect a client. Message\n{}", e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Error occurred when trying to open the socket. Message\n{}", e.getMessage());
        }
    }

}
