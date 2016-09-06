package homework6;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by art
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // from - объект с геттерами
        From from = new From();
        from.setIntField(10);
        from.setStringField("some string");
        from.setLoaded(true);

        // to - объект с сеттерами
        To to = new To();

        System.out.println("1. BeanUtils Realization");
        System.out.println("Before " + to);
        BeanUtils.assign(to, from);
        System.out.println("After " + to);
        System.out.println();

        System.out.println("2. Printing all methods for class " + from.getClass().getName() + " and it's superclass");
        ReflectionUtils.printAllMethodsForClassAndSuperclass(from);
        System.out.println();

        Object object = new ArrayList<>();
        System.out.println("3. All getters for class " + object.getClass().getName());
        ReflectionUtils.printGetters(object);
        System.out.println();

        System.out.println("4. Checking constants with names equaled to their values  " + Constants.class.getName());
        ReflectionUtils.checkStringConstantsNames(Constants.class);
        System.out.println();
    }
}
