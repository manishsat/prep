package my.prep.practive.dp;

import java.util.Arrays;

/*
 * Given an array of integers where each element represents the max number of steps that can be made forward from that element. 
 * Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
 * If an element is 0, then cannot move through that element.
 *
 * MinJump(i,j) = {		INT_MAX #if A[0] == 0
 * 						1  #i==j  
 * 						
 * 						i   
 * 							Min(minjump(k,j))  #if A[k] > j-k
 * 						k=j-1 		
 * 						
 * 						INT_MAX                #Else 						
 * 				} 
 */





public class MinJumps {
    //O(n!)
	public static int minjump(int arr[],int start,int end) {
		//base condition
		if(start == end -1) return 1; // you have reached 1 step before the last step you just need to one more step to reach to an end.
		
		if(arr[start] ==0) return Integer.MAX_VALUE;
		
		if(start == end) return 0;
			
		int min = Integer.MAX_VALUE;
		for(int j=end-1;j>start;j--) {
			if(arr[j] >= (end-j)) {
				min = Math.min(min, minjump(arr, start, j));
			}
		}
		return 1+min;
	}
	//O(N^2)
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
		System.out.println(Arrays.toString(arr));
		
		minJumps(arr);
		System.out.println("\nMin Jump (recursive) "+minjump(arr, 0, arr.length-1));
	}
}
