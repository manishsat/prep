package my.prep.practive.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
		System.out.println("Minimum coins are "+finaMinCoins(coins, total));
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
	
	
	
	
	
	//single dimension DP
	//Using this approach 
	/**
	 * findMinCoins (S,N) = {
	 * 							0  #if N==0
	 * 							0  #if S==NULL
	 * 							min(1 + findMinCoins(S',N-nk), findMinCoins(S',N))
	 * 								for each nk which is member of S
	 * */
	public static int findMinCoins(final int[] coins, final int sum) {
		
		if (sum <=0 || coins.length == 0) return 0;
		
	    int[] minCoins = new int[sum + 1];
	    for (int s = 1; s <= sum; s++) {
	        minCoins[s] = Integer.MAX_VALUE - 1;
	    }

	    for (int s = 1; s <= sum; s++) {
	        for (int coin : coins) {
	            if (s >= coin) {
	                minCoins[s] = Math.min(minCoins[s], minCoins[s - coin] + 1);
	            }
	        }
	    }
	    return (minCoins[sum] == Integer.MAX_VALUE) ? 0 : minCoins[sum];
	}
	
	//two dimension DP
	//Note: This does not consider unlimited coins condition
		
	public static List<Integer> finaMinCoins(int[] coins,int total) {
		
		int dptable[][] = new int[coins.length + 1][total+1];
		
		for(int i=0; i<dptable[0].length;i++) {
			dptable[0][i] = Integer.MAX_VALUE - 1;
		}
		
		for(int i=0;i < dptable.length;i++) {
			dptable[i][0] = Integer.MAX_VALUE -1;
		}
		
		for(int i=1;i<dptable.length;i++) { // for each coin
			for(int j =1;j<dptable[0].length;j++) { //for each sum
				
				if(coins[i-1] > j) {
					dptable[i][j] = dptable[i-1][j]; // we can not use the Coin as Coin is > than total
				}else if(coins[i-1] == j) {
					//Now we now we are going to use only 1 coin.
					dptable[i][j] = 1;
				}
				else{
					dptable[i][j] = Math.min(dptable[i-1][j], 1+dptable[i-1][j-coins[i-1]]);
				}
					
			}
		}
		
		//Back track to find out which coins we used.
		List<Integer> selectedCoins = new ArrayList<Integer>();
		int i = dptable.length-1;
		int j  = dptable[0].length -1;
		while(i > 0 && j > 0 ) {
			if(dptable[i][j] == dptable[i-1][j]) {
				i--;// no using ith coin 
			}else if(coins[i-1] <= j) {
				selectedCoins.add(coins[i-1]);
				j = j - coins[i-1];
				i--;
			}
		}
		return selectedCoins;
	}
}
