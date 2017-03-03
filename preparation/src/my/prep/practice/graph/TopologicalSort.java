package my.prep.practice.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
/* Topological Sort in a Directed Graph*/
public class TopologicalSort {
	
	public static void main(String[] agrs) {
		
	}
	public static List<Integer> getTopologocalOrderDFS(Graph<Integer> g) {
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for(GraphNode<Integer> v : g.getAllVertecies()) {
			if(!visited.contains(v.getVertex())) {
				topologicalHelper(v,visited,stack);
			}
		}
		
		List<Integer> topologicalList = new ArrayList<Integer>();
		while(!stack.isEmpty()) {
			topologicalList.add(stack.pop());
		}
		return topologicalList;
	}
	
	private static void topologicalHelper(GraphNode<Integer> vertex,HashSet<Integer> visited,Stack<Integer> stack) {
		visited.add(vertex.getVertex());
		for(Edge e : vertex.getEdges()) {
			if(!visited.contains(e.toNode().getVertex())) {
				topologicalHelper(e.toNode(),visited,stack);
			}
		}
		stack.push((vertex.getVertex()));//the stack is going to hole the topological sort
	}
	
	
	public static List<Integer> getTopologicalOrderBFS(Graph<Integer> g) {
		List<Integer> tsortList = new LinkedList<Integer>();
		Queue<GraphNode<Integer>> q =  new LinkedList<GraphNode<Integer>>();
		List<GraphNode<Integer>> nodes = getVertexWithNoInDegree(g);
		
		for(GraphNode<Integer> n:nodes) {
			if(!q.offer(n)) {
				throw new RuntimeException("couldn't able to add in processing queue.");
			}
		}
		
		while(!q.isEmpty()){
			GraphNode<Integer> gnode = q.poll();
			tsortList.add(gnode.getVertex());
			for(Edge edge : gnode.getEdges()) {
				g.removeEdge(edge);
				GraphNode<Integer> child = edge.toNode();
				if(getInDegree(child) ==0) {
					q.offer(child);
				}
			}
		}
		
		if(g.getEdgeCount() > 0)
			throw new RuntimeException("Graph has cycle");
		return tsortList;
	}
	
	private static List<GraphNode<Integer>> getVertexWithNoInDegree(Graph<Integer> g) {
		List<GraphNode<Integer>> list = new ArrayList<GraphNode<Integer>>();
		return list;
	}
	
	private static int getInDegree(GraphNode<Integer> gnode) {
		//TODO: Need to implement
		return 0;
	}
}
