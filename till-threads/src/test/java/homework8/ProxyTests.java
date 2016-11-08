package homework8;

import org.junit.Test;

public class ProxyTests {
    @Test
    public void referencesTest() {
        Calculator calculator = new CalculatorImpl();
        // не кешируемые результаты
        calculator.plus(100, 100); // #1
        calculator.plus(200, 200); // #2
        calculator.plus(100, 100); // #3

        // передаем вызовы методов в CacheProxy
        Calculator cached = ProxyUtils.makeCached(calculator);
        Integer first = cached.plus(100, 100); // #1
        Integer second = cached.plus(200, 200); // #2
        Integer third = cached.plus(100, 100); // #1
        // проверяем совпадают ли ссылки у двух одинаковых результатов, которые мы вытащили из кэша
        System.out.println(first == second);
        System.out.println(first == third);

        // минус не помечен кешем
        cached.minus(100, 1);
    }
}
