package GraphAlgorithms.LabyrinthBFS;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();
        int anInt = scanner.nextInt();
        int Ent = nextInt * anInt;
        int lines = Ent / nextInt;
        char[] chars = new char[Ent];
        Graph graph = new Graph(Ent);
        int count = 0;
        int counter;
        int endPoint;
        for (counter = 0; counter < nextInt; ++counter) {
            String a = scanner.next();
            for (endPoint = 0; endPoint < anInt; ++endPoint) {
                chars[count] = a.charAt(endPoint);
                ++count;
            }
        }
        System.out.println("Initial labyrinth:");
        counter = 0;
        int startPoint;
        for (startPoint = 0; startPoint < Ent; ++startPoint) {
            System.out.print(chars[startPoint]);
            ++counter;
            if (counter == anInt && startPoint != Ent - 1) {
                System.out.println();
                counter = 0;
            }
        }
        System.out.println("\nGraph:");
        startPoint = -3;
        endPoint = -3;
        for (int i = 0; i < Ent; ++i) {
            int lineCount;
            for (lineCount = 1; i > lineCount * lines - 1; ++lineCount);
            if (chars[i] == 'S') startPoint = i;
            if (chars[i] == 'F') endPoint = i;
            if (chars[i] == 'S' || chars[i] == 'F' || chars[i] == '.') {
                if (i - anInt >= 0 && (chars[i - anInt] == 'S' || chars[i - anInt] == 'F' || chars[i - anInt] == '.'))
                    graph.add(i, i - anInt);
                if (i + anInt < Ent && (chars[i + anInt] == 'S' || chars[i + anInt] == 'F' || chars[i + anInt] == '.'))
                    graph.add(i, i + anInt);
                if (i - 1 >= lineCount * lines - lines && (chars[i - 1] == 'S' || chars[i - 1] == 'F' || chars[i - 1] == '.'))
                    graph.add(i, i - 1);
                if (i + 1 < lineCount * lines && (chars[i + 1] == 'S' || chars[i + 1] == 'F' || chars[i + 1] == '.'))
                    graph.add(i, i + 1);
            }
        }

        for (int i = 0; i < Ent; ++i) {
            String a = "";
            for (int j = 0; j < graph.get(i).size(); ++j) {
                String var10001 = graph.get(i).get(j).toString();
                a = a.concat(var10001 + " ");
            }
            if (Objects.equals(a, "")) a = "None";
            System.out.println(i + " - " + a);
        }
        System.out.println("BFS result is :");
        graph.search(startPoint, endPoint, anInt);
    }
}


