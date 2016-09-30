package homework4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by art
 */
public class GenericMultimapImpl<K, V> implements GenericMultimap<K, V> {
    private Map<K, List<V>> multimap = new HashMap<>();

    public GenericMultimapImpl() {
    }

    @Override
    public List<V> get(K key) {
        if (key == null) {
            throw new NullPointerException("key can't be null");
        }
        List<V> values = multimap.get(key);
        if (values == null) {
            throw new NullPointerException("no values presented by given key. put something to the map first");
        }
        return values;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key can't be null");
        }
        if (value == null) {
            throw new NullPointerException("value can't be null");
        }
        List<V> values = multimap.get(key);
        if (values == null) {
            values = new ArrayList<>();
        }
        values.add(value);
        multimap.put(key, values);
    }

    public Map<K, List<V>> getMultimap() {
        return multimap;
    }
}
