package my.prep.practice.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//Convert from WORD1->WORDN by changing a single letter at a time.
//Dict is provided.
public class WordLadder {

	
	public static void main(String[] args) {
		String source = "hit";
		String target = "cog";
		String[] words = {"hit","hot", "dot", "dog", "lot", "log","cog"};
		Set<String> wordSet = new HashSet<String>();
		for(String w : words) {
			wordSet.add(w);
		}
		System.out.println("Shortest path is "+findShortestPath(source,target,wordSet));
		System.out.println("Shortest (2WBFS) path is "+findShortestPath2WayBFS(source,target,wordSet));
	}
	// l is the length of each word
	// N is the number of words in the dict
	// for each word we are generating 26l words 
	
	//Find a word from dict = O(26l log(N)) = l log(N)
	//How many word you can generate in worst case = 26 ^ l
	public static List<String> findShortestPath(String start, String end, Set<String> dict) {
		
		if(start.equals(end)) return Collections.EMPTY_LIST;
		
		if(!dict.contains(start) || !dict.contains(end) ) throw new IllegalArgumentException();
		
		Map<String,String> parentMap = new HashMap<String,String>();
		
		HashSet<String> visited = new HashSet<String>();
		
		Queue<String> queue = new LinkedList<String>();
		
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			String nextWord = queue.poll();
			
			if(nextWord.equals(end)) {
				//we found 
				break;
			}
			
			char chArray[]= nextWord.toCharArray();
			for(int i=0;i<chArray.length;i++) {
				
				char c = chArray[i];
				
				for(int alphabet='a'; alphabet <='z';alphabet++) {
					
					
					
					if(alphabet == c) continue;
					chArray[i] = (char)alphabet;
					String word = new String(chArray);
					if(dict.contains(word)) {
						//its a valid word
						if(!visited.contains(word)) {  //<-- OR YOU CAN REPLACE WITH dict.remove(word)
							//not looked before
							queue.offer(word);
							parentMap.put(word,nextWord);
							visited.add(word);
						}
					}
				}
				chArray[i] = c;
			}
		}
		List<String> ladder = new ArrayList<String>();
		
		if(parentMap.containsKey(end)) {
			constructPath(start, end, parentMap, ladder);
			return ladder;
		}
		
		return Collections.emptyList();
	}
	
	private static void constructPath(String start,String end,Map<String,String> parentMap, List<String> path) {
		String p = parentMap.get(end);
		if(p == null) {
			System.out.println(start + "-" + end);
		}
		if(p.equals(start)) {
			path.add(p);
			
		}else{
			constructPath(start, p, parentMap, path);
				
		}
		
		path.add(end);
		
	
	}
	
	public static List<String> findShortestPath2WayBFS(String start, String end, Set<String> dict) {
		if(start.equals(end)) return Collections.EMPTY_LIST;
		
		if(!dict.contains(start) || !dict.contains(end) ) throw new IllegalArgumentException();
		
		HashSet<String> visited = new HashSet<String>();
		
		HashSet<String> rVisited = new HashSet<String>();
		
		Queue<NodeLevel> queue = new LinkedList<NodeLevel>();
		
		Queue<NodeLevel> rqueue = new LinkedList<NodeLevel>();
		
		queue.offer(new NodeLevel(start,1));
		
		rqueue.offer(new NodeLevel(end,1));
		
		Map<String,String> parentMap = new HashMap<String,String>();
		
		Map<String,String> rParentMap = new HashMap<String,String>();
		
		
		List<String> ladder = new ArrayList<String>(); // to store the sequence of conversion
		int level = 1;
		int rlevel = 1;
		
		boolean found = false;
		while(!queue.isEmpty() && !rqueue.isEmpty() && !found) {
			
			
			if(queue.size() < rqueue.size()) {
				//process this queue
				while(!queue.isEmpty() && !found && queue.peek().level == level) { // make sure we process level by level
					String word = queue.poll().value;
					
					if(word.equals(end)) {
						found = true;
						break;
					}
					List<String> nextWordList = getNextWords(word,dict);
					for(String w: nextWordList) {
						if(!visited.contains(w)) {
							visited.add(w);
							if(rVisited.contains(w)) {
								//means we are done, from front->this word we have traced the path starting from source word and from end to this word we have traced from end word to this word.
								found=true;
								constructPath(start, word, parentMap, ladder);
								List<String> endPart = new ArrayList<String>();
								constructPath(end, w, rParentMap, endPart);
								Collections.reverse(endPart);
								ladder.addAll(endPart);
								break;
							}else{
								queue.offer(new NodeLevel(w, level + 1));
								parentMap.put(w, word);
							}
							
							
						}
					}
					
					
				}//end while
				level++;
			}else{
				
				while(!rqueue.isEmpty() && !found && rqueue.peek().level == rlevel) { // make sure we process level by level
					String word = rqueue.poll().value;
					
					List<String> nextWordList = getNextWords(word,dict);
					for(String w: nextWordList) {
						if(!rVisited.contains(w)) {
							rVisited.add(w);
							if(visited.contains(w)) {
								//means we are done, from end->this word we are trace the path and from start to this word traced by front
								found=true;
								//now we need to move parent->child pair from rParemtMap to parentMap
								constructPath(start, w, parentMap, ladder);
								List<String> endPart = new ArrayList<String>();
								constructPath(end, word, rParentMap, endPart);
								Collections.reverse(endPart);
								ladder.addAll(endPart);
								
								break;
							}else{
								rqueue.offer(new NodeLevel(w, rlevel + 1));
								rParentMap.put(w, word); // remember this, this is exactly opposite of flow coming from start -> end
							}
							
							
						}
					}
					
					
				}//end while
				rlevel++;
				
			}
		}
		
		
		if(!found)return Collections.emptyList();
		
		//constructPath(start, end, parentMap, ladder);
		return ladder;
		
		
		
	}
	
	private static List<String> getNextWords(String word, Set<String> dict) {
		
		List<String> wordList = new ArrayList<String>();
		char chArray[]= word.toCharArray();
		for(int i=0;i<chArray.length;i++) {
			
			char c = chArray[i];
			
			for(int alphabet='a'; alphabet <='z';alphabet++) {
				
				
				
				if(alphabet == c) continue;
				chArray[i] = (char)alphabet;
				String nextword = new String(chArray);
				if(dict.contains(nextword)) {
					wordList.add(nextword);
				}
			}
			chArray[i] = c;
		}
		
		return wordList;
	}
	
	public static List<List<String>> findAllShortestPaths(String start, String end, Map<String,String> dict) {
		
		return Collections.EMPTY_LIST;
	}
	
	private static class NodeLevel {
		private String value;
		private int level;
		
		public NodeLevel(String value, int level) {
			this.value = value;
			this.level = level;
		}
		
		public String toString() {
			return value;
		}
	}
}
