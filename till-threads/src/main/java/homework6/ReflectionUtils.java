package homework6;

import homework5.ExceptionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by art
 */
public class ReflectionUtils {
    public static final String GETTER_PREFIX = "get";
    public static final String BOOLEAN_GETTER_PREFIX = "is";
    public static final String SETTER_PREFIX = "set";

    public static void printAllMethodsForClassAndSuperclass(Object object) {
        Class aClass = object.getClass();
        printDeclaredMethods(aClass);
        Class superclass = aClass.getSuperclass();
        printDeclaredMethods(superclass);
    }

    public static void printDeclaredMethods(Class aClass) {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    // выводим все геттеры
    public static void printGetters(Object object) {
        Class<?> aClass = object.getClass();
        printGetters(aClass);
    }

    public static void printGetters(Class aClass) {
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                printMethodInfo(method);
            }
        }
    }

    /**
     * Check is that method getter or not
     * @param method Method we want to check
     * @return Method that starts with get or is (for booleans), without parameters
     * and with return type not equals to Void will return true
     * otherwise - false
     */
    public static boolean isGetter(Method method) {
        String name = method.getName();
        return (name.startsWith(GETTER_PREFIX) || name.startsWith(BOOLEAN_GETTER_PREFIX))
                && method.getParameters().length == 0
                && !method.getReturnType().equals(void.class);
    }

    /**
     * Check is that method setter or not
     * @param method Method we want to check
     * @return Method that starts with set, with 1 parameter
     * and with return type Void will return true
     * otherwise - false
     */
    public static boolean isSetter(Method method) {
        return method.getName().startsWith(SETTER_PREFIX)
                && method.getParameters().length == 1
                && method.getReturnType().equals(void.class);
    }

    public static void printMethodInfo(Method method) {
        System.out.println(method.getName());
    }

    // проверяем константы
    public static void checkStringConstantsNames(Class aClass) {
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // проверяем строки ли это
            if (declaredField.getType().equals(String.class)) {
                // для приватных филдов ставим setAccessible в true
                declaredField.setAccessible(true);
                try {
                    // http://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Field.html#get%28java.lang.Object%29
                    // "If the underlying field is a static field, the obj argument is ignored; it may be null."
                    if (declaredField.getName().equals(declaredField.get(null))) {
                        System.out.println("Field with name " + declaredField.getName() + " has value "  + declaredField.get(null));
                    }
                } catch (IllegalAccessException e) {
                    ExceptionUtils.printExceptionMessage(e);
                }
            }
        }
    }
}
