package homework8;

/**
 * Тестовая реализация интерфейса Calculator
 */
class CalculatorImpl implements Calculator {
    @Cache
    @Override
    public Integer plus(int a, int b) {
        return a + b;
    }

    @Override
    public Integer minus(int a, int b) {
        return a - b;
    }
}
