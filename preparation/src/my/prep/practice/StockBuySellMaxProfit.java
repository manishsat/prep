package my.prep.practice;

/**
 * Maximum profit by buying and selling a share at most k times 
 */
public class StockBuySellMaxProfit {

	public int maxProfitWithOneTransaction(int[] prices) {
		if(prices.length <= 1) {
			return 0;
		}
		int maxprofit = Integer.MIN_VALUE;
		int minPrice = prices[0];
		for(int i=0;i<prices.length-1;i++) {
			if(prices[i] < minPrice) minPrice = prices[i];
			maxprofit  = Math.max(maxprofit, prices[i]-minPrice);
		}
		
		return maxprofit;
	}
	/**
	 * Max profit with at max K transaction.
	 * K= No of transactions
	 * N = No of prices
	 * O(KN^2)
	 * 
	 * Max(i,j)  = max {
	 * 						max(i,j-1)   #dont transact on jth day
	 * 						max(i-1,m) + [price(j) - price(m)]    #Selling on jth day	
	 * 								for m= 0...j-1
	 * 		}
	 */
	public static int maxProfitWithKTransaction(int[] prices,int k) {
		if(k==0||prices.length<=1) {
			return 0;
		}
		int dptable[][] = new int[k+1][prices.length];
		for(int i=1;i< dptable.length;i++) { //loop thru transaction
			
			for(int j=1;j<dptable[0].length;j++) { //loop thru all the prices
				int max = Integer.MIN_VALUE;
				for(int m=0;m<j;m++) {
					max = Integer.max(max,prices[j]-prices[m]+dptable[i-1][m]);
				}
				
				//
				dptable[i][j] = Math.max(dptable[i][j-1],max);
			}//end loop prices
		}
		
		return dptable[k][prices.length-1];
	}
	
	//O(KN)
	public static int maxProfitWithKTransactionFast(int[] prices,int k) {
		if(k==0||prices.length<=1) {
			return 0;
		}
		int dptable[][] = new int[k+1][prices.length];
		for(int i=1;i< dptable.length;i++) { //loop thru transaction
			int maxdiff = -prices[0];
			for(int j=1;j<dptable[0].length;j++) { //loop thru all the prices
				dptable[i][j] = Math.max(dptable[i][j-1],prices[j]+maxdiff);
				maxdiff = Math.max(maxdiff, dptable[i-1][j]-prices[j]);
			}
		}
		
		return dptable[k][prices.length-1];
	}
	
	
	public static void main(String[] args) {
		int prices[] = {2, 5, 7, 1, 4, 3, 1, 3};

        System.out.println("Max profit fast solution " + maxProfitWithKTransaction(prices, 3));

	}
}
