import java.util.*;

public class WelshPowellAlgorithm {
    public static void main(String [] args){

        Graph graph = new Graph();
        
        graph.addEdge("A","B");
        graph.addEdge("A","C");
        graph.addEdge("B","C");
        graph.addEdge("C","D");
        graph.addEdge("D","E");
        graph.addEdge("D","F");
        graph.addEdge("D","G");
        graph.addEdge("E","F");
        graph.addEdge("F","G");

        graph.WelshPowell();
    }
}

class Graph{
	private Map<String, List<String>> adjVertices= new HashMap<>();

    public void WelshPowell(){
        Map<String,String> vertexColor = new HashMap<>();
        Map<String, Integer> vertexList = new HashMap<>();
        for (String string : adjVertices.keySet()) {
            vertexList.put(string,adjVertices.get(string).size());
            vertexColor.put(string,null);
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(vertexList.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortedList);
        int i=0;
        for (Map.Entry<String,Integer> entry : sortedList) {
            String node = entry.getKey();
            if(!vertexColor.get(node).equals(Integer.toString(i)) && !adjVertices.get(node).contains(Integer.toString(i))){
                vertexColor.put(node,Integer.toString(i));
                sortedList.remove(entry);
            }
        }
        
    }

    public void addEdge(String src, String dest) {
        if(!adjVertices.containsKey(src)){
            addVertex(src);
        }
        if(!adjVertices.containsKey(dest)){
            addVertex(dest);
        }
        adjVertices.get(src).add(dest);
        adjVertices.get(dest).add(src);
    }

    private void removeEdge(String u, String v) {
        adjVertices.get(u).remove(v);
        adjVertices.get(v).remove(u);
    }

    public void addVertex(String vertex) {
        adjVertices.putIfAbsent(vertex, new ArrayList<>());
    }

    public Map<String, List<String>> getAdj() {
		return adjVertices;
	}

    
}
