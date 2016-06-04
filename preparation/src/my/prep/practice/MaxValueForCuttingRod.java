package my.prep.practice;


import java.util.ArrayList;
import java.util.List;


public class MaxValueForCuttingRod {
	
	/*
	 * Max(n) = {	0 #if n==0
	 * 				Max(pi+Max(n-i))
	 * 				1<=i<=n	
	 * 			}
	 * */
	public static int findMaxPrice(int prices[],int rodLength) {
		if(rodLength <=0 || prices.length ==0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		for(int i=1;i<=rodLength;i++) {
			max = Math.max(max,prices[i-1]+findMaxPrice(prices, rodLength-i));
		}
		return max;
	}
	
	public static int findMaxPriceDP(int prices[],int rodLength) {
		
		int maxvalue[] = new int [prices.length+1];
		int choices[]  = new int[prices.length+1];
		maxvalue[0] = 0; //maxvalue of the 0 length is 0
		//in dynamic programing you go through all possible length of the rod.
		
		//start from length of the rod is 1
		for(int i=1;i<=rodLength;i++) {
			int max = Integer.MIN_VALUE;
			for(int j=1;j<=i;j++) { //compare all length less then 1...i
				int smallRodValue = prices[j-1]+maxvalue[i-j];
				if(smallRodValue > max) {
					//this is the rod we need to make a first cut for rod = ith length
					max = smallRodValue;
					choices[i] = j;
				}
				
			}
			maxvalue[i] = max;
		}
		System.out.println("Max value could be = " + maxvalue[prices.length]);
		int cut = 0;
		int l = prices.length;
		System.out.println("Total lenght os the rod is "+l);
		while(l!=0) {
			cut = choices[l];
			System.out.println("Cut = "+cut);
			l = l - cut;
		}
		
		return maxvalue[prices.length];
	}
	
	
	public static MaxValue maxValue(int length,int prices[]) {
		int choices[]  = new int[length+1];
		int max =  determineMaxValue(length, prices, new int[length+1], choices);
		List<Integer> parts = new ArrayList<Integer>();
		for(int l=length;l>0;) {
			int part = choices[l];
			parts.add(part);
			l=l-part;//remaing length
		}
	
		return  new MaxValue(max,parts.toArray(new Integer[parts.size()]));
	}
	//This is TOP DOWN MEMOIZED Solution
	private static int determineMaxValue(int length,int prices[],int intermediateResult[]
			,int choices[]) {
		//base case
		if(length == 0) {
			return 0;
		}
		int max = 0;
		for(int i=1;i<=length;i++) {
			int smallRodValue = 0 ;
			int result = 0;
			if(intermediateResult[length-i]!=0)  smallRodValue = intermediateResult[length-i];
			result = prices[i-1] + (smallRodValue !=0 ? smallRodValue:determineMaxValue(length-i, prices,intermediateResult,choices));
			if(result > max) {
				max = result;	
				choices[length] = i;
			}
			
		}
		intermediateResult[length] = max;
		return max;
	}
	public static void main(String[] args) {
		//int prices[] = {1,5,8,9,10,17,17,20,21,23,34,30,37,23,40,42,30,20,43,50,51,52,53,54,55,56,67,54,43,23,67};
//		int prices[] = {1, 5, 8, 9, 10, 17, 17, 20};
//		System.out.println(maxValue(prices.length,prices));
		
		int price[] = {1,5,8,9,10,17,17,20};
		System.out.println(findMaxPrice(price,price.length));
		System.out.println(findMaxPriceDP(price,price.length));
		
	}
	
	
}
