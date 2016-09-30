package homework1;

import org.json.JSONObject;

/**
 * Первое дз - джавадоки
 * @author artem
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        JSONObject json = new JSONObject("{product:{name:potato}}");
        JsonUtils.prettyPrint(json);
    }
}
