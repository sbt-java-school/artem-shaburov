package ru.sbt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbt.cache.Cache;
import ru.sbt.dao.CacheDao;
import ru.sbt.dao.CacheDaoJdbcImpl;
import ru.sbt.entities.CacheModel;
import ru.sbt.entities.Operation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 *
 */
public class CacheProxy implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(CacheProxy.class);
    private Object delegate;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(
            "jdbc:h2:~/");
    private CacheDao cacheDao = new CacheDaoJdbcImpl(jdbcTemplate);

    public CacheProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (delegate == null) {
            throw new NullPointerException("delegate can't be null");
        }
        if (method.isAnnotationPresent(Cache.class)) {
            Operation operation = new Operation(delegate, method, args);
            Optional<CacheModel> cachedResult = cacheDao.findByKey(operation);
            if (cachedResult.isPresent()) {
                logger.info("result from db: {}", cachedResult);
                return cachedResult.get();
            }
            Object result = method.invoke(delegate, args);
            logger.info("result: {}", result);
            CacheModel cacheModel = new CacheModel(operation, result);
            boolean created = cacheDao.create(cacheModel);
            logger.info("db updated: {}", created);
            return result;
        }
        return method.invoke(delegate, args);
    }
}
