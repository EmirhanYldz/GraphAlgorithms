import java.util.*;

public class BoruvkaSollinAlgorithm{
    public static void main(String [] args){
        
        Graph graph = new Graph();

        graph.addEdge("A","B",5);
        graph.addEdge("A","C",7);
        graph.addEdge("B","C",9);
        graph.addEdge("C","D",6);
        graph.addEdge("D","E",2);
        graph.addEdge("D","F",1);
        graph.addEdge("D","G",5);
        graph.addEdge("E","F",3);
        graph.addEdge("F","G",2);

        graph.Boruvka();
    }
}


class Graph{
	private Map<String, Map<String, Integer>> adjVertices = new HashMap<>();

    public void Boruvka(){
        Map<String, Map<String, Integer>> MST = new HashMap<>();
        Map<String, Map<String, Integer>> copyAdjVertices = new HashMap<>();
        for (String string : adjVertices.keySet()) {
            for (String string2 : adjVertices.get(string).keySet()) {
                addEdgeToGraph(string, string2, adjVertices.get(string).get(string2), copyAdjVertices);
            }
        }

        for (String string : adjVertices.keySet()) {
            String current = string;
            String next = null;
            int cost = Integer.MAX_VALUE;
            for (String string2 : adjVertices.get(current).keySet()) {
                if(adjVertices.get(current).get(string2)<cost){
                    next = string2;
                    cost = adjVertices.get(current).get(next);
                }
            }
            addEdgeToGraph(current, next, cost, MST);
            removeEdge(current, next, copyAdjVertices);
        }

        System.out.println(adjVertices);
        System.out.println(MST);
        System.out.println(copyAdjVertices);
    }

    public Map<String, Map<String,Integer>> getAdj() {
		return adjVertices;
	}

    public void addEdge(String src, String dest,int weight) {
        if(!adjVertices.containsKey(src)){
            addVertex(adjVertices,src);
        }
        if(!adjVertices.containsKey(dest)){
            addVertex(adjVertices,dest);
        }
        adjVertices.get(src).put(dest,weight);
        adjVertices.get(dest).put(src,weight);
    }

    public void addEdgeToGraph(String src, String dest,int weight,Map<String, Map<String, Integer>> graph) {
        if(!graph.containsKey(src)){
            addVertex(graph,src);
        }
        if(!graph.containsKey(dest)){
            addVertex(graph,dest);
        }
        if(!graph.get(src).containsKey(dest)){
            graph.get(src).put(dest,weight);
        }      
        if(!graph.get(dest).containsKey(src)){
            graph.get(dest).put(src,weight);
        }
    }

    private void addVertex(Map<String,Map<String,Integer>> graph,String vertex) {
        graph.putIfAbsent(vertex, new HashMap<String,Integer>());
    }

    private void removeEdge(String src, String dest,Map<String,Map<String,Integer>> graph) {
        if(graph.get(src).containsKey(dest)){
            graph.get(src).remove(dest);
        }
        if(graph.get(dest).containsKey(src)){
            graph.get(dest).remove(src);
        }
    }

}