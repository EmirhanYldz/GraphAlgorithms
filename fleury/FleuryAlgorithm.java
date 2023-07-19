import java.util.*;

public class FleuryAlgorithm {
    private int vertices;
    private List<List<Integer>> adjList;
    
    public FleuryAlgorithm(int v) {
        vertices = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new LinkedList<>());
        }
    }
    
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    
    private boolean isValidNextEdge(int u, int v) {
        // Count the number of remaining edges
        int count = 0;
        for (int i : adjList.get(u)) {
            if (i != -1) {
                count++;
            }
        }
        
        // If there's only one remaining edge, it's a valid next edge
        if (count == 1) {
            return true;
        }
        
        // Check if removing the edge u-v disconnects the graph
        boolean[] visited = new boolean[vertices];
        int count1 = DFSCount(u, visited);
        
        // Remove the edge u-v and check if it disconnects the graph
        removeEdge(u, v);
        Arrays.fill(visited, false);
        int count2 = DFSCount(u, visited);
        
        // Add the edge back to the graph
        addEdge(u, v);
        
        return (count1 > count2) ? false : true;
    }
    
    private int DFSCount(int v, boolean[] visited) {
        visited[v] = true;
        int count = 1;
        
        for (int i : adjList.get(v)) {
            if (i != -1 && !visited[i]) {
                count += DFSCount(i, visited);
            }
        }
        
        return count;
    }
    
    private void removeEdge(int u, int v) {
        adjList.get(u).remove(new Integer(v));
        adjList.get(v).remove(new Integer(u));
    }
    
    public void printEulerPath() {
        int u = 0; // Starting vertex for Euler's path
        
        // Find a vertex with an odd degree as the starting vertex
        for (int i = 0; i < vertices; i++) {
            if (adjList.get(i).size() % 2 != 0) {
                u = i;
                break;
            }
        }
        
        printEulerUtil(u);
        System.out.println();
    }
    
    private void printEulerUtil(int u) {
        for (int v : adjList.get(u)) {
            if (v != -1 && isValidNextEdge(u, v)) {
                System.out.print(u + "-" + v + " ");
                removeEdge(u, v);
                printEulerUtil(v);
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        FleuryAlgorithm graph = new FleuryAlgorithm(4);
        
        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        
        // Print Euler's path
        System.out.print("Euler Path: ");
        graph.printEulerPath();
    }
}