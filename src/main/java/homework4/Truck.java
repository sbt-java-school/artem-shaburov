package homework4;

/**
 * Created by art
 */
public class Truck {
    enum Type {
        MAN, VOLVO, KAMAZ
    }

    private long id;
    private Type type;
    private int capacity;

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
}
