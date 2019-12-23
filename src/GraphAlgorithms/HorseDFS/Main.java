package GraphAlgorithms.HorseDFS;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int posX = scanner.nextInt();
        int posY = scanner.nextInt();
        LinkedList<int[]> paths = new LinkedList<>();
        System.out.println("Graph:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                paths.add(PosCheck(i, j, size));
                int[] temp = paths.get(i * size + j);
                if (temp.length > 0) {
                    System.out.printf("%d - ", i * size + j);
                    int[] temporary = paths.get(i * size + j);
                    Arrays.stream(temporary).forEach(value -> System.out.printf("%d ", value));
                    System.out.println();
                }
            }
        }
        System.out.println("Hamiltonian path:");
        int index = posX * size + posY;
        int counter = 0;
        int[] path = PathSearch(paths, new int[]{index}, size * size);
        if (size == 1 || path == null) {
            System.out.println("No way");
        } else {
            int[] answer = IntStream.range(0, path.length).map(i -> indexOf(path, i)).toArray();
            for (int e : answer) {
                System.out.printf("%d ", e);
                counter++;
                if (counter % 5 == 0) System.out.println();
            }
        }
    }


    private static int[] PosCheck(int x, int y, int size) {
        List<Integer> poses = new LinkedList<>();
        for (int i = -2; i <= 2; i += 4)
            if (((i + x) >= 0) && ((i + x) < size))
                for (int j = -1; j <= 1; j += 2) if (j + y >= 0 && j + y < size) poses.add((x + i) * size + y + j);

        for (int i = -1; i <= 1; i += 2)
            if (i + x >= 0 && i + x < size)
                for (int j = -2; j <= 2; j += 4) if (j + y >= 0 && j + y < size) poses.add((x + i) * size + y + j);
        Collections.sort(poses);
        int[] array = new int[poses.size()];
        Arrays.setAll(array, poses::get);
        return array;
    }

    private static int LastArrayItem(int[] a) {
        return a[a.length - 1];
    }

    private static int[] PathSearch(LinkedList<int[]> paths, int[] path, int lim) {
        if (path.length == lim) return path;
        int[] pathForPoint = paths.get(LastArrayItem(path));
        for (int e : pathForPoint) {
            if (indexOf(path, e) == -1) {
                int[] newPath = new int[path.length + 1];
                int i = 0;
                for (int j : path) newPath[i++] = j;
                newPath[i] = e;
                int[] answer = PathSearch(paths, newPath, lim);
                if (answer != null) return answer;
            }
        }
        return null;
    }

    private static int indexOf(int[] path, int n) {
        return IntStream.range(0, path.length).filter(i -> path[i] == n).findFirst().orElse(-1);
    }
}

