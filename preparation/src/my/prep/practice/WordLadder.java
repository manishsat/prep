package my.prep.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	
	public static ArrayList<ArrayList<String>> findAllPaths(String start,String end, HashSet<String> dict) {
		
		HashMap<String,ArrayList<String>> parentMap = new HashMap<String,ArrayList<String>>();
		
		if(start == end || start.equals(end)) {
			addOrUpdateParentList(start,end,parentMap);
			ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
			result.add(parentMap.get(start));
			return result;
		}
		
		Queue<String> queue = new LinkedList<String>();
		addOrUpdateParentList(start,start,parentMap);
		queue.offer(start);
		while(!queue.isEmpty()) {
			String word = queue.poll();
			
			if(word.equals(end)) {
				continue;//found the word no need to go forward from here
			}
			char[] chs = word.toCharArray();
			for(int i=0; i<chs.length;i++) {
				char oldChar = chs[i];
				for(char c = 'a'; c<='z';c++) {
					chs[i] = c;
					String nextWord = String.valueOf(chs);
					if(dict.contains(nextWord)) {
						//valid word
						
						queue.offer(nextWord);
						addOrUpdateParentList(nextWord,word,parentMap);
					}
				}
				chs[i] = oldChar; // put the char back;
			}
		}
		return parentPaths(start,end,parentMap);
	}
	
	private static void addOrUpdateParentList(String child, String parent, HashMap<String,ArrayList<String>> parentMap ) {
		assert(child !=null);
		assert (parent != null);
		assert (parentMap != null);
		if(!parentMap.containsKey(child)) {
			ArrayList<String> plist = new ArrayList<String>();
			plist.add(parent);
			parentMap.put(child, plist);
		}else{
			parentMap.get(child).add(parent);
		}
		
	}
	
	private static ArrayList<ArrayList<String>> parentPaths(String start, String end, HashMap<String,ArrayList<String>> parentMap) {
		ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
		if(start == end || start.equals(end)) {
			ArrayList<String> plist = new ArrayList<String>();
			plist.add(start);
			paths.add(plist);
			return paths;
		}else if(parentMap.containsKey(end)) {
			for(String s : parentMap.get(end)) {
				ArrayList<ArrayList<String>> list = parentPaths(start,s,parentMap);
				for(ArrayList<String> p : list) {
					p.add(end);
					paths.add(p);
				}
			}
		}
		return paths;
	}

}
