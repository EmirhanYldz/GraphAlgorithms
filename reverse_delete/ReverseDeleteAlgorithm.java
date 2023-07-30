import java.util.*;

public class ReverseDeleteAlgorithm{
    public static void main(String [] args){

    }
}

class Graph{
	private Map<String, Map<String, Integer>> adjVertices = new HashMap<>();
    private List<Edge> edges = new ArrayList<>();

    public List<Edge> getEdges() {
        return edges;
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

        edges.add(new Edge(src, dest, weight));

    }
    private void addEdgeMST(String src, String dest,int weight,Map<String,Map<String,Integer>> graph) {
        if(!graph.containsKey(src)){
            addVertex(graph,src);
        }
        if(!graph.containsKey(dest)){
            addVertex(graph,dest);
        }
        graph.get(src).put(dest,weight);
        graph.get(dest).put(src,weight);
    }

    private void addVertex(Map<String,Map<String,Integer>> graph,String vertex) {
        graph.putIfAbsent(vertex, new HashMap<String,Integer>());
    }

    private void removeEdge(String src, String dest,Map<String,Map<String,Integer>> graph) {
        graph.get(src).remove(dest);
        graph.get(dest).remove(src);
    }

}

class Edge{
    public String source;
    public String next;
    public int weight;

    Edge (String source,String next,int weight){
        this.source=source;
        this.next=next;
        this.weight=weight;
    }
}
