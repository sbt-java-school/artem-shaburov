package homework3;

/**
 * Задание 3 - разобраться с гитом и подумать над бинарным поиском
 * Created by art
 */
public class Main {
    public static void main(String[] args) {
        int[] array = new int[] { 1, 3, 4, 19, 112, 212 };
        int result = binarySearchNoRecursion(19, array, 0, array.length - 1);
        System.out.println(result);
    }

    /** с рекурсией */
    private static int binarySearch(int key, int[] array, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (key < array[mid]) {
            return binarySearch(key, array, low, mid - 1);
        } else if (key > array[mid]) {
            return binarySearch(key, array, mid + 1, high);
        }
        return mid;
    }

    /** через вайл */
    private static int binarySearchNoRecursion(int key, int[] array, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        while (array[mid] != key) {
            if (key < array[mid]) {
                high = mid - 1;
            } else if (key > array[mid]) {
                low = mid + 1;
            }
            // recalculate
            mid = low + (high - low) / 2;
        }
        return mid;
    }
}
