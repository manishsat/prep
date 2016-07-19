package my.prep.practice;

public class Knapsack {
	
	/* We have given an array of weights and their values, we have to pick items such that their
	 * total weight can not exceed W (totcal capacity allowed to carry) with max value achieved.
	 * 
	 * M(i,j) = function to return max value of picking ith item with total j weight
	 * 
	 *   M(i,j) = max{
	 *   				M(i-1,j), Val[i] + M(i-1,j-wt[i])
	 *   			  } 
	 * 
	 * So have to choose between picking the ith item and not picking the ith item.
	 * */
	
	public int maxValue(int wt[],int val[],int W,int n) {
	
		int[][] memo = new int[n+1][W+1];
		internalMaxValue(wt,val,W,n,memo);	
		printItems(memo, W, n, wt, val);
		return memo[n][W];
	}
	
	public void printItems(int[][] memo,int W,int n,int[] wt,int[] val) {
		int i=n,j=W;
		while(j != 0 && i >0) {
			if(memo[i][j]!=memo[i-1][j]) {
				//if i-1 else with weight W is not equal to i+1 we select ith
				//means ith needed
				System.out.println("Item with weight = "+wt[i-1] +  " and value = "+val[i-1]);
				i--;
				j = j - wt[i];
			}else{
				i--;
			}
		}
	}
	private int internalMaxValue(int wt[],int val[],int W,int n,int[][] memo) {
		//Base Condigtion
		if(W <=0 || n == 0) {
			return 0;
		}
		
		if(wt[n-1] > W) {
			//weight of the nth item is > then total weight, we need to ignore it.
			memo[n][W] = internalMaxValue(wt,val,W,n-1,memo);
			return memo[n][W];
		}
		//else if weight of the ith item if less then total weight
		
		//Now we have 2 options either pick it and not pick it.
		
		int opt1 = 0;
		if(memo[n-1][W]!=0) {
			opt1 = memo[n][W];
		}else{
			opt1= internalMaxValue(wt, val, W, n-1,memo); //not picking it
			memo[n-1][W] = opt1;
		}
		
		int opt2 = 0;
		if(memo[n-1][W-wt[n-1]]!=0) {
			opt2 = memo[n-1][W-wt[n]];
		}else{
			memo[n-1][W-wt[n-1]] = internalMaxValue(wt, val, W-wt[n-1], n-1,memo); 
			opt2 = val[n-1] +memo[n-1][W-wt[n-1]]; //pick it
			
		}
		
	
		memo[n][W] = Math.max(opt1,opt2);
		return memo[n][W];
	}
	
	
	public static int[][] internalMaxValueDP(int wt[],int val[],int W,int n) {
		int[][] dp = new int[n+1][W+1];
		
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=W;j++) {
				if(i==0||j==0) {
					dp[i][j] = 0;
				}else{
					if(wt[i-1]>j) {
						dp[i][j] = dp[i-1][j];
					}else{
						dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-wt[i-1]]+val[i-1]);
					}
				}
				
				
			}
			
		}
		return dp;
	}
	
	public static void main(String[] args) {
//		int[] wt = new int[] {1,2,3};
//		int[] val = new int[]{6,10,12};
//		int W = 5;
//		Knapsack ks = new Knapsack();
//		System.out.println("Max value = "+ks.maxValue(wt, val, W, wt.length));
		int[] wt = new int[] {5,4,6,3};
		int [] val = new int[]{10,40,30,50};
		int W = 10;
		Knapsack ks1 = new Knapsack();
		
		System.out.println("Max value = "+ks1.maxValue(wt, val, W, wt.length));
		int[][] dp = ks1.internalMaxValueDP(wt, val, W, wt.length);
		System.out.println("Max value = "+dp[dp.length-1][dp[0].length-1]);
		ks1.printItems(dp, W, wt.length, wt, val);
	}
	
	

}
