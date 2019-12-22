package PhilosophyAlgorithms.LongestIncreasingSubsequence;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[] array = new int[k];
        for (int i = 0; i < k; i++)
            array[i] = scanner.nextInt();

        System.out.printf("Length %d",
                new Main().longIncSub(array, k));

    }

    private int longIncSub(int[] arr, int n) {
        int[] lis = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++)
            lis[i] = 1;

        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        for (int i = 0; i < n; i++)
            if (max < lis[i])
                max = lis[i];

        return max;
    }

}
