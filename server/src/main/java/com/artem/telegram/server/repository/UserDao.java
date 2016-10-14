package com.artem.telegram.server.repository;

/**
 * @author artem
 */
public interface UserDao {

    /**
     * Находит пользователя по имени
     *
     * @param username имя пользователя
     * @return объект User или null, если такой пользователь не найден
     */
    User findUserByUsername(String username);

    /**
     * Сохраняет пользователя в дао
     *
     * @param user объект User, который требуется добавить в дао
     */
    void saveUser(User user);

}
