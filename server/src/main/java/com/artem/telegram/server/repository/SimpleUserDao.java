package com.artem.telegram.server.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * UserDao, которая работает с листом. Данные в нашем приложении являются постоянными
 * только от запуска сервера, до его отключения. На это время здесь хранится список всех юзеров.
 *
 * @author artem
 */
public class SimpleUserDao implements UserDao {

    /**
     * Список пользователей на сервере, онлайн или оффлайн
     */
    private List<User> users = new ArrayList<>();

    /**
     * Единственная для дао сущность
     */
    private static SimpleUserDao instance;

    /**
     * Метод для получения сущности, lazy synchronized singleton
     *
     * @return сущность этого класса
     */
    public static SimpleUserDao getInstance() {
        SimpleUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (SimpleUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SimpleUserDao();
                }
            }
        }
        return localInstance;
    }

    /**
     * Приватный конструктор, чтобы у нас была только одна входная точка в класс
     */
    private SimpleUserDao() {
    }

    /**
     * @param username имя пользователя
     * @return объект User или null
     */
    @Override
    public User findUserByUsername(String username) {
        if (username == null) {
            throw new NullPointerException("Username can't be null");
        }
        boolean userExists = userExists(username);
        if (userExists) {
            User user = users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .collect(Collectors.toList())
                    .get(0);
            return user;
        }
        return null;
    }

    /**
     * @param user объект User, который требуется добавить в дао
     */
    @Override
    public void saveUser(User user) {
        if (user == null) {
            throw new NullPointerException("User can't be null");
        }
        // чтобы при вызове этого метода в листе не оказалось двух одинаковых юзеров, сначала проверяем нет ли уже такого
        boolean userExists = userExists(user.getUsername());
        if (!userExists) {
            users.add(user);
        }
    }

    /**
     * Проверяет, существует ли юзер в списке
     * @param username имя пользователя
     * @return true, если юзер есть в списке
     */
    private boolean userExists(String username) {
        Predicate<User> predicate = u -> u.getUsername().equals(username);
        return users.stream()
                .anyMatch(predicate);
    }

}
