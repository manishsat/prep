package my.prep.practive.dp;
/**
 *In Balanced Partition problem,you have a set of n integers each in the range 0 … K. 
 * Partition these integers into two subsets such that you minimize |S1 – S2|, 
 * where S1 and S2 denote the sums of the elements in each of the two subsets 
 * 
 * A = {A1, A2, ....... An} set of integers
 * 
 * P(i,j) = { 1 if some sub set of i has sum j 
 * 				0 #otherwise
 * 			}
 * 
 * P(i,j) = max {
 * 					p(i-1,j), #excluding the element
 *				}	P(i-1,j-Ai) #including the ith element so subtracted the value of ith element A[i]
 *				
 * 
 * S = sum(A)/2
 * 
 * If I can find out any string whose sum of elements is as close as possible to S 
 * 
 * Min {S-i: P(n,i)=1}
 * 
 * Sum (s1) = i 
 * 
 * Sum (s2) = 2S -i
 * 
 * Min { Sum(s1) - Sum(s2) }
 * 
 * Min { (i) - (2S - i)}
 * Min (2 (S-i))
 * 
 * }
 */
public class BalancePartition {

	
	public static Result balancePartition(int[] a) {
		int sum = 0;
		int length = a.length;
		
		
		for (int i =0 ; i<length;i++) sum+=a[i]; 
		
		int s = sum/2;
		int dp[][] = new int[length+1][s+1];
		
		for (int i=0;i<=length;i++) { // remember dp has 1 more row then original array.
			
			for(int j=0;j <=s;j++) { //j is the sum (one of the sum and j could go up to sum)
				
				if(i==0 && j==0) dp[i][j] = 1;  // 0 element can have sum 0
				
				else if(i==0 && j!=0) { 
					dp[i][j] = 0;//if we have 0 element then we can not have any sum so 0 for all non-zero j
				}	
				else{
					
					//dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-a[i]]);
					//if a[i] is > j then index will go in negative so 
					
					dp[i][j] = Math.max(dp[i-1][j], 
							
							( (j-a[i-1] < 0) ? 0 : dp[i-1][j-a[i-1]]));
					
				}
				
			}
			
		}
		int result = Integer.MAX_VALUE;
		for (int i=0;i<=s ;i++) {
			
			// in this if we just need to check length means last row but i col (which is a sum)
			// by our recursive definition if the col is 1 means either P(length,i) = 1 {p(length-1,i) == 1 OR p(length-1,i-A1) ==1}
			
			if(dp[length][i]==1) {
				result = Math.min(result, 2 * (s-i));	
			}
			
			// ith the sum here and S-i gives us the how much sum of any subset away from S and we are interested in the subset which is 
			//as close as possible 
			//and if we find the subset which is as close as possible then the other subset is going to be away from S by the same difference.
			//so the difference between S1 and S2 could be twice the difference of S-S1.
			
			
		}
		Result r = new Result(dp,result);
		return r;
	}
	
	public static void printElements(int[][] dp,int[] a) {
		
		int sum = dp[0].length-1;
		int i=dp.length-1;
		StringBuilder str = new StringBuilder("[");
		while(sum >0) {
			
			if(dp[i][sum] == 1) {
				if(dp[i-1][sum] !=1) {
					str.append(a[i-1]);
					str.append(",");
					sum = sum - a[i-1];
				}
			}
			
			i--;
		}
		str.append("]");
		
		System.out.println("Elements are "+str.toString());
	}
	public static void main(String[] args) {
		int[] A = {9, 9, 6, 6, 6};
		
		Result r = balancePartition(A);
		System.out.println(r.mindDifference);
		printElements(r.dp, A);
		int [] a = {3, 1, 1, 2, 2, 1};
		r = balancePartition(a);
		printElements(r.dp, a);
		System.out.println(r.mindDifference);
		
		int [] b = {1,5};
		r = balancePartition(b);
		//printElements(r.dp, b);
		System.out.println(r.mindDifference);
		
		int[] c = {2, 10, 3, 8, 5, 7, 9, 5, 3, 2};
		r = balancePartition(c);
		printElements(r.dp, c);
		System.out.println(r.mindDifference);
		
		
	}
	
	 static class Result {
		public int [][] dp;
		public int mindDifference;
		public Result(int[][] dp,int diff) {
			this.dp = dp;
			this.mindDifference = diff;
		}
	}
}
