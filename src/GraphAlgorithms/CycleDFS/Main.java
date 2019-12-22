package GraphAlgorithms.CycleDFS;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int E = scanner.nextInt();
        int[][] matrix = new int[V][V];
        IntStream.range(0, E).forEach(i -> matrix[scanner.nextInt()][scanner.nextInt()] = 1);
        int index = 0;
        boolean option = false;
        while (index < V) {
            int[] path = new int[]{index};
            for (int i = 0; i < V; i++) {
                if (matrix[index][i] == 1) option = CycleSearch(matrix, path, i, V);
                if (option) break;
            }
            if (option) break;
            index++;
        }

        if (option) {
            int[] ans = CycleGet(matrix, V, new int[]{index});
            System.out.println("Cycle:");
            for (int i : ans) System.out.printf("%d ", i);
            System.out.print("\n");
        } else {
            System.out.println("No cycles");
        }
    }

    private static int[] CycleGet(int[][] matrix, int v, int[] path) {
        for (int i = 0; i < v; i++) {
            if (matrix[path[path.length - 1]][i] == 1) {
                int[] newPath = new int[path.length + 1];
                System.arraycopy(path, 0, newPath, 0, path.length);
                newPath[path.length] = i;
                if (i != path[0]) {
                    boolean opt = CycleSearch(matrix, path, i, v);
                    if (opt) return CycleGet(matrix, v, newPath);
                } else {
                    return path;
                }
            }
        }
        return new int[]{0};
    }

    private static boolean CycleSearch(int[][] matrix, int[] path, int n, int v) {
        if (path[0] == n) return true;
        if (indexOf(path, n) > 0) return false;
        boolean result = false;
        for (int i = 0; i < v; i++) {
            if (matrix[n][i] == 1) {
                int[] newPath = new int[path.length + 1];
                System.arraycopy(path, 0, newPath, 0, path.length);
                newPath[path.length] = n;
                result = CycleSearch(matrix, newPath, i, v);
                if (result) break;
            }
        }
        return result;
    }

    private static int indexOf(int[] path, int n) {
        return IntStream.range(0, path.length).filter(i -> path[i] == n).findFirst().orElse(-1);
    }
}
