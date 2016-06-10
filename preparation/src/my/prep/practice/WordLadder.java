package my.prep.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
 * Given two words (start and end), and a set of words (a set)
 * Find the length of shortest transformation sequence from start to end, such that: 
 * 1.Only one letter can be changed at a time
 * 2. Each intermediate word must exist in a given set
 */
public class WordLadder {
	
	/***
	 * Very important things, we don't need to create a graph but treat this problem as a graph
	 * problem and traverse BFS, BFS can give the optimum path
	 * The words array is a kind of giving you edges you can draw from previous word to the
	 * next word.
	 * 
	 * Constraints: We can only change 1 char
	 * 
	 * O(LN^2)
	 * L= Length of the word (source)
	 * N = Number of words in the words set
	 * 
	 * Reference: http://algs4.cs.princeton.edu/41graph/WordLadder.java.html
	 * Reference : http://web.stanford.edu/class/archive/cs/cs106b/cs106b.1134/handouts/15-Assignment2.pdf
	 * 
	 * */
	
	public static Map findShortestPath(String source,String target,String[] words) {
		if(source.equals(target)) {
			
			Map m = new HashMap<String,String>();
			m.put(target, source); //we can reach from source->target without hop
			return m;
		}
		
		Queue<String> q = new LinkedList<String>(); //processing queue
		Map parentMap = new HashMap<String,String>(); // this map is going to hold a reference to the parent.
		boolean found = false;
		q.add(source);
		parentMap.put(source, null);
		while(q.peek()!= null && !found) {
			String word = q.poll();
			
			if(target.equals(word)) {
				found = true;
				
			}else{
				for(String s : words) {
					if(getDiff(s,word) == 1) {
						if(!parentMap.containsKey(s)) {
							parentMap.put(s, word);
							q.add(s);//add the next word from previous word in the queue
						}
					}
				}
			}
		}
		
		return (found) ? parentMap: null;
		
	}
	
	public static int getDiff(String s1, String s2) {
		if(s1.length()!=s2.length()) {
			return -1; 
		}
		int diff = 0;
		for(int i=0;i<s1.length();i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				diff++;
			}
		}
		
		return diff;
	}
	
	public static void main(String[] args) {
		String source = "CAT";
		String target = "BAT";
		String[] words = {"CAT", "BAT", "COT", "COG", "COW", "RAT", "BUT", "CUT", "DOG", "WEB"};
		Map<String,String> path = findShortestPath(source, target, words);
		printPath(path,target);
		source = "CAT";
		target = "DOG";
		path = findShortestPath(source, target, words);
		printPath(path,target);
	}
	public static void printPath(Map<String,String> path,String target) {
		if(path == null) {
			System.out.println("No path");
		
		}else{
			String s = path.get(target);
			while(s!=null) {
				System.out.println(s + "->"+target);
				target = s;
				s = path.get(target);
			}
		}
	}

}
