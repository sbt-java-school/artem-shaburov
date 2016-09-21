package homework4;

import java.io.*;

/**
 * Created by art
 */
public class Truck implements Serializable {
    private static final long serialVersionUID = 4893084921904046779L;

    private long id;
    private Type type;
    private int capacity;

    public enum Type {
        MAN, VOLVO, KAMAZ
    }

    public Truck(long id, Type type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (id != truck.id) return false;
        if (capacity != truck.capacity) return false;
        return type == truck.type;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", type=" + type +
                ", capacity=" + capacity +
                '}';
    }

    /* homework 10 */
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        id = inputStream.readLong();
        type = (Type) inputStream.readObject();
        capacity = inputStream.readInt();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.writeLong(id);
        outputStream.writeObject(type);
        outputStream.writeInt(capacity);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new TruckProxy(this);
    }
}

class TruckProxy implements Serializable {
    private static final long serialVersionUID = 9111838873960553836L;
    private String data;
    transient private static final String DELIMITER = ", ";

    public TruckProxy(Truck truck) {
        data = truck.getId() + DELIMITER + truck.getType() + DELIMITER + truck.getCapacity();
        System.out.println(data);
    }

    private Object readResolve() {
        String[] pieces = data.split(DELIMITER); // когда встречаем последовательные два символа зпт + пробел
        if (pieces.length != 3) {
            throw new IllegalArgumentException("data not valid");
        }
        // если неправильный аргумент передать в парсинг инума, то будет illegalArgument, с интами и лонгами numberFormat
        Truck truck = new Truck(Long.parseLong(pieces[0]), Truck.Type.valueOf(pieces[1]), Integer.parseInt(pieces[2]));
        return truck;
    }
}
