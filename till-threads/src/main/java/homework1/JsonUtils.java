package homework1;

import org.json.JSONObject;

/**
 * Класс-помощоник для работы с JSONObject
 * @author artem
 * @version 1.0
 */
public class JsonUtils {
    /**
     * Pretty print for JSONObject
     * @param json you want to print
     */
    public static void prettyPrint(JSONObject json) {
        System.out.println(json.toString(4)); // выводим с отступом 4
    }
}
