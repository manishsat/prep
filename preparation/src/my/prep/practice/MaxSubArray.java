package my.prep.practice;

/*
 * Write an efficient  program to find the sum of contiguous subarray within a one-dimensional array of numbers 
 * which has the largest sum. 
 * 
 */

public class MaxSubArray {
	
	/**
	 * V(i,j) = {V(i,j-1) + A[j]}
	 * 
	 * O(N^2)
	 * */

	public static int maxSubArray(int[] a) {
		if(a.length == 0) {
			System.out.println("Empty array !!");
			return Integer.MIN_VALUE;
		}
		int max = Integer.MIN_VALUE;
		for(int i=0;i<a.length;i++) {
			 int val = a[i];
			for(int j=i+1;j<a.length;j++) {
				val = val + a[j];
				max = Math.max(max, val);
			}
		}
		
		return max;
	}
	
	/**
	 * T(i) = Sum(A[x])
	 * 		  1<=X<=i
	 * 
	 * V(i,j) = max {
	 * 					T(j) - min {T(i-1) }
	 * 							1<=i<=j
	 * 				}
	 * 				
	 * 				
	 * In particular for fixed j we can find  maximal V(i,j) by finding i<=j with minimal value of T(i-1)	
	 * 
	 * O(N)
	 * */
	
	public static int maxSubArrayBetter(int[] a) {
		if(a.length == 0) {
			System.out.println("Empty array !!");
			return Integer.MIN_VALUE;
		}
		
		int V = a[0]; // this is the actual max sum
		int Tmin = a[0]; // this is to hold min sum 1 to i
		int total = a[0]; // this is to hold total 1 to j
		for (int i =1;i<a.length;i++) {
			
			total = total + a[i];
			if(total - Tmin > V) {
				V = total - Tmin; 
			}
			if(total < Tmin) Tmin = total;
		}
		
		return V;
	}
	
	/**
	 * S(i) = Max Sum of number up to ith element
	 * S(i) = Max (S(i-1)+A[i],A[i])
	 * The idea behind is if A[i] element is adding positive to the sum then include A[i] and extend our range.
	 * Otherwise either S(i-1) is the max sub array or we hope we find more positive element after ith element so keep A[i]
	 * */
	
	public static int maxSumArrayDP(int[] a) {
		if(a.length == 0) {
			System.out.println("Empty array !!");
			return Integer.MIN_VALUE;
		}
		int max = a[0];
		int sumUptoPrevious = a[0];
		for(int i=1;i<a.length;i++) {
			sumUptoPrevious = Math.max(a[i], sumUptoPrevious + a[i]);
			max = Math.max(max, sumUptoPrevious);
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println("Max sum of sub array is "+maxSubArray(a));
		System.out.println("Max sum of sub array is (Better Algo) "+maxSubArrayBetter(a));
		int b[] =  {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println("Max sum of sub array is "+maxSubArray(b));
		System.out.println("Max sum of sub array is (Better Algo) "+maxSubArrayBetter(b));
		System.out.println("Max sum of sub array is (DP) "+maxSumArrayDP(b));
		
		
		int c[] =  {-2, -3, -4, -1, -2, -7, -5, -3};
		System.out.println("Max sum of sub array (All negative) is (Better Algo) "+maxSubArrayBetter(c));
		System.out.println("Max sum of sub array (All negative) is (DP) "+maxSumArrayDP(c));
		
	}
	
}
