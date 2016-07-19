package my.prep.practice;

import java.util.ArrayList;

public class MinimumCoins {
	
	private int coin_array[];
	private int intermediateResult[];
	public MinimumCoins(int coin_array[]) {
		this.coin_array = coin_array;
	}
	
	private void init(final int val) {
		intermediateResult = new int[val];
	}
	public int minCoins(int bill) {
		if(intermediateResult == null) init(bill+1);
		if(bill<=0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		//loop thru all coins and get minimum 
		for (int i=0; i<coin_array.length;i++) {
			if(coin_array[i] <= bill) { // ignore coins bigger then bill
				int remainingBill = bill-coin_array[i];
				boolean isThere = intermediateResult[remainingBill] !=0;
				int result =  isThere	?	intermediateResult[remainingBill]: minCoins(remainingBill);
				//store the result if not there already
				if(result < min)  min = result;
			}
		}
		intermediateResult[bill]=1+min;
		//System.out.println(String.format("Bill is %d and result is %d ", bill,(1+min)));
		return 1+ min;
	}
	
	
	
	
	public static void main(String[] args) {
//		int coin_array[] = {1,2,3};
//		int bill = 5000;
//		MinimumCoins mc = new MinimumCoins(coin_array);
//		int noofcoins = mc.minCoins(bill);
//		System.out.println(noofcoins);
//		
		int coins[] = {1,5,6,8};
		int total = 11;
		ArrayList<Integer> whichcoins = new ArrayList<Integer>();
		System.out.println(mincoins(coins, total,whichcoins) + " coins are " + whichcoins);
	
		mincoinsDP(coins, total);
	}
	
	
	public static int mincoins(int[] coins, int total,ArrayList<Integer> whichcoins) {
		
		if (total <= 0 ) {
			return 0;
		}
		
		//check for every coin 
		int min = Integer.MAX_VALUE;
		int minIndex = 0;
		ArrayList<Integer> coinsFromSub = null;
		for(int i=0;i<coins.length;i++) {
			int rest = total - coins[i];
			ArrayList<Integer> l = new ArrayList<Integer>();
			int minForRest = Math.min(min,mincoins(coins,rest,l));
			if(minForRest < min) {
				min = minForRest;
				minIndex = i;
				coinsFromSub = l;
			}
		}
		whichcoins.add(coins[minIndex]);
		whichcoins.addAll(coinsFromSub);
		return 1+min;
	}
	
	public static void mincoinsDP(int[] coins,int total) {
		int[][] dp = mincoinsInternal(coins,total);
		System.out.println("Coins are " + dp[dp.length-1][dp[0].length-1]);
		
		int i=coins.length-1;
		int j = total;
		
		while( j > 0) {
			if(dp[i][j] == dp[i-1][j]) {
				//means coming from top coin[i] is not in the ans
				i--;
				
			}else{
				System.out.println("Coin - " + coins[i]);
				j = j - coins[i];
			}
		}
	}
	
	public static int[][] mincoinsInternal(int[] coins,int total) {
		
		int dptable[][] = new int[coins.length][total+1];
		for(int i=0;i<dptable.length;i++) {
			for(int j =0;j<dptable[0].length;j++) {
				if(i==0) {
					dptable[i][j] = j/coins[i];
				}else{
					if(coins[i] > j) {
						dptable[i][j] = dptable[i-1][j];
					}else{
						try {
						dptable[i][j] = Math.min(dptable[i-1][j], 1+dptable[i][j-coins[i]]);
						}catch(Exception ex) {
							System.out.println(ex );
						}
					}	
				}
				
			}
		}
		return dptable;
	}
	
	
	
}
