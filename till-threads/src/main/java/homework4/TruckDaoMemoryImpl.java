package homework4;

import java.util.Arrays;
import java.util.List;

/**
 * Created by art
 */
public class TruckDaoMemoryImpl implements TruckDao {
    @Override
    public List<Truck> list() {
        return Arrays.asList(
                new Truck(5, Truck.Type.VOLVO, 105),
                new Truck(1, Truck.Type.KAMAZ, 110),
                new Truck(2, Truck.Type.MAN, 105),
                new Truck(3, Truck.Type.MAN, 100),
                new Truck(8, Truck.Type.KAMAZ, 110),
                new Truck(17, Truck.Type.VOLVO, 100)
        );
    }
}
