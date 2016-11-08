package homework8;

/**
 * Created by art
 */
public interface Calculator {
    @Cache
    Integer plus(int a, int b);
    int minus(int a, int b);
}
