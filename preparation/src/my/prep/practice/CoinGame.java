package my.prep.practice;

public class CoinGame {
	
	public static int minWinningAmout(int coins[],int i,int j) {
		if(i==j) return coins[i];
		
		if(i==j-1) return Math.max(coins[i], coins[j]);
		
		int option1 = coins[i] + Math.min(minWinningAmout(coins, i+2, j),
											minWinningAmout(coins, i+1, j-1) 
										);
		
		int option2 = coins[j] + Math.min(minWinningAmout(coins, i+1, j-1),
											minWinningAmout(coins, i, j-2) 
										);
 
		return Math.max(option1, option2);
	}

	public static void main(String[] args) {
		int coins[] = new int[] {8,15,3,7};
		System.out.println("Winning amount " + minWinningAmout(coins, 0, coins.length-1));
	}
}
