package homework8;

/**
 * Created by art
 */
public class CalculatorImpl implements Calculator {
    @Override
    public Integer plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
