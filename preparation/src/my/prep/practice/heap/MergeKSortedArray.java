package my.prep.practice.heap;

import java.util.PriorityQueue;

//Merge K sorted array

public class MergeKSortedArray {

	public static void main(String[] agrs) 
	{
		
	}
	
	public int[] mergeKSortedArray(int[][] arrays) {
		
		//option1 is combine all array into one big array and sort it.
		//assume there are k arrays and each has 'n' elements
		// Time complexity O(kn log(kn)) -> merged sort
		
		//option 2. pick first element from each array and put in th PriorityQueue (which is nothing but a min heap).
	    // this way we can reduce time complexity to O(kn log k)
		int size = 0;
		for(int i=0;i<arrays.length;i++) {
			size = size + arrays[i].length;
		}
		
		int mergedArray[]= new int[size];
		
		PriorityQueue<ArrayNode> pq = new PriorityQueue<ArrayNode>();
		
		for(int i=0;i<arrays.length;i++) {
			pq.add(new ArrayNode(i,0,arrays[i][0]));
		}
		
		int mergeIndex=0;
		while(!pq.isEmpty()) {
			ArrayNode node = pq.poll();
			if(node !=null) {
				mergedArray[mergeIndex] = node.value;
			}
			pq.add(new ArrayNode(node.array,node.arrayIndex+1,arrays[node.array][node.arrayIndex+1]));
			mergeIndex++;
		}
		
		return mergedArray;
	}
	
	class ArrayNode implements Comparable<ArrayNode>{

		private int array;
		private int arrayIndex;
		private int value;
		
		public ArrayNode(int array,int arrayIndex, int value) {
			this.array = array;
			this.arrayIndex = arrayIndex;
			this.value = value;
		}
		@Override
		public int compareTo(ArrayNode o) {
			if(this.value > o.value) return 1;
			else if(this.value < o.value) return -1;
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}

