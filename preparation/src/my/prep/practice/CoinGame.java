package my.prep.practice;

public class CoinGame {
	/*
	 * Min Amount we can at least win when there are i...j coins left and it is our turn to pick.  
	 */
	public static int minWinningAmount(int coins[],int i,int j) {
		if(i==j) return coins[i];
		//This condition makes sure that i+2 never ever goes beyond j
		//
		if(i==j-1) return Math.max(coins[i], coins[j]);
		
		
		//option 1 when we pick ith coin
		int option1 = coins[i] + Math.min(minWinningAmount(coins, i+2, j),
											minWinningAmount(coins, i+1, j-1) 
										);
		
		//option 2 when we pick jth coin
		int option2 = coins[j] + Math.min(minWinningAmount(coins, i+1, j-1),
											minWinningAmount(coins, i, j-2) 
										);
		//max benefit selecting either i of jth when is our turn
		return Math.max(option1, option2);
	}

	public static int minWinningAmountDP(int coins[]) {
		int[][] dp = new int[coins.length][coins.length];
		for(int range=0;range<dp.length;range++) {
			//This range is the number of coins from i..j
			
			for(int i=0,j=range;j<coins.length;i++,j++) {
				// a = dp(i+2,j) - I choose i player2 chooses i+1
				// b = dp(i+1,j-1)- I choose i , player2 chooses j OR
				// I choose j , player2 chooses i
				// c = dp(i,j-2)- I choose j , opponent chooses j-1
				int a, b, c;
				if (i + 2 <= j)
					a = dp[i + 2][j];
				else
					a = 0;
				//////////////////////////////////
				if (i + 1 <= j - 1)
					b = dp[i + 1][j - 1];
				else
					b = 0;
				//////////////////////////////////
				if (i <= j - 2)
					c = dp[i][j-2];
				else
					c = 0;
				//////////////////////////////////
				dp[i][j] = Math
						.max(coins[i] + Math.min(a, b), coins[j]+ Math.min(b, c)); 	
			}
			
		}
		return dp[0][coins.length-1];
	}
	public static void main(String[] args) {
		int coins[] = new int[] {1,5,3,2,3,7};
		System.out.println("Winning amount " + minWinningAmount(coins, 0, coins.length-1));
		System.out.println("Winning amount " + minWinningAmountDP(coins));
	}
}
