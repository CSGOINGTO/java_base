package interview_problems.graph;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DependencyProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int num = Integer.parseInt(line);
        Set<String> strs = new HashSet<>(num);
        GraphEdge[] edges = new GraphEdge[num];
        for (int i = 0; i < num; i++) {
            line = in.nextLine();
            final GraphEdge graphEdge = new GraphEdge();
            graphEdge.setBeginDepend(line.split(" ")[0]);
            graphEdge.setEndDepend(line.split(" ")[1]);
            edges[i] = graphEdge;
            strs.add(line.split(" ")[0]);
            strs.add(line.split(" ")[1]);
        }
        String[] depends = new String[strs.size()];
        final Graph graph = new Graph(strs.toArray(depends), edges, true);
        graph.print();
        int sum = 0;
        graph.dfs(0, new int[num], "d", sum);
        System.out.println(sum);
    }

}

class Graph{
    int size;

    int[][] matrix;

    String[] dependArray;

    public Graph(String[] dependArray, GraphEdge[] edges, boolean direction) {
        this.dependArray = dependArray;
        this.size = dependArray.length;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix[i][j] = -2;
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        for (GraphEdge edge: edges) {
            int begin = findIndex(edge.getBeginDepend(), dependArray);
            int end = findIndex(edge.getEndDepend(), dependArray);
            matrix[begin][end] = edge.getCost();
            if (!direction) matrix[end][begin] = edge.getCost();
        }
    }

    private int findIndex(String depend, String[] dependArray) {
        for (int i = 0; i < dependArray.length; i++) {
            if (depend.equals(dependArray[i])) {
                return i;
            }
        }
        return -1;
    }

    void print() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(dependArray[i] + " ");
            for (int j : matrix[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    void dfs(int start, int[] visit, String target, int sum) {
        System.out.print(dependArray[start] + " ");
        visit[start] = 1;
        for (int i = 0; i < visit.length; i++) {
            if (matrix[start][i] == 0 && visit[i] == 0) {
                if (dependArray[i].equals(target)) {
                    sum++;
                }
                dfs(i, visit, target, sum);
            }
        }

    }
}

class GraphEdge {

    private String beginDepend;

    private String endDepend;

    private int cost;

    public String getBeginDepend() {
        return beginDepend;
    }

    public void setBeginDepend(String beginDepend) {
        this.beginDepend = beginDepend;
    }

    public String getEndDepend() {
        return endDepend;
    }

    public void setEndDepend(String endDepend) {
        this.endDepend = endDepend;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "beginDepend='" + beginDepend + '\'' +
                ", endDepend='" + endDepend + '\'' +
                ", cost=" + cost +
                '}';
    }
}
