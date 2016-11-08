package homework8;

import org.junit.Assert;
import org.junit.Test;

public class ProxyTests {
    @Test
    public void referencesTest() {
        Calculator calculator = new CalculatorImpl();
        // не кешируемые результаты
        Integer notCached1 = calculator.plus(100, 100); // #1
        Integer notCached2 = calculator.plus(200, 200); // #2
        Integer notCached3 = calculator.plus(100, 100); // #3

        Assert.assertNotSame(notCached1, notCached3);

        // передаем вызовы методов в CacheProxy
        Calculator cached = ProxyUtils.makeCached(calculator);
        Integer cached1 = cached.plus(100, 100); // #1
        Integer cached2 = cached.plus(200, 200); // #2
        Integer cached3 = cached.plus(100, 100); // #1

        // проверяем совпадают ли ссылки у двух одинаковых результатов, которые мы вытащили из кэша
        Assert.assertSame(cached1, cached3);
    }
}
