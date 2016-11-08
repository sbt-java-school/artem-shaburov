package homework8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by art
 */
public class CacheProxy implements InvocationHandler {
    // в мапе ключом является CacheKey - объект, хранящий в себе метод + аргументы метода
    private Map<CacheKey, Object> cachedResults = new HashMap<>();
    // object, на котором вызывается invoke
    private Object object;

    public CacheProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (object == null) {
            throw new NullPointerException("object can't be null");
        }
        Method classMethod = object.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        CacheKey cacheKey = null;
        if (method.isAnnotationPresent(Cache.class)) {
            cacheKey = new CacheKey(method, args);
        } else if (classMethod.isAnnotationPresent(Cache.class)) {
            cacheKey = new CacheKey(classMethod, args);
        }
        if (cacheKey != null) {
            if (!cachedResults.containsKey(cacheKey)) {
                cachedResults.put(cacheKey, method.invoke(object, args));
            }
            return cachedResults.get(cacheKey);
        }
        return method.invoke(object, args);
    }

    // класс-помощник для упаковки метода и аргумента в один объект, который будет являться ключом к нашей мапе
    private class CacheKey {
        private Method method;
        private Object[] args;

        private CacheKey(Method method, Object[] args) {
            this.method = method;
            this.args = args;
        }

        // для правильной работы мапы с элементами нашего класса, переопределяем hashCode() и equals()
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            CacheKey cacheKey = (CacheKey) object;

            if (!method.equals(cacheKey.method)) return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(args, cacheKey.args);
        }

        @Override
        public int hashCode() {
            int result = method.hashCode();
            result = 31 * result + Arrays.hashCode(args);
            return result;
        }
    }
}
