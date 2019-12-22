package PhilosophyAlgorithms.GreedStone;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    final static int VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int temp = scanner.nextInt();
                if (temp == 0) graph[i][j] = VALUE;
                else graph[i][j] = temp;
            }
        new Main().walkOnGraph(n, graph);
    }

    private void walkOnGraph(int size, int[][] graph) {
        int[] pathsCost = new int[size];
        for (int line = 0; line < size; line++) {
            boolean[] visitedNodes = new boolean[size];
            IntStream.range(0, size).forEach(i -> visitedNodes[i] = false);

            int summaryCost = 0;
            //return 2 to line-line
            pathsCost[line] = recursionIteration(visitedNodes, size, line, graph, summaryCost, size, line);
        }
        /*
        после цикла проверяем массив paths
        если все переменные равны Integer.MAX_VALUE - возвращаем "Lost"
        иначе ищём минимум, и восстанавливаем путь по индексу минимума
        */
        printWay(pathsCost, graph, size);
    }

    private void printWay(int[] pathsCost, int[][] graph, int size) {
        int minimum = VALUE;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (minimum > pathsCost[i] && pathsCost[i] > 0) {
                minimum = pathsCost[i];
                index = i;
            }
        }

        if (minimum == Integer.MAX_VALUE) {
            System.out.print("Lost");
        } else {
            boolean[] visitedNodes = new boolean[size];
            IntStream.range(0, size).forEach(i -> visitedNodes[i] = false);
            int summaryCost = 0;
            System.out.print("Path:\n");

            recursionIterationForConsole(visitedNodes, size, index, graph, summaryCost, size, index);
            System.out.printf("%d ", index);
            System.out.printf("\nCost: %d", minimum);
        }
    }

    private int recursionIterationForConsole(
            boolean[] visitedNodes,
            int size, int line,
            int[][] graph, int summaryCost,
            int counter, int firstNode) {
        int indexMinimum = 0;
        //назначаем минимум самым большим числом
        int minimum = VALUE;
        //помечаем вершину как посещенную
        visitedNodes[line] = true;

        /*
        приходим в начало - возвращаем стоимость
        не можем найти путь дальше - возвращаем Integer.MAX_VALUE;
        выходим из рекурсии
        */
        //System.out.printf("%d ", line);
        if (summaryCost < 0) return VALUE;
        //для последней вершины
        if (counter == 1) {
            for (int i = 0; i < size; i++) {
                if (i == firstNode) {
                    if (graph[line][i] != VALUE) {
                        summaryCost += graph[line][i];
                        System.out.printf("%d ", line);
                        return summaryCost;
                    }
                }
            }
            return VALUE;
        } else {
            //ищём минимальный маршрут до любой из доступных вершин
            for (int j = 0; j < size; j++) {
                //проверям веришну на посещенность и минимум по цене
                if (minimum > graph[line][j] && !visitedNodes[j]) {
                    minimum = graph[line][j];
                    indexMinimum = j;
                }

            }
            System.out.printf("%d ", line);
            summaryCost += minimum;
            return recursionIterationForConsole(visitedNodes, size, indexMinimum, graph, summaryCost, --counter, firstNode);
        }
    }


    private int recursionIteration(
            boolean[] visitedNodes,
            int size, int line,
            int[][] graph, int summaryCost,
            int counter, int firstNode) {

        int indexMinimum = 0;
        //назначаем минимум самым большим числом
        int minimum = VALUE;
        //помечаем вершину как посещенную
        visitedNodes[line] = true;

        /*
        приходим в начало - возвращаем стоимость
        не можем найти путь дальше - возвращаем Integer.MAX_VALUE;
        выходим из рекурсии
        */
        if (summaryCost < 0) return VALUE;
        //для последней вершины
        if (counter == 1) {
            for (int i = 0; i < size; i++) {
                if (i == firstNode) {
                    if (graph[line][i] != VALUE) {
                        summaryCost += graph[line][i];
                        return summaryCost;
                    }
                }
            }
            return VALUE;
        } else {
            //ищём минимальный маршрут до любой из доступных вершин
            for (int j = 0; j < size; j++) {
                //проверям веришну на посещенность и минимум по цене
                if (minimum > graph[line][j] && !visitedNodes[j]) {
                    minimum = graph[line][j];
                    indexMinimum = j;
                }
            }
            summaryCost += minimum;
            return recursionIteration(visitedNodes, size, indexMinimum, graph, summaryCost, --counter, firstNode);
        }
    }
}
