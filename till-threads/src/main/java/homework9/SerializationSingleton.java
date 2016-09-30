package homework9;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * {@link homework4.Truck} добавлены методы readObject, writeObject, writeReplace
 *
 * @author artem
 */
public class SerializationSingleton implements Serializable {
    private static final long serialVersionUID = -4867263820291257779L;
    private static SerializationSingleton instance;
    private String firstName;
    transient private int age;

    /**
     * Synchronized lazy singleton initialization
     *
     * @return singleton instance
     */
    public static SerializationSingleton getInstance() {
        SerializationSingleton localInstance = instance;
        if (localInstance == null) {
            synchronized (SerializationSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SerializationSingleton();
                }
            }
        }
        return localInstance;
    }

    private SerializationSingleton() {
    }

    /**
     * возвращаем замещающий объект вместо текущего
     */
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "SerializationSingleton{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
