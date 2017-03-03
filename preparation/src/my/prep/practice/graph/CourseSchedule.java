package my.prep.practice.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1. 
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]. 
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example, given 2 and [[1,0]], there are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

For another example, given 2 and [[1,0],[0,1]], there are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * 
 * 
 * 
 * 
 * */
public class CourseSchedule {

	public static void main(String[] args) {
		int courses;
		int[][] preRequisites;
	}
	//This solution is using BFS
	// O(N) + e
	// e is number of edges, which actually in pCounter
	public static List<Integer> topologicalSort(int courses,int[][] preRequisites ) {
		
		if(preRequisites == null) throw new IllegalArgumentException("pre requisites array is empty");
		if(courses == 0 ) return Collections.EMPTY_LIST;
		
		
		int[] pCounter = new int[courses]; // a preRequisites counter,, how many pre-requisite are there to start  a particular course
		
		for(int i=0;i<preRequisites.length;i++) {
			int courseId = preRequisites[i][0];
			pCounter[courseId]++;
		}
		
		
		List<Integer> tsort = new ArrayList<Integer>();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for(int i=0; i < pCounter.length;i++) {
			if(pCounter[i]==0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int courseId = queue.poll();
			tsort.add(courseId);
			
			for(int i=0;i<courses;i++) {
				pCounter[preRequisites[i][1]]--;
				if(pCounter[preRequisites[i][0]]==0) {
					queue.offer(i);
				}
			}
		}
		
		return (tsort.size() == courses) ? tsort : Collections.EMPTY_LIST;
	}
	
	
}
