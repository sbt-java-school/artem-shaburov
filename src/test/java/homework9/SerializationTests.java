package homework9;

import homework4.Truck;
import org.junit.Test;

import java.io.*;

/**
 * @author artem
 */
public class SerializationTests {
    private static final String SERIALIZATION_FILE_NAME = "src/main/resources/data.ser";

    @Test
    public void writeTruck() {
        Truck truck = new Truck(100, Truck.Type.VOLVO, 1000);
        writeObject(truck);
    }

    @Test
    public void readTruck() {
        Object object = readObject();
        if (object instanceof SerializationError) {
            SerializationError serializationError = (SerializationError) object;
            System.out.println(serializationError.getMessage());
            return;
        }
        Truck truck = (Truck) object;
        System.out.println(truck);
    }

    @Test
    public void singletonTest() {
        // write
        SerializationSingleton serializationSingleton = SerializationSingleton.getInstance();
        serializationSingleton.setFirstName("Alan");
        writeObject(serializationSingleton);

        // read
        Object object = readObject();
        if (object instanceof SerializationError) {
            SerializationError serializationError = (SerializationError) object;
            System.out.println(serializationError.getMessage());
            return;
        }
        SerializationSingleton serializedSingleton = (SerializationSingleton) object;
        System.out.println(serializedSingleton);
    }

    private void writeObject(Object object) {
        try (FileOutputStream fos = new FileOutputStream(SERIALIZATION_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readObject() {
        try (FileInputStream fis = new FileInputStream(SERIALIZATION_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new SerializationError();
    }
}

