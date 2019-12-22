package Search.Doubling;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int value = scanner.nextInt();
        int[] array = IntStream.range(0, length).map(i -> scanner.nextInt()).toArray();
        System.out.println("Initial array:");
        IntStream.range(0, length).forEach(i -> System.out.printf("%d ", array[i]));
        System.out.print("\n");
        new Main().doublingSearch(array, value, length);

    }

    private void binarySearchForDoublingSearch(int[] array, int value, int low, int end) {
        while (low <= end) {
            int middle = (low + end) >>> 1;
            System.out.print(array[middle] + " ");
            if (array[middle] == value) {
                return;
            } else if (value > array[middle]) {
                low = middle + 1;
            } else {
                end = middle - 1;
            }
        }
    }

    private void doublingSearch(int[] array, int value, int length) {
        System.out.print("\nDoubling search:\n");
        if (array.length == 1) {
            System.out.print(array[0]);
            return;
        }
        if (array[0] == value) {
            System.out.print(array[0]);
            return;
        }
        int high = 1;
        while (high < length && array[high] < value) high *= 2;
        if (high < length && array[high] == value) {
            System.out.print(array[high]);
            return;
        }
        if (array[1] == value) {
            System.out.print(array[1]);
            return;
        }
        if (high == 1) {
            System.out.print(array[0]);
            return;
        }
        binarySearchForDoublingSearch(array, value, high / 2 + 1, Math.min(high - 1, length - 1));
    }
}
