package my.prep.practice.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

//http://logicmazes.com/n2mz.html
//In number puzzle you start from top left and need to reach bottom right corner.
//You can move in all directions and you have to move by number written in that cell.
public class NumberPuzzle {

	public static void main(String[] agrs) {
		int[][] maze= { {6,3,2,4,6,2,5},
						{3,5,2,4,4,4,1},
						{3,3,2,3,3,4,2},
						{3,4,1,2,2,5,3},
						{4,4,4,2,3,2,4},
						{2,5,4,2,3,2,5},
						{3,5,2,1,4,4,0}
					  };
		
		int[][] maze2 = {	{2,2,1},
							{1,2,2},
							{2,2,0}
						};
		
		System.out.println(getSteps(maze, 0, 0));
	}
	
	public static List<Point> getSteps(int[][] maze,int startRowIndex, int startColIndex) {
		if(startRowIndex > maze.length || startColIndex > maze[0].length) {
			return null;
		}
		Queue<Point> queue = new LinkedList<Point>();
		Point firstPoint = new Point(startRowIndex,startColIndex,maze[startRowIndex][startColIndex]);
		queue.add(firstPoint);
		Set<Point> visitedSet = new HashSet<Point>();
		Map<Point,Point> parentMap = new HashMap<Point,Point>();
		
		visitedSet.add(firstPoint);
		parentMap.put(firstPoint, null);
		
		Point endPoint  = new Point(maze.length-1,maze[0].length - 1,maze[maze.length-1][maze[0].length-1]);
		boolean found = false;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.equals(endPoint)) {
				found = true;
				break;
			}
			for(Point nextPoint :getAllPossibleNextMoves(maze,p)) {
				if(!visitedSet.contains(nextPoint)) {
					
					queue.offer(nextPoint);
					visitedSet.add(nextPoint);
					parentMap.put(nextPoint,p);
				}
			}
			
		}
		
		if(found) {
			return getPath(parentMap,endPoint);
		}
		
		return null;
		
	}
	
	private static List<Point> getPath(Map<Point,Point> parentMap, Point endPoint) {
		Point p = endPoint;
		Stack<Point> stack = new Stack<Point>();
		stack.push(p);
		while(p!=null) {
			p = parentMap.get(p);
			if(p!=null) stack.push(p);
		}
		
		List<Point> list = new ArrayList<Point>();
		while(!stack.isEmpty()) {
			list.add(stack.pop());
		}
		
		return list;
	}
	
	private static List<Point> getAllPossibleNextMoves(int [][] maze, final Point p) {
		List<Point> allMoves = new ArrayList<Point>();
		if(p.col + p.value <= maze[0].length-1) {
			allMoves.add(new Point(p.row,p.col+p.value,
					maze[p.row][p.col + p.value]));
		}

		if(p.col + p.value <= maze[0].length-1) {
			allMoves.add(new Point(p.row,p.col+p.value,
					maze[p.row][p.col + p.value]));
		}

		if(p.col - p.value >= 0) {
			allMoves.add(new Point(p.row,p.col-p.value,
					maze[p.row][p.col - p.value]));
		}
		
		if(p.row + p.value <= maze.length-1) {
			allMoves.add(new Point(p.row+p.value,p.col,
					maze[p.row + p.value][p.col]));
		}

		if(p.row - p.value >= 0) {
			allMoves.add(new Point(p.row-p.value,p.col,
					maze[p.row - p.value][p.col]));
		}
		
		return allMoves;
	}
	
	
	
	
	private static class Point {
		int row;
		int col;
		int value;
		public Point(int row,int col,int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
		@Override
		public int hashCode() {
			Integer x = new Integer(row);
			Integer y = new Integer(col);
			Integer z = new Integer(value);
			return x.hashCode() + y.hashCode() + z.hashCode();
		}
		@Override
		public boolean equals(Object o) {
			if(this == o) return true;
			if(o == null)return false;
			if(!(o instanceof Point)) {
				return false;
			}
			
			Point other = (Point)o;
			if(other.row == this.row &&
					other.col == this.col &&
					other.value == this.value) {
				return true;
			}
			return false;
			
		}
		
		public String toString() {
			return new String("Point ["+ this.row  + "]" + "[" + this.col + "]");
		}
	}
}
