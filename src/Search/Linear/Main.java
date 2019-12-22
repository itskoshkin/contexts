package Search.Linear;

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
        new Main().linearSearch(array, value);
    }

    private void linearSearch(int[] array, int value) {
        System.out.print("Linear search:\n");
        for (int item : array) {
            System.out.print(item + " ");
            if (item == value) break;
        }
    }
}
