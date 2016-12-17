package my.prep.practice.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphUtil {
	public static void main(String[] args) {
		
	}
	
	public static void BFS(Graph<Integer> g,Integer vertex) {
		GraphNode<Integer> startVertex = g.getVertex(vertex);
		if(startVertex == null || g == null) return;
		
		
		Queue<GraphNode<Integer>> queue = new LinkedList<GraphNode<Integer>>();
		queue.offer(startVertex);
		startVertex.setVisited(true);
		while(!queue.isEmpty()) {
			GraphNode<Integer> node = queue.poll();
			visitNode(node);
			// not visited
			for(Edge edge:node.getEdges()) {
				if(!edge.toNode().isVisited()) {
					queue.offer(edge.toNode());
					edge.toNode().setVisited(true);
				}
					
			}
			
		}
		
	}
	
	public static void DFS(Graph<Integer> g,Integer vertex) {
		GraphNode<Integer> startVertex = g.getVertex(vertex);
		if(g == null || startVertex == null) return;
		visitNode(startVertex);
		startVertex.setVisited(true);
		for(Edge edge:startVertex.getEdges()) {
			if(!edge.toNode().isVisited()) {
				edge.toNode().setVisited(true);
				DFS(g,(Integer)edge.toNode().getVertex());
			}
		}
	}
	
	private static void visitNode(GraphNode gnode) {
		System.out.println(gnode.getVertex());
	}
}
