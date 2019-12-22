package PhilosophyAlgorithms.MaxSubmatrix;


import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private final static int INTS = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] ints = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int i1 = 0; i1 < k; i1++) {
                ints[i][i1] = scanner.nextInt();
            }
        }
        System.out.printf("Max submatrix: %d", new Main().searchMaxSubMatrix(ints));

    }

    private int searchMaxSubMatrix(int[][] ints3) {
        int ints1 = INTS, i1 = 0;
        while (i1 < ints3[0].length) {
            int[] ints2 = new int[ints3.length];
            int i = i1;
            while (i < ints3[0].length) {
                int k = 0;
                while (k < ints3.length) {
                    ints2[k] += ints3[k][i];
                    k++;
                }
                int[] ints = AlgKadane(ints2);
                if (ints[0] <= ints1) {
                } else {
                    ints1 = ints[0];
                }
                i++;
            }
            i1++;
        }
        return ints1;
    }

    private int[] AlgKadane(int[] ints) {
        int[] ints1 = new int[]{INTS, 0, -1};
        int i2 = 0, i1 = 0;
        {
            int i = 0;
            while (true) {
                if (i < ints.length) {
                    i2 += ints[i];
                    if (i2 >= 0) {
                        if (i2 <= ints1[0]) {
                        } else {
                            ints1[0] = i2;
                            ints1[1] = i1;
                            ints1[2] = i;
                        }
                    } else {
                        i2 = 0;
                        i1 = i + 1;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        if (ints1[2] == -1) {
            IntStream.range(0, ints.length).filter(i -> ints[i] > ints1[0]).forEach(i -> {
                ints1[0] = ints[i];
                ints1[1] = i;
                ints1[2] = i;
            });
            return ints1;
        } else {
            return ints1;
        }
    }
}