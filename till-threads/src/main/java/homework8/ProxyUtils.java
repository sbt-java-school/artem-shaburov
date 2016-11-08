package homework8;

import java.lang.reflect.Proxy;

/**
 * Created by art
 */
public class ProxyUtils {
    public static <T> T makeCached(T object) {
        Class objectClass = object.getClass();
        Object proxyInstance = Proxy.newProxyInstance(
                objectClass.getClassLoader(),
                objectClass.getInterfaces(),
                new CacheProxy(object)
        );
        @SuppressWarnings("unchecked") T tProxyInstance = (T) proxyInstance;
        return tProxyInstance;
    }
}
