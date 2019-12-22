package PhilosophyAlgorithms.Backpack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] thing = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            thing[i] = scanner.nextInt();
            value[i] = scanner.nextInt();
        }

        System.out.println("new Backpack().knapSack(capacity, thing, value, n) = "
                + new Main().knapSack(capacity, thing, value, n));
    }


    private int knapSack(int capacity, int[] thing, int[] value, int n) {
        int[][] buff = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0)
                    buff[i][j] = 0;
                else if (thing[i - 1] <= j)
                    buff[i][j] = Math.max(value[i - 1] + buff[i - 1][j - thing[i - 1]], buff[i - 1][j]);
                else
                    buff[i][j] = buff[i - 1][j];
            }
        }
        return buff[n][capacity];
    }
}
