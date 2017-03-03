package my.prep.practice.graph;

public class Edge<T> {
	private GraphNode<T> node1;
	private GraphNode<T> node2;
	private int weight = 1; // default
	
	public Edge(GraphNode<T> n1, GraphNode<T> n2) {
		this.node1 = n1;
		this.node2 = n2;
	}
	
	public Edge(GraphNode<T> n1, GraphNode<T> n2,int weight) {
		this.node1 = n1;
		this.node2 = n2;
		this.weight = weight;
	}
	
	public GraphNode<T> fromNode() {
		return node1;
		
	}
	
	public GraphNode<T> toNode() {
		return node2;
	}
	
	public int getWeight() {
		return weight;
	}
	public boolean isBetween(GraphNode<T> node1, GraphNode<T> node2) {
		return (this.node1 == node1 && this.node2 == node2);
	}
}
