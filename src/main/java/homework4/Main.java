package homework4;

import java.io.*;
import java.util.*;

/**
 * Дз 4 - Мультимапы, Дженерики
 * Created by art
 */
public class Main {
    private Map<Long, Truck> truckRegistry = new TreeMap<>();

    public Main(TruckDao truckDao) {
        List<Truck> list = truckDao.list();
        for (Truck truck : list) {
            Truck previousValue = truckRegistry.put(truck.getId(), truck);
            if (previousValue != null) {
                throw new IllegalArgumentException("two trucks have the same Id");
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "data.ser";

        Truck truck = new Truck(10, Truck.Type.KAMAZ, 100);
        serialize(fileName, truck);
        System.out.println(truck);

        Truck truck2 = deserialize(fileName);
        System.out.println(truck2);

//        // старый код
//        TruckDao truckDao = new TruckDaoMemoryImpl();
//        Main application = new Main(truckDao);
//        application.viewTruckRegistry();
//
//        // 1. просто мультимап с известными типами
//        TruckMultimap truckMultimap = new TruckMultimapImpl();
//        truckMultimap.put(Truck.Type.KAMAZ, new Truck(1, Truck.Type.KAMAZ, 100));
//        truckMultimap.put(Truck.Type.VOLVO, new Truck(2, Truck.Type.VOLVO, 100));
//        List<Truck> kamazList = truckMultimap.get(Truck.Type.KAMAZ);
//        System.out.println(kamazList);
//        Map<Truck.Type, List<Truck>> truckRegistryByType = ((TruckMultimapImpl) truckMultimap).getTruckRegistryByType();
//        System.out.println(truckRegistryByType);
//
//        // 2. мультимап с дженериками
//        GenericMultimap<Truck.Type, Truck> genericMultimap = new GenericMultimapImpl<>();
//        genericMultimap.put(Truck.Type.VOLVO, new Truck(1, Truck.Type.VOLVO, 100));
//        genericMultimap.put(Truck.Type.KAMAZ, new Truck(1, Truck.Type.KAMAZ, 100));
//        List<Truck> volvoList = genericMultimap.get(Truck.Type.VOLVO);
//        System.out.println(volvoList);
//        Map multimap = ((GenericMultimapImpl) genericMultimap).getMultimap();
//        System.out.println(multimap);
    }

    private static <T> T deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        ois.close();
        return  (T) object;
    }

    private static void serialize(String fileName, Object object) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
    }

    private void viewTruckRegistry() {
        for (Long truckId : truckRegistry.keySet()) {
            System.out.println(truckId);
        }
        System.out.println(truckRegistry);
    }

    private Truck getTruckById(long truckId) {
        Truck truck = truckRegistry.get(truckId);
        if (truck == null) {
            throw new IllegalArgumentException("truck with id = " + truckId + " not found");
        }
        return truck;
    }
}
