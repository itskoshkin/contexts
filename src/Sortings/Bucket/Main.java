package Sortings.Bucket;

import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private static final int size = 100;

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] array = new double[size];
        IntStream.range(0, n).forEach(i -> array[i] = scanner.nextDouble());
        System.out.print("Initial array:\n");
        IntStream.range(0, n).forEach(i -> System.out.printf("%.2f ", array[i]));
        System.out.print("\n\n");
        new Main().bucketSort(array, n);
    }

    private void bucketSort(double[] array, int n) {
        if (n > 1) {
            double max = array[0];
            double min = array[0];
            for (int i = 1; i < n; i++) {
                if (min > array[i]) min = array[i];
                if (max < array[i]) max = array[i];
            }
            if (max != min) {
                double diff = max - min;
                int k = 2 * n;
                double p = diff / k;
                int[] count = new int[2 * size];
                double[][] bucket = new double[size * 2][size];
                for (int i = 0; i < n; i++) {
                    int bcktTemp = (int) ((array[i] - min) / p);
                    if (bcktTemp == k) bucket[k - 1][count[k - 1]++] = max;
                    else bucket[bcktTemp][count[bcktTemp]++] = array[i];
                }
                {
                    int d = 0;
                    while (d < k) {
                        if (count[d] > 0) {
                            System.out.print("Bucket:\n");
                            for (int j = 0; j < count[d]; j++) System.out.printf("%.2f ", bucket[d][j]);
                            System.out.print("\n");
                        }
                        for (int i = count[d] - 1; i > 0; i--)
                            for (int j = 0; j < i; j++)
                                if (bucket[d][j] > bucket[d][j + 1]) {
                                    double buffer = bucket[d][j];
                                    bucket[d][j] = bucket[d][j + 1];
                                    bucket[d][j + 1] = buffer;
                                }
                        if (count[d] > 0) {
                            System.out.print("Sorted bucket:\n");
                            for (int j = 0; j < count[d]; j++) System.out.printf("%.2f ", bucket[d][j]);
                            System.out.print("\n");
                            System.out.print("\n");
                        }
                        d++;
                    }
                }
                System.out.print("Final array:\n");
                int point = 0, d = 0;
                while (d < k) {
                    for (int i = 0; i < count[d]; i++) {
                        array[point] = bucket[d][i];
                        System.out.printf("%.2f ", array[point]);
                        point++;
                    }
                    d++;
                }
            } else {
                System.out.print("Bucket:\n");
                IntStream.range(0, n).forEach(i -> System.out.printf("%.2f ", array[i]));
                System.out.print("\nSorted bucket:\n");
                IntStream.range(0, n).forEach(i -> System.out.printf("%.2f ", array[i]));
                System.out.print("\n\nFinal array:\n");
                IntStream.range(0, n).forEach(i -> System.out.printf("%.2f ", array[i]));
                System.out.print("\n");
            }
        }
    }
}
