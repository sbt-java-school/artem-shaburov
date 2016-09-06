package homework8;

/**
 * Created by art
 */
public interface Calculator {
    @Cache
    int plus(int a, int b);
    int minus(int a, int b);
}
