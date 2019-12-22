package PhilosophyAlgorithms.Coins;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    static int CoordX, CoordY;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String[][] tempStringMatrix = new String[n][k];
        int[][] tempIntMatrix = new int[n][k];
        int X = 0, Y = 0;
        {
            int i = 0;
            while (i < n) {
                int j = 0;
                while (j < k) {
                    tempStringMatrix[i][j] = scanner.next();
                    if (!"S".equals(tempStringMatrix[i][j])) {
                        tempIntMatrix[i][j] = Integer.parseInt(tempStringMatrix[i][j]);
                    } else {
                        X = i;
                        Y = j;
                        tempIntMatrix[i][j] = 0;
                    }
                    j++;
                }
                i++;
            }
        }

        CoordY = Y;
        CoordX = X;

        int[][] matrix = new int[n - X][k - Y];
        int i = 0;
        while (i < n - X) {
            if (k - Y >= 0) System.arraycopy(tempIntMatrix[X + i], Y, matrix[i], 0, k - Y);
            i++;
        }


        System.out.print("Path:\n");
        System.out.printf("\nCoins: %d", new Main().maxCost(matrix, matrix.length, matrix[0].length));

    }

    private int maxCost(int[][] matrix, int n, int k) {

        int[][] costCalculateMatrix = new int[n][k];
        int sum = 0;
        {
            int i = 0;
            while (i < k) {
                costCalculateMatrix[0][i] = sum + matrix[0][i];
                sum = costCalculateMatrix[0][i];
                i++;
            }
        }
        sum = 0;
        {
            int i = 0;
            while (i < n) {
                costCalculateMatrix[i][0] = sum + matrix[i][0];
                sum = costCalculateMatrix[i][0];
                i++;
            }
        }

        int i = 1;
        while (i < n) {
            int j = 1;
            while (j < k) {
                costCalculateMatrix[i][j] = matrix[i][j] + Math.max(
                        Math.max(costCalculateMatrix[i - 1][j - 1],
                                costCalculateMatrix[i - 1][j]),
                        costCalculateMatrix[i][j - 1]);
                j++;
            }
            i++;
        }


        print(costCalculateMatrix, n - 1, k - 1);

        return costCalculateMatrix[n - 1][k - 1];
    }


    private void print(int[][] costCalculateMatrix, int n, int k) {
        ArrayList<Integer> arrayList1 = new ArrayList<>(n), arrayList2 = new ArrayList<>(k);
        arrayList1.add(n);
        arrayList2.add(k);
        if (n != 0 || k != 0) {
            do {
                if (!(n == 0)) {
                    if (!(k != 0 && costCalculateMatrix[n][k - 1] > costCalculateMatrix[n - 1][k])) n--;
                    else k--;
                } else k--;
                arrayList1.add(n);
                arrayList2.add(k);
            } while (!(n == 0 && k == 0));
        }
        IntStream.iterate(
                arrayList1.size() - 1, l -> l >= 0, l -> l - 1).
                forEachOrdered(l -> System.out.printf("(%d,%d) ", arrayList1.get(l) + CoordX, arrayList2.get(l) + CoordY));
    }
}

