package ru.sbt.cb.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class CrudJpaDao<T, PK> implements CrudDao<T, PK> {
    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> entityClass;

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudJpaDao.class);

    public CrudJpaDao() {
        Type genericSuperclass;
        Class<?> parametrizedClass = getClass();
        do {
            genericSuperclass = parametrizedClass.getGenericSuperclass();
            if (genericSuperclass instanceof Class) {
                parametrizedClass = (Class<?>) genericSuperclass;
            }
        } while (genericSuperclass != null && !(genericSuperclass instanceof ParameterizedType));
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        if (type != null && type.getActualTypeArguments().length > 0) {
            @SuppressWarnings("unchecked") Class<T> entityClass = (Class<T>) type.getActualTypeArguments()[0];
            this.entityClass = entityClass;
            LOGGER.info("Dao class: {}", this.entityClass);
        }
    }

    @Override
    public T create(T t) {
        if (t == null) {
            throw new NullPointerException("Entity can't be null.");
        }
        entityManager.persist(t);
        return t;
    }

    @Override
    public T get(PK id) {
        if (id == null) {
            throw new NullPointerException("Id can't be null.");
        }
        return entityManager.find(entityClass, id);
    }

    public List<T> list() {
        return entityManager.createQuery(String.format("select entity from %s entity", entityClass.getSimpleName()),
                entityClass)
                .getResultList();
    }

    @Override
    public T update(T t) {
        if (t == null) {
            throw new NullPointerException("Entity can't be null.");
        }
        return entityManager.merge(t);
    }

    @Override
    public void delete(PK id) {
        if (id == null) {
            throw new NullPointerException("Id can't be null.");
        }
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }
}
