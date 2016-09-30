package homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by art
 */
public class HtmlReader {
    /**
     * Empty constructor
     */
    public HtmlReader() {
    }

    /**
     * 1.
     * Read html content by giving url
     * @param urlAddress String representation of the url
     */
    public void readContent(String urlAddress) throws MalformedURLException {
        // используем java.net пакет
        // получаем объект URL. если адрес не валидный, прокидываем exception для второго метода
        URL url = new URL(urlAddress);

        // открываем входящий поток для контента со страницы и создаем buffered reader для буфферизации полученного контента
        // reader'ы у нас closeable
        try (InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            // ну и наконец печатаем это все
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            ExceptionUtils.printExceptionMessage(e);
        }
    }

    /**
     * Recursive function that calls console and trying to read html content by url given in the console
     */
    public void readFromConsole() {
        // читаем из консоли
        // также используем auto closable
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String urlAddress = bufferedReader.readLine();
            try {
                // используем ранее написанный метод, передаем ему url из консоли
                readContent(urlAddress);
            } catch (MalformedURLException e) {
                // если поймали exception печатаем его
                ExceptionUtils.printExceptionMessage(e);
                // и вызываем наш метод снова
                readFromConsole();
            }
        } catch (IOException e) {
            ExceptionUtils.printExceptionMessage(e);
        }
    }
}
