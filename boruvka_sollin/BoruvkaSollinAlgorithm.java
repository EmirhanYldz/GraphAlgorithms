import java.util.*;

public class BoruvkaSollinAlgorithm{
    public static void main(String [] args){
        
    }
}


class Graph{
	private Map<String, Map<String, Integer>> adjVertices = new HashMap<>();

    public void Boruvka(){
        Map<String, Map<String, Integer>> MST = new HashMap<>();
        Map<String, Map<String, Integer>> copyAdjVertices = new HashMap<>();
        copyAdjVertices = adjVertices;

        for (String iterable_element : iterable) {
            
        }
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

    private void addVertex(Map<String,Map<String,Integer>> graph,String vertex) {
        graph.putIfAbsent(vertex, new HashMap<String,Integer>());
    }

}