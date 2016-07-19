package my.prep.practice;
/*
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. 
 * Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
 * If an element is 0, then cannot move through that element.
 * 
 * O(N^2)
 * */
public class MinJumps {

	public static void minJumps(int[] a) {
		int[] jumps = new int[a.length];
		jumps[0] = 0; // we need 0 jumps to reach first element 
		int[] track = new int[a.length];
		for(int i=1;i<a.length;i++) { // looping thru all the elements starting from 1.
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for(int j=0; j <i;j++) {
				//try for every element j such that j <i and i <= a[j] + j 
				//what it means is a[j] has enough steps to reach ith element and we need min of them
				if(i <= a[j] + j) {
					if(jumps[j]+1 < min) {
						min = jumps[j]+1;
						minIndex = j;
					}
					
					
				}
			}
			jumps[i] = min;
			track[i] = minIndex;
			
			
		}
		System.out.println("Min jump req are "+jumps[a.length-1]);
		int index=a.length-1;
		while(index!=0) {
			System.out.print(a[index]+"->");
			index = track[index];
		}
		System.out.print(a[index]);
	}
	
	public static void main(String[] args) {
		int arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		minJumps(arr);
	}
}
