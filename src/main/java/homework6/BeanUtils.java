package homework6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by art
 * Задание 6
 * Реализовать следующий класс по документации
 */
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        Class fromClass = from.getClass();
        Method[] fromMethods = fromClass.getDeclaredMethods();
        Class toClass = to.getClass();
        Method[] toMethods = toClass.getDeclaredMethods();

        for (Method fromMethod : fromMethods) {
            // если не геттер, идем дальше
            if (!ReflectionUtils.isGetter(fromMethod)) {
                continue;
            }
            for (Method toMethod : toMethods) {
                // если не сеттер, идем дальше
                if (!ReflectionUtils.isSetter(toMethod)) {
                    continue;
                }
                // сравниваем имена методов
                String getterName = fromMethod.getName();
                String setterName = toMethod.getName();

                // проверяем начинается ли метод с get, так как булин геттеры начинаются с is и режем имя
                getterName = getterName.startsWith(ReflectionUtils.GETTER_PREFIX) ?
                        getterName.substring(3) : getterName.substring(2);
                // также режем у сеттера
                setterName = setterName.substring(3);

                // и сравниваем
                if (!getterName.equals(setterName)) {
                    continue;
                }
                // и проверяем совпадают ли типы у передаваемого значения сеттера и возвращаемого значения геттера
                Class<?> getterReturnType = fromMethod.getReturnType();
                Class<?> getterReturnTypeSuperclass = getterReturnType.getSuperclass();
                // isSetter проверяет, чтобы у метода был ровно один параметр, поэтому берем тут 0 индекс
                Class<?> setterParameterType = toMethod.getParameterTypes()[0];
                // и если все совпадает, то инвокаем геттер и сеттер со значением геттера
                if (getterReturnType.equals(setterParameterType)
                        || (getterReturnTypeSuperclass != null && getterReturnTypeSuperclass.equals(setterParameterType))) {
                    Object getterResult = fromMethod.invoke(from);
                    toMethod.invoke(to, getterResult);
                    break;
                }
            }
        }
    }
}
