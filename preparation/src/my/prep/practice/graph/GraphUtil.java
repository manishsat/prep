package my.prep.practice.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;



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
				DFS(g,(Integer)edge.toNode().getVertex());
			}
		}
	}
	
	

	
	
	
	
	//Time: O(V+E)
	//Space : O(V) is worst case
	public static boolean isCycle(Graph<Integer> g) {
		
		Set<GraphNode<Integer>> currentVertexSet = new HashSet<GraphNode<Integer>>();
		Set<GraphNode<Integer>> visitedVertexSet = new HashSet<GraphNode<Integer>>();
		Set<GraphNode<Integer>> currentCallSTackVertexSet = new HashSet<GraphNode<Integer>>();
		
		for(GraphNode<Integer> gnode: g.getAllVertecies()) {
			currentVertexSet.add(gnode);
		}
		//We are removing from currentVesrtesSet  in the isCycleInTheGraph method and moving to visitedSet
		//So 1. we dont need to check isVisited here before calling "isCycleInTheGraph" method
		//2.Need to use iterator otherwise you will concurrent Modification Exception.
		for (Iterator<GraphNode<Integer>> i = currentVertexSet.iterator(); i.hasNext();) {
			if(isCycleInTheGraph(i.next(),currentVertexSet,visitedVertexSet,currentCallSTackVertexSet)) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
		
	}
	
	private static boolean isCycleInTheGraph(GraphNode<Integer> currentVertex,Set<GraphNode<Integer>> currentVertexSet,
			Set<GraphNode<Integer>> visitedVertexSet,
			Set<GraphNode<Integer>> currentCallSTackVertexSet) {
		
		moveVertex(currentVertex, currentVertexSet, currentCallSTackVertexSet);
		
		for(Edge edge :currentVertex.getEdges()) {
			if(currentCallSTackVertexSet.contains(edge.toNode())) {
				return Boolean.TRUE;
			}
			if(visitedVertexSet.contains(edge.toNode())) {
				continue;
			}
			if(isCycleInTheGraph(edge.toNode(),currentVertexSet,visitedVertexSet,currentCallSTackVertexSet)) {
				return Boolean.TRUE;
			}
		}
		moveVertex(currentVertex,currentCallSTackVertexSet,visitedVertexSet);
		return Boolean.FALSE;
	}
	private static void moveVertex(GraphNode<Integer> vertex, Set<GraphNode<Integer>> from,Set<GraphNode<Integer>> to ) {
		if(from.remove(vertex))
			if(to.add(vertex))
				return;
		throw new RuntimeException();
	}
	private static void visitNode(GraphNode gnode) {
		System.out.println(gnode.getVertex());
	}
}
