import java.util.*;

public class PrimAlgorithm{
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


    }
}


class Graph{
	private Map<String, Map<String, Integer>> adjVertices = new HashMap<>();

    public void Prim(){
        Map<String, Map<String, Integer>> MST = new HashMap<>();
        Map<String, Map<String, Integer>> copyAdjVertices = new HashMap<>();
        copyAdjVertices = adjVertices;
        Set<String> visited = new HashSet<>();


        Set<String> keySet=adjVertices.keySet();
        Iterator<String> it=keySet.iterator();
        String node = it.next();

        addVertex(MST, node);
        for(int i =0;i<adjVertices.keySet().size();i++){
            
        }

    }

    private void PrimUtil(Map<String,Map<String,Integer>> graph,Map<String,Map<String,Integer>> copy){
        int cost = Integer.MAX_VALUE;
        String currentNode = null;
        String edgeNode = null;
        for (String string : graph.keySet()) {
            currentNode = string;
            edgeNode = getCloseVertex(string,edgeNode,cost,copy);
        }
        addEdge(edgeNode, edgeNode, cost);
    }



    private String getCloseVertex(String node ,String edgeNode,int cost,Map<String,Map<String,Integer>> graph){
        for (String string : graph.get(node).keySet()) {
            if(graph.get(node).get(string)<cost){
                edgeNode = string;
                cost = graph.get(node).get(string);
            }
        }
        return edgeNode;
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
    public void addEdgeToMST(String src, String dest,int weight,Map<String, Map<String, Integer>> graph) {
        if(!graph.containsKey(src)){
            addVertex(graph,src);
        }
        if(!adjVertices.containsKey(dest)){
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
