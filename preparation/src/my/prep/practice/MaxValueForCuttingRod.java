package my.prep.practice;


import java.util.ArrayList;
import java.util.List;

public class MaxValueForCuttingRod {
	
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
		int prices[] = {1,5,8,9,10,17,17,20,21,23,34,30,37,23,40,42,30,20,43,50,51,52,53,54,55,56,67,54,43,23,67};
		//int prices[] = {1, 5, 8, 9, 10, 17, 17, 20};
		System.out.println(maxValue(prices.length,prices));
	}
	
	
}
