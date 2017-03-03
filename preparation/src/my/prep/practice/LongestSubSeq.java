package my.prep.practice;

import java.util.Arrays;

/*
 * The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that 
 * all elements of the subsequence are sorted in increasing order. For example, 
 * length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 * 
 * LIS(i) = Longest sub seq ending at ith location is Max of all sub seq ending at i-1 location + 1
 * 
 * LIS(i)  Max{ LIS(j) } + 1 
 *         j<i
 *  
 *  Now we need to select max sub sequence
 *  
 *  Max (LIS(i))
 *  0<=j<=n
 *  n = total number of elements in the array.
 *  
 *  Time complexcity O(n^2)
 */
public class LongestSubSeq {

	public static void main(String[] args) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		Result r = longestSubSequence( arr );
		System.out.println("Length of lis is " + r.b + "\n" );
		printSequence((Integer[])r.a,(Integer)r.b,arr);
		
		System.out.println("Length of lis is (recursive) " + LIS(arr) + "\n" );
		
//		int A[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
//		r = longestSubSequence( A );
//		System.out.println("Length of lis is " + r.b + "\n" );
//		printSequence((Integer[])r.a,(Integer)r.b,A);
//		
//		r = longestSubSeqNew(A);
//		
//		System.out.println("Length of lis is " + r.c + "\n" );
//		printSequenceNew((Integer[])r.a,(Integer[])r.b,A,(Integer)r.c);
//		
//		int B[] = { 25, 20, 18,15 , 14, 12, 8, 5, 2 };
//		r = longestSubSeqNew(B);
//		
//		System.out.println("Length of lis is " + r.c + "\n" );
//		printSequenceNew((Integer[])r.a,(Integer[])r.b,B,(Integer)r.c);
//		
		
		
		
		
	}
	//O(n!)
	public static int LIS(int[] arr) {
		if(arr== null) throw new IllegalArgumentException("arr can not be null");
		return LISHelper(arr,0,arr.length-1);
	}
	private static int LISHelper(int[] arr,int startIndex,int endIndex) {
		if(startIndex == endIndex) return 1;
		int lastElementIndex = endIndex;
		int maxLis = 0;
		while(startIndex < lastElementIndex) {
			if(arr[endIndex] > arr[lastElementIndex-1]) {
				maxLis = Math.max(maxLis, LISHelper(arr,startIndex,lastElementIndex-1 )+1);
			}
			lastElementIndex --;
		}
		return maxLis;
		
	}
	
	public static Result longestSubSequence(int[] a) {
		
		Integer[] lis = new Integer[a.length]; //this will keep lis of all elements ending at i where 0<=i<a.length
		
		//every element has at least 1, 0 sub seq and itself 0+1=1
		for (int i =0;i<lis.length;i++) lis[i] =1;
		
		for(int i=1;i<a.length;i++) {
			
			for(int j=0;j<i;j++) {
				if(a[i] > a[j]) {
					//means i is more then j, means ith element can contribute in longest sub seq ending at jth place
					//But we need to pick the one which has max LIS
					//Pick max of LIS ending at ith place and LIS ending at jth place + 1
					lis[i] = Math.max(lis[i],(lis[j]+1));
				}
			}
			
		}
		
		//Now we have lis for all element but we need pick max
		Integer max=0;
		
		for(int i=0;i<lis.length;i++)
			max=Math.max(max, lis[i]);
		
		//This max is the length of LIS
		return new Result<Integer[],Integer,Integer>(lis, max);
	}
	
	/**
	 * Using patience sorting used in Card game Solitare
	 * http://chandermani.blogspot.com/2012/05/alogorithm-finding-longest-increasing.html
	 * O(N)
	 * */
	public static Result  longestSubSeqNew(int[] a) {
		Integer[] backPointer = new Integer [a.length];
		Integer[] table = new Integer[a.length];
		table[0] = a[0]; // this table is going to have longest sub sequence up to "len" index.
		Integer len = 1; // this always refer to the empty slot.
		backPointer[0] = 0;
		
		for(int i=1; i<a.length;i++) {
			
			if(a[i]<table[0]) {
				//replace it
				table[0]= a[i];
				backPointer[i] =0;
			}else if(a[i] > table[len-1]) {
				//next element from array is greater then last max element 
				//then add it 
				table[len] = a[i];
				backPointer[i] = table[len -1];
				len++;
			}else {
				// in between
				//find element which is just greater then ith element and replace that element with ith.
				//any new element from array 
				int index = getNextIndex(table,0,len-1,a[i]) ;
				table[index] = a[i]	;
				backPointer[i] = table[index-1];
			}
		}
		return new Result<Integer[],Integer[],Integer>(backPointer, table,len);
		
	}
	
	private static int getNextIndex(Integer[] table,int left,int right,int val) {
		//until left approaches to right -1
		//and right is going be to > val and left < val
		//left<val<right.
		while(right - left > 1) {
			int mid = left + (right-left)/2;
			if (table[mid] >=val) 
				right = mid;
			else 
				left = mid;
		}
		
		return right;
	}
	
	public static void printSequenceNew(Integer[] backPointer,Integer[] table,int[] arr,int lisLength) {
		StringBuilder str = new StringBuilder("[");
		int val = table[lisLength-1];
		
		while(val !=0) {
			str.append(val);
			str.append(",");
			int index = getIndex(arr, val);
			val = backPointer[index];
			
			
		}
		str.append("]");
		
		System.out.println("Sequence is "+str.toString());
	}
	
	private static int getIndex(int[] a, int val) {
		for (int i=0;i<a.length;i++) {
			if(a[i]==val) {
				return i;
			}
		}
		return -1;
	}
	public static void printSequence(Integer[] lis,Integer max,int[] arr) {
		//get the index of max LIS from LIS array
		//the index will give us the index of the element where the LIS ended
		int index=getMaxIndex(lis,lis.length-1, max);
		
		StringBuilder str = new StringBuilder("[");
		//opp intil we reach index = 0
		while(index >=0) {
			str.append(arr[index]);
			str.append(",");
			//now find out index of LIS which is 1 less then LIS and we need to scan LIS array only upto the index-1
			
			index=getMaxIndex(lis, index-1, --max);
		}
		str.append("]");
		
		System.out.println("Sequence is "+str.toString());
	}
	
	private static int getMaxIndex(Integer[] a,int end,Integer max) {
		
		for(int i=0;i<=end;i++)
			if(a[i] == max) return i;
		
		return -1;
	}
	
}
