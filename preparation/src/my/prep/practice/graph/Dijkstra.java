package my.prep.practice.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 
    It maintains a list of unvisited vertices.
    It chooses a vertex (the source) and assigns a maximum possible cost (i.e. infinity) to every other vertex.
    The cost of the source remains zero as it actually takes nothing to reach from the source vertex to itself.
    In every subsequent step of the algorithm it tries to improve(minimize) the cost for each vertex. Here the cost can be distance, money or time taken to reach that vertex from the source vertex. The minimization of cost is a multi-step process.
        For each unvisited neighbor (vertex 2, vertex 3, vertex 4) of the current vertex (vertex 1) calculate the new cost from the vertex (vertex 1).
        For e.g. the new cost of vertex 2 is calculated as the minimum of the two ( (existing cost of vertex 2) or (sum of cost of vertex 1 + the cost of edge from vertex 1 to vertex 2) )
    When all the neighbors of the current node are considered, it marks the current node as visited and is removed from the unvisited list.
    Select a vertex from the list of unvisited nodes (which has the smallest cost) and repeat step 4.
    At the end there will be no possibilities to improve it further and then the algorithm ends

 * 
 * 
 * */
public class Dijkstra {

	
	public static void main(String[] args) {
		Graph<Integer> g = new Graph<Integer>();
		List<GraphNode<Integer>> vertices =  g.getAllVertecies();
		
		
	}
	//a shortest path from a source node to every other node
	public static Map<GraphNode<Integer>,GraphNode<Integer>> doDijkstra(GraphNode<Integer> sourceVertex,
			List<GraphNode<Integer>> vertices) {
		
		Map<Integer,Integer> distance = new HashMap<Integer,Integer>();
		
		Map<GraphNode<Integer>,GraphNode<Integer>> pathMap = new  HashMap<GraphNode<Integer>,GraphNode<Integer>>();
		
		
		PriorityQueue<NodeDistance> pq = new PriorityQueue<NodeDistance>();
		for(GraphNode<Integer> gnode : vertices) {
			distance.put(gnode.getVertex(), Integer.MAX_VALUE);
			pq.add(new NodeDistance(Integer.MAX_VALUE, gnode.getVertex(),gnode));
		}
		pq.add(new NodeDistance(0, sourceVertex.getVertex(),sourceVertex));
		distance.put(sourceVertex.getVertex(),0);
		while(!pq.isEmpty()) {
			
			NodeDistance nd = pq.poll();
			GraphNode<Integer> node = nd.getNode();
			for(Edge edge : node.getEdges()) {
				GraphNode<Integer> neighborNode = edge.toNode();
				Integer d = distance.get(neighborNode.getVertex());
				
				int tempDistance = nd.distance + edge.getWeight();
				if(tempDistance < d) {
					NodeDistance nDistance = new NodeDistance(tempDistance,neighborNode.getVertex(),neighborNode);
					pq.remove(nDistance);
					pq.add(nDistance);
					distance.put(neighborNode.getVertex(),tempDistance);
					pathMap.put(neighborNode, node);
				}
			
			}
			
		}
		
		return pathMap;
		
	}
	
	private static  class NodeDistance implements Comparable<NodeDistance> {
		
		private Integer distance;
		private int vertex;
		private GraphNode<Integer> node;
		public NodeDistance(int distance,int vertex,GraphNode<Integer> node) {
			this.distance = distance;
			this.vertex = vertex;
			this.node = node;
		}
		public GraphNode<Integer> getNode() {
			return node;
		}
		public boolean equals(Object other) {
			if(other == null) return false;
			
			if(other == this) return true;
			
			if(!(other instanceof NodeDistance)) return false;
			
			NodeDistance o = (NodeDistance) other;
			if(o.vertex == vertex) return true;
			return false;
			
		}
		@Override
		public int compareTo(NodeDistance o) {
			return distance.compareTo(o.distance);
		}
	}
}
