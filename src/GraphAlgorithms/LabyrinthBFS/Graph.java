package GraphAlgorithms.LabyrinthBFS;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Graph {
    private int top;
    private LinkedList<Integer>[] graph;

    Graph(int top) {
        this.top = top;
        this.graph = new LinkedList[top];
        int[] routes = new int[top];

        for (int i = 0; i < top; ++i) {
            this.graph[i] = new LinkedList();
            routes[i] = -1;
        }

    }

    void add(int source, int destination) {
        this.graph[source].add(destination);
        Collections.sort(this.graph[source]);
    }

    LinkedList get(int i) {
        return this.graph[i];
    }

    void search(int first, int last, int length) {
        boolean[] visited = new boolean[this.top];
        Queue<Integer> queue = new LinkedList();
        int[] iterations = new int[this.top];
        int a;
        for (a = 0; a < this.top; ++a) iterations[a] = -1;
        iterations[first] = 0;
        queue.add(first);
        int i;
        while (!queue.isEmpty()) {
            a = queue.remove();
            if (a == last) break;
            visited[a] = true;
            for (i = 0; i < this.get(a).size(); ++i) {
                int b = (Integer) this.get(a).get(i);
                if (!visited[b]) {
                    iterations[b] = iterations[a] + 1;
                    queue.add(b);
                }
            }
        }
        a = 0;
        for (i = 0; i < this.top; ++i) {
            System.out.print(iterations[i] + " ");
            ++a;
            if (a == length) {
                System.out.println();
                a = 0;
            }
        }

    }
}
