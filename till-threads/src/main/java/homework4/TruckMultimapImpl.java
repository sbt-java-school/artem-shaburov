package homework4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by art
 */
public class TruckMultimapImpl implements TruckMultimap {
    private Map<Truck.Type, List<Truck>> truckRegistryByType = new HashMap<>();

    public TruckMultimapImpl() {
    }

    @Override
    public List<Truck> get(Truck.Type type) {
        if (type == null) {
            throw new NullPointerException("type can't be null");
        }
        List<Truck> trucks = truckRegistryByType.get(type);
        if (trucks == null) {
            throw new NullPointerException("put something to the map before get it");
        }
        return trucks;
    }

    @Override
    public void put(Truck.Type type, Truck truck) {
        if (type == null) {
            throw new NullPointerException("type can't be null");
        }
        if (truck == null) {
            throw new NullPointerException("truck can't be null");
        }
        if (!truck.getType().equals(type)) {
            throw new IllegalArgumentException("type of truck and type given in the parameter must be the same");
        }
        List<Truck> trucks = truckRegistryByType.get(type);
        if (trucks == null) {
            trucks = new ArrayList<>();
        }
        trucks.add(truck);
        truckRegistryByType.put(type, trucks);
    }

    public Map<Truck.Type, List<Truck>> getTruckRegistryByType() {
        return truckRegistryByType;
    }
}
