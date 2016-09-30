package homework5;

import java.net.MalformedURLException;

/**
 * Created by art
 * Задание 5 на exception'ы
 * реализовать метод
 * readContent(String url) - читает html страницу по данному в параметре url
 * реализовать код, при котором url будет вводиться в консоли. если url не валидный, нужно снова вызвать консоль для ввода url
 */
public class Main {
    public static void main(String[] args) {
        HtmlReader htmlReader = new HtmlReader();

        // чтение из консоли
        htmlReader.readFromConsole();

        // чтение по url
        try {
            htmlReader.readContent("https://toster.ru/q/24");
        } catch (MalformedURLException e) {
            ExceptionUtils.printExceptionMessage(e);
        }
    }
}
