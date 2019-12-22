package Sortings.Merge;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();
        int[] arrayForSort = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) arrayForSort[i] = scanner.nextInt();
        System.out.println("Initial array:");
        for (int i = 0; i < arrayLength; i++) System.out.printf("%d ", arrayForSort[i]);
        System.out.print("\n\n");


        new Main().mergeSort(arrayForSort, arrayLength);
    }

    private int[] mergeSort(int[] arrayImport, int n) {

        if (n < 2) return arrayImport;
        int mid = n / 2;

        int[] arrayFirst = new int[mid];
        int[] arraySecond = new int[n - mid];

        System.arraycopy(arrayImport, 0, arrayFirst, 0, mid);
        System.arraycopy(arrayImport, mid, arraySecond, 0, n - mid);

        mergeSort(arrayFirst, mid);
        mergeSort(arraySecond, n - mid);
        merge(arrayImport, arrayFirst, arraySecond, mid, n - mid);

        return arrayImport;
    }

    private static void merge
            (int[] arrayExport, int[] arrayFirst, int[] arraySecond, int left, int right) {

        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (arrayFirst[i] <= arraySecond[j]) arrayExport[k++] = arrayFirst[i++];
            else arrayExport[k++] = arraySecond[j++];
        }

        while (i < left) arrayExport[k++] = arrayFirst[i++];
        while (j < right) arrayExport[k++] = arraySecond[j++];

        System.out.println("Left part: " + Arrays.toString(arrayFirst)
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                + " ");
        System.out.println("Right part: " + Arrays.toString(arraySecond)
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                + " ");
        System.out.println("Merged parts: " + Arrays.toString(arrayExport)
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                + " " + "\n");

    }
}
