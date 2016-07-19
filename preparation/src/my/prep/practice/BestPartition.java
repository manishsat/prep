package my.prep.practice;

/*
 * http://articles.leetcode.com/the-painters-partition-problem/
 */
public class BestPartition {
	
	private static int sum(int[] arr,int startIndex,int endIndex) {
		int s=0;
		for(;startIndex<=endIndex;startIndex++) s+=arr[startIndex];
		return s;
	}
	/**
	 *   
	 * @param n is the block for painters to paint.
	 * @param k no of painters available to paint n blocks. 
	 */
	public static int partitionBad(int[] arr,int n,int k,int startIndex) {
		//Base case 1
		if(k==1) {
			return sum(arr,startIndex,n-1);		
		}
		// we have only 1 block to paint and it has to contiguous 
		//irrespective of how many painters we have we can only paint the first wall.
		if(n==1) {
			return arr[startIndex];
		}
		
		//This become so complicated if you start partitioning from start of the array
		//like 1st partition at 0 and rest starts from 1st ... n
		//meaning first partition is up to i and best partition for i->n-1
		//so for i->n-1 partition you can not start from 0 so you have to keep start Index which make this algo complicated.
		int best = Integer.MAX_VALUE;
		for(int i=startIndex;i<n;i++) {
			best = Math.min(best, 
					Math.max(sum(arr,startIndex,i), partitionBad(arr, n, k-1,i+1)));
		}
		return best;
	}
	
	
	/**
	 *  This is better solution then above you always start from 0 so no need to keep startIndex argument
	 * @param n is the block for painters to paint.
	 * @param k no of painters available to paint n blocks. 
	 */
	public static int partition(int[] arr,int n,int k) {
		//Base case 1
		if(k==1) {
			return sum(arr,0,n-1);		
		}
		// we have only 1 block to paint and it has to contiguous 
		//irrespective of how many painters we have we can only paint the first wall.
		if(n==1) {
			return arr[0];
		}
		
		//Here thinking is little different but concept is same
		//partition k-1 upto i and from i to n-1 we have 1 partition
		//and keep increasing i so make i -> n-1 lower and lower each time
		int best = Integer.MAX_VALUE;
		for(int i=1;i<=n;i++) {
			best = Math.min(best, 
					Math.max(sum(arr,i,n-1), partition(arr, i, k-1)));
		}
		return best;
	}
	
	//O(kN^2)
	public static int partitionDP(int[] arr,int n,int k) {
		
		int[][] dptable = new int[k+1][n+1];
		int cum[] = new int[n+1];
		cum[0] = 0;
		
		for(int i=1;i<=n;i++) {
			cum[i] = cum[i-1] + arr[i-1]; 
		}
		
		for(int i=1;i<=n;i++) {
			dptable[1][i] = cum[i]; 
		}
		
		for(int i=1;i<=k;i++) {
			dptable[i][1] = arr[0];
		}
		
		for(int i=2;i<=k;i++) {
			
			for(int j = 2;j<=n;j++ ) {
				 //try all j position as k-1 partition
				int best = Integer.MAX_VALUE; 
				for (int x=1;x<=j;x++) {
					 best = Math.min(best, Math.max(cum[j]-cum[x], dptable[i-1][x]));
				 }
				dptable[k][j] = best;
			}
		}
		
		return dptable[k][n];
	}
	
	
	
	
	public static void main(String[] args) {
		int A[] = {100, 200, 300, 400, 500, 600, 700, 800, 900};
		System.out.println("largest boards to paint " + partition(A,A.length, 3));
		System.out.println("largest boards to paint " + partitionBad(A,A.length, 3,0));
		
		int B[] = {100, 200, 300};
		
		System.out.println("largest boards to paint " + partition(B,B.length, 2));
		System.out.println("largest boards to paint " + partitionBad(B,B.length, 2,0));
		System.out.println("largest boards to paint " + partitionDP(B,B.length, 2));
		
		
	}
	

}
