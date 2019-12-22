package Search.Binary;

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
        System.out.print("\nBinary search:\n");
        new Main().binarySearch(array, value, 0, array.length - 1);
    }


    private void binarySearch(int[] array, int value, int low, int high) {
        int middle;
        while (low <= high) {
            middle = (low + high) >>> 1;
            System.out.print(array[middle] + " ");
            if (array[middle] == value) return;
            else if (value > array[middle]) low = middle + 1;
            else high = middle - 1;
        }
    }
}

