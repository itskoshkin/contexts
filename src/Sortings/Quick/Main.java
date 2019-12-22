package Sortings.Quick;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = IntStream.range(0, length).map(i -> scanner.nextInt()).toArray();

        System.out.print("Initial array:\n");
        IntStream.range(0, length).forEach(i -> System.out.printf("%d ", array[i]));
        System.out.print("\n");
        new Main().quickSort(array, 0, length - 1, length);
    }

    private void quickSort(int[] array, int start, int end, int length) {
        if (start < end) {
            int pivot = (start + end) / 2;
            int mid = array[(start + end) / 2];
            System.out.printf("\nPivot index: %d , pivot element: %d", (start + end) / 2, mid);
            int[] b = new int[length];
            int i = start;
            for (int k = start; k <= end; k++)
                if (k != pivot) {
                    if (array[k] <= mid) {
                        b[i] = array[k];
                        i++;
                    }
                }
            b[i] = mid;
            pivot = i;
            i++;
            for (int x = start; x <= end; x++)
                if (array[x] > mid) {
                    b[i] = array[x];
                    i++;
                }
            if (end + 1 - start >= 0) System.arraycopy(b, start, array, start, end + 1 - start);
            System.out.print("\nArray after partition: ");
            IntStream.range(0, length).forEach(j -> System.out.printf("%d ", array[j]));
            System.out.print("\n");
            quickSort(array, start, pivot - 1, length);
            quickSort(array, pivot + 1, end, length);
        }
    }

}
