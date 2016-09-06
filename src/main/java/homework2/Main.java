package homework2;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Домашка №2 - погрузка грузовиков
 * Created by art
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileUtils fileUtils = new FileUtils();
        // забираем все содержимое из файла
        String content = fileUtils.readResourceFile("input.txt");
        // делим на отдельные символы, чтобы потом спарсить в инты
        String[] words = content.split("[ \n]");
        // по условию должно быть хотя бы 3 аргумента (n,w,loads...), проверяем этот момент
        int numberOfWords = words.length;
        if (numberOfWords <= 2) {
            throw new IllegalArgumentException("file must contains at least 3 numbers");
        }
        try {
            // здесь все парсим, чтобы ниже передать в нашу функцию и ловим exception при парсинге строк в инты
            int numberOfLoads = Integer.parseInt(words[0]); // первое число в файле у нас - это число грузов
            int weight = Integer.parseInt(words[1]); // второе - грузоподъемность
            // остальные переписываем в массив и передаем все это в ф-цию load
            int[] loads = new int[numberOfLoads];
            for (int i = 0; i < numberOfLoads; i++) {
                loads[i] = Integer.parseInt(words[i + 2]);
            }
            // получаем результат
            String res = load(numberOfLoads, weight, loads);
            // пишем все в файл
            fileUtils.writeResourceFile("output.txt", res);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String load(int n, int w, int[] loads) {
        int num = 0; // количество погруженных предметов
        int weightSum = 0; // суммарная масса погруженных предметов
        for (int i = 0; i < n; i++) {
            int tmpWeightSum = weightSum + loads[i];
            if (tmpWeightSum <= w) {
                num++;
                weightSum = tmpWeightSum;
            }
        }
        System.out.println("result: " + num + " " + weightSum);
        return num + " " + weightSum;
    }
}
