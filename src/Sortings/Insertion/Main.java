package Sortings.Insertion;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();
        int[] arrayForSort = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) arrayForSort[i] = scanner.nextInt();
        System.out.println("Initial array:");
        IntStream.range(0, arrayLength).forEach(i -> System.out.printf("%d ", arrayForSort[i]));
        System.out.print("\n");
        new Main().insertionSort(arrayForSort);
    }


    private int[] insertionSort(int[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
            System.out.printf("Insertion - Element #%d\n", i);
            for (int value : array) System.out.printf("%d ", value);
            System.out.print("\n");
        }
        return array;
    }
}
