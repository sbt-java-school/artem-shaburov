package homework4;

import java.util.List;

/**
 * Created by art
 */
public interface TruckMultimap {
    List<Truck> get(Truck.Type type);
    void put(Truck.Type type, Truck truck);
}
