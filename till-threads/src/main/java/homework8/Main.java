package homework8;

/**
 * Created by art
 * Реализовать кеширующий прокси
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        // не кешируемые результаты
        calculator.plus(100, 100); // #1
        calculator.plus(200, 200); // #2
        calculator.plus(100, 100); // #3

        // передаем вызовы методов в CacheProxy
        Calculator cached = ProxyUtils.makeCached(calculator);
        cached.plus(100, 100);// #1
        cached.plus(200, 200); // #2
        cached.plus(100, 100); // #1
        // минус - не помечен кешем
        cached.minus(100, 1);
    }
}
