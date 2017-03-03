package my.prep.practice.graph;

import java.util.List;

public class GraphNode<T> {
	private T vertex;
	private List<Edge<T>> edges;
	
	public boolean isVisited;
	
	public void setVisited(boolean visited) {
		this.isVisited = visited;
	}
	
	public GraphNode(T vertex) {
		this.vertex = vertex;
	}
	
	public T getVertex() {
		return vertex;
	}
	
	private boolean hasEdge(GraphNode<T> node) {
		for(Edge<T> edge:edges) {
			if(edge.isBetween(this, node)) return true;
		}
		return false;
	}
	
	public boolean hasEdge(Edge<T> edge) {
		return edges.contains(edge);
	}
	public boolean addEdge(int weight, GraphNode<T> node) {
		if(!hasEdge(node)) {
			Edge<T> edge = new Edge<T>(this,node,weight);
			return edges.add(edge);
		}
		return false;
	}
	public boolean addEdge(GraphNode<T> node) {
		return addEdge(1,node);
	}
	
	public boolean isVisited() {
		return isVisited;
	}
	
	public List<Edge<T>> getEdges() {
		return edges;
	}
	
	public void removeEdge(Edge<T> edge) {
		if(hasEdge(edge)) {
			edges.remove(edge);
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		
		GraphNode<T> other = (GraphNode<T>)obj;
		
		if(this.vertex.equals(other.vertex)) return true;
		
		return false;
		
		
	}
}
