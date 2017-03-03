package my.prep.practice.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Graph<T> {
	//private List<GraphNode<T>> vertices;
	private Map<T,GraphNode<T>> vertices;
	private Map<T, TreeSet<GraphNode<T>>> adjacencyList;
	
	public Graph() {
		adjacencyList = new HashMap<T,TreeSet<GraphNode<T>>>();
	}
	
	public boolean addVertex(GraphNode<T> node) {
		if(vertices.containsKey(node.getVertex())) {
			return false;
		}
		
		
		adjacencyList.put(node.getVertex(), new TreeSet<GraphNode<T>>());
		
		return vertices.put(node.getVertex(),node) != null;
	}
	
	public GraphNode<T> getVertex(T vertex) {
		return vertices.get(vertex);
	}
	
	public boolean hasVertex(T vertex) {
		return vertices.containsKey(vertex);
	}
	
	public boolean hasEdge(T vertex1, T vertex2) {
		if(!hasVertex(vertex1) || !hasVertex(vertex2)) return false;
		
		return adjacencyList.get(vertex1).contains(vertex2);
	}
	
	public boolean hasEdge(Edge<T> edge) {
		Set<GraphNode<T>> neighbors = adjacencyList.get(edge.fromNode().getVertex());
		return neighbors.contains(edge.toNode());
	}
	
	
	public void removeEdge(Edge<T> edge) {
		adjacencyList.get(edge.fromNode().getVertex()).remove(edge.toNode());
	}
	public int getEdgeCount() {
		int edgeCount = 0;
		for(Map.Entry<T, TreeSet<GraphNode<T>>> entry : adjacencyList.entrySet()) {
			edgeCount+=entry.getValue().size();
		}
		
		return edgeCount;
	}
	
	public List<GraphNode<T>> getAllVertecies() {
		List<GraphNode<T>> list = new ArrayList<GraphNode<T>>();
		for(Map.Entry<T,GraphNode<T>> entry : vertices.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	
	public boolean addEdge(T fromVertex, T toVertex,int weight) {
		if(!hasVertex(fromVertex) || !hasVertex(toVertex)) return false;
		GraphNode<T> fromNode = getVertex(fromVertex);
		GraphNode<T> toNode = getVertex(toVertex);
		
		//first add the edge in the graph node and then add in the adj list implementation.
		
		return fromNode.addEdge(toNode) && adjacencyList.get(fromNode).add(toNode);
	}
	
	
}
