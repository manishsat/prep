package my.prep.practice;

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
		int coin_array[] = {1,2,3};
		int bill = 5000;
		MinimumCoins mc = new MinimumCoins(coin_array);
		int noofcoins = mc.minCoins(bill);
		System.out.println(noofcoins);
	}
}
