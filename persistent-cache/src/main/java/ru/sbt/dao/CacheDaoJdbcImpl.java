package ru.sbt.dao;

import ru.sbt.entities.Operation;
import ru.sbt.JdbcTemplate;
import ru.sbt.entities.CacheModel;

import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 *
 */
public class CacheDaoJdbcImpl implements CacheDao {
    private JdbcTemplate jdbcTemplate;

    public CacheDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<CacheModel> findByKey(Operation operation) {
        return jdbcTemplate.execute(connection -> {
            String sql = "select operation, result from cache where operation = ?";

            // проверяем есть ли операция в бд, возвращаем объект cache model, если есть
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos)
            ) {
                oos.writeObject(operation);
                oos.flush();

                InputStream in = new ByteArrayInputStream(baos.toByteArray());
                preparedStatement.setBlob(1, in);

                in.close();

                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                Blob resultBlob = resultSet.getBlob(3);

                try (InputStream resultBlobBinaryStream = resultBlob.getBinaryStream();
                     ObjectInputStream ois = new ObjectInputStream(resultBlobBinaryStream)
                ) {
                    Object resultObject = ois.readObject();
                    CacheModel cacheModel = new CacheModel(operation, resultObject);

                    return Optional.of(cacheModel);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return Optional.empty();
        });
    }

    @Override
    public boolean create(CacheModel cacheModel) {
        return jdbcTemplate.execute(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into cache (operation, result)" +
                            "values (?, ?)");
            Operation operation = cacheModel.getOperation();
            Object result = cacheModel.getResult();

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(baos)
            ) {
                oos.writeObject(operation);
                oos.flush();

                InputStream operationInputStream = new ByteArrayInputStream(baos.toByteArray());
                preparedStatement.setBlob(1, operationInputStream);

                operationInputStream.close();

                oos.writeObject(result);
                oos.flush();

                InputStream resultInputStream = new ByteArrayInputStream(baos.toByteArray());
                resultInputStream.close();

                preparedStatement.setBlob(1, operationInputStream);
                preparedStatement.setBlob(2, resultInputStream);
                int updated = preparedStatement.executeUpdate();

                return updated == 1;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        });
    }
}
