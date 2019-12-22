package GraphAlgorithms.CitiesDijkstra;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCities = Integer.parseInt(scanner.nextLine());
        LinkedList<LinkedList<Integer>> graphPath = new LinkedList<>();
        LinkedList<LinkedList<String>> citiesPath = new LinkedList<>();
        double[] road = new double[numberOfCities];
        int[] red = new int[numberOfCities];
        int[] black = new int[numberOfCities];
        for (int i = 0; i < numberOfCities; ++i) {
            graphPath.add(new LinkedList<>());
            citiesPath.add(new LinkedList<>());
            road[i] = 2.147483646E9D;
            red[i] = i;
            black[i] = 0;
        }
        LinkedList<String> IndexOfCities = new LinkedList<>();
        int[][] _XY = new int[numberOfCities][2];
        String whither;
        for (int i = 0; i < numberOfCities; ++i) {
            whither = scanner.nextLine();
            String[] isSplit = whither.split(" ");
            IndexOfCities.add(i, isSplit[0]);
            _XY[i][0] = Integer.parseInt(isSplit[1]);
            _XY[i][1] = Integer.parseInt(isSplit[2]);
            int links = isSplit.length - 3;
            for (int j = 0; j < links; ++j) citiesPath.get(i).add(isSplit[j + 3]);
        }

        String isWay = scanner.nextLine();
        String[] isWaySplit = isWay.split(" ");
        String outbreak = isWaySplit[0];
        whither = isWaySplit[1];
        int j = IndexOfCities.indexOf(outbreak);
        int endPoint = IndexOfCities.indexOf(whither);
        road[j] = 0.0D;
        double[][] value = new double[numberOfCities][numberOfCities];
        new Main().fillingArray(numberOfCities, citiesPath, IndexOfCities, _XY, value, graphPath);
        new Main().Dijkstra(numberOfCities, road, black, graphPath, red, value);
        new Main().printPath(road, endPoint, red, IndexOfCities, j);
    }

    private void fillingArray(int numberOfCities, LinkedList<LinkedList<String>> citiesPath,
                              LinkedList<String> IndexOfCities, int[][] _XY, double[][] value,
                              LinkedList<LinkedList<Integer>> graphPath) {
        for (int i = 0; i < numberOfCities; ++i) {
            int links = citiesPath.get(i).size();
            for (int j = 0; j < links; ++j) {
                int neighbourhoodI = IndexOfCities.indexOf((citiesPath.get(i)).get(j));
                double x = _XY[i][0] - _XY[neighbourhoodI][0];
                double y = _XY[i][1] - _XY[neighbourhoodI][1];
                value[i][neighbourhoodI] = Math.sqrt(x * x + y * y);
                graphPath.get(i).add(neighbourhoodI);
            }
        }
    }

    private void Dijkstra(int numberOfCities, double[] road, int[] black,
                          LinkedList<LinkedList<Integer>> graphPath, int[] red, double[][] value) {
        for (int i = 0; i < numberOfCities; ++i) {
            double integerMin = 2.147483647E9D;
            int cities = numberOfCities;
            int neighbourhoods;
            for (neighbourhoods = numberOfCities - 1; neighbourhoods >= 0; --neighbourhoods) {
                if (road[neighbourhoods] < integerMin && black[neighbourhoods] == 0) {
                    integerMin = road[neighbourhoods];
                    cities = neighbourhoods;
                }
            }
            neighbourhoods = graphPath.get(cities).size();
            for (int j = 0; j < neighbourhoods; ++j) {
                int neighbourhood = (graphPath.get(cities)).get(j);
                double neighbourCost = road[neighbourhood];
                double way = value[cities][neighbourhood];
                if (neighbourCost > way + road[cities]) {
                    road[neighbourhood] = way + road[cities];
                    red[neighbourhood] = cities;
                }
            }
            black[cities] = 1;
        }

    }

    private void printPath(double[] road, int endPoint, int[] red, LinkedList<String> IndexOfCities, int newPoint) {
        Stack<String> pathway = new Stack<>();
        if (road[endPoint] != 2.147483646E9D) {
            int j = endPoint;
            int parent = red[j];
            pathway.add(IndexOfCities.get(parent));
            j = parent;
            while (parent != newPoint && parent != endPoint) {
                parent = red[j];
                pathway.add(IndexOfCities.get(parent));
                j = parent;
            }
            StringBuilder correctWay = new StringBuilder();
            while (!pathway.isEmpty()) correctWay.append(pathway.pop()).append(" ");
            System.out.printf("Path is not greater than %d\n", (int) Math.ceil(road[endPoint]));
            System.out.print("Path:\n");
            System.out.printf("%s%s", correctWay, IndexOfCities.get(endPoint));
        } else {
            System.out.print("Path:\n");
            System.out.print("No way");
        }
    }
}


