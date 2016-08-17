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
        String content = fileUtils.readResourceFile("input.txt");
        System.out.println(content);
        String[] words = content.split("[ \n]");
        System.out.println(Arrays.toString(words));
        int numberOfWords = words.length;
        if (numberOfWords > 2) {
            // здесь все парсим, чтобы ниже передать в нашу функцию
            int numberOfLoads = Integer.parseInt(words[0]); // первое число в файле у нас - это число грузов
            int weight = Integer.parseInt(words[1]); // второе - грузоподъемность
            int[] loads = new int[numberOfLoads];
            for (int i = 0; i < numberOfLoads; i++) {
                loads[i] = Integer.parseInt(words[i + 2]);
            }
            String res = load(numberOfLoads, weight, loads);
            fileUtils.writeResourceFile("output.txt", res);
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
