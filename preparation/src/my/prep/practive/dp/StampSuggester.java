package my.prep.practive.dp;

import java.util.ArrayList;
import java.util.Arrays;

/* You are given stamps and you need to choose stamps to make out a total postal charges*/
/*
 * You are given a set of stamps and a postal charges and need to tell the best way to use the stamps.
 * */
public class StampSuggester {

	public static void main(String[] args) {
		int[] stamps ={2,3,4,6,1};
		int postalCharges = 12;
		System.out.println("Having Stamps " + Arrays.toString(stamps) + " we need "+getTotalNumberOfStamps(stamps,postalCharges) + " for postal "+postalCharges);
		System.out.println("Having Stamps " + Arrays.toString(stamps) + " Min Stamps need "+getMinStamps(stamps,postalCharges) + " for postal "+postalCharges);
		
		getMinStampsDP(stamps,postalCharges) ;
		
		int[] stamps2 = { 90, 30, 24, 10, 6, 2, 1 };
		int postalCharges2 = 18;
		System.out.println("Having Stamps " + Arrays.toString(stamps2) + " Min Stamps need "+getMinStamps(stamps2,postalCharges2) + " for postal "+postalCharges2);
		getMinStampsDP(stamps2,postalCharges2) ;
		
		postalCharges2 = 34;
		System.out.println("Having Stamps " + Arrays.toString(stamps2) + " Min Stamps need "+getMinStamps(stamps2,postalCharges2) + " for postal "+postalCharges2);
		getMinStampsDP(stamps2,postalCharges2);
		
	}
	/*
	 * The function gives you total number of stamps. May no be the best option
	 *  Time - 2^N where n is number of stamps.
	 * */
	public static int getTotalNumberOfStamps(int[] stamps, int postalCharges) {
		if(postalCharges <=0) {
			return -1; //indication of bad postal charges
		}
		return getTotalNumberOfStamps(stamps,postalCharges,0);
		
	}
	
	private static int getTotalNumberOfStamps(int[] stamps,int postalCharges, int index) {
		
		int count = 0;
		if(index >= stamps.length) {
			return -1; // indication of fail
		}
		if(postalCharges == stamps[index]) {
			count = count +1;
		}else if(stamps[index] > postalCharges) {
			//of course we can not use this stamp go for next stamp
			count = getTotalNumberOfStamps(stamps,postalCharges,index+1);
		}
		else {
			//now we have 2 option first take stamp at index into account or not to take.
			
			//option 1 use the stamp at the given index
			int result = getTotalNumberOfStamps(stamps,postalCharges-stamps[index],index+1);
			if(result != -1) {
				//ok means we found solution and we can use option 1
				count = 1 + result;
			}else{
				result =  getTotalNumberOfStamps(stamps,postalCharges,index+1);
				count = result;
			}
			
		}
		
		return count;
	}
	// Time - 2^N, where N is the number of stamps;
	public static int getMinStamps(int[] stamps, int postalCharges) {
		if(postalCharges <=0) {
			return -1;
		}
		return getMinStamps(stamps,postalCharges,0);
	}
	private static int getMinStamps(int[] stamps, int postalCharges,int index) {
		int count = 0;
		if(index >= stamps.length) {
			return Integer.MAX_VALUE;
		}
		
		if(postalCharges == stamps[index]) {
			count = count +1;
		}else if(stamps[index] > postalCharges) {
			count = getMinStamps(stamps,postalCharges,index+1);
		}else{
			//DONT DO LIKE THIS getMinStamps could return MAX VAL and adding 1 to it could over flow and makes value negative and the 
			// Math.min will select the negative number.
//			count = Math.min(1 + getMinStamps(stamps, postalCharges - stamps[index],index+1), 
//					getMinStamps(stamps, postalCharges,index+1));
			//option1 - consider the stamp at index
			int option1 = getMinStamps(stamps, postalCharges - stamps[index],index+1);
			if(option1 != Integer.MAX_VALUE) {
				option1 = option1 +1; //adding 1 for considering using stamps at index
			}
			int option2 = getMinStamps(stamps, postalCharges,index+1);
			count = Math.min(option1, option2);
		}
		return count;
	}
	
	
	public static void getMinStampsDP(int[] stamps, int postalCharges) {
		if(postalCharges <=0) return ;
		if(stamps.length ==0) return ;
		int [][] dp= new int[stamps.length+1][postalCharges+1];
		
		for(int pc=0;pc<=postalCharges;pc++) {
			dp[0][pc] = Integer.MAX_VALUE-1;
		}
		for(int s=0;s<=stamps.length;s++) {
			dp[s][0] = Integer.MAX_VALUE-1;
		}
		
		for(int stamp=1;stamp<=stamps.length;stamp++) {
			
			for(int pc=1;pc<=postalCharges;pc++) {
				
				if(stamps[stamp-1] < pc) {
					//either use stamp or not use pick the best option
					//first option pick
					
					dp[stamp][pc] = Math.min(dp[stamp-1][pc-stamps[stamp-1]] + 1, dp[stamp-1][pc]);
				}else if (stamps[stamp-1] == pc) {
					//pick it and the max stamps would be just 1
					dp[stamp][pc] = 1;
				}else{
					//stamp is less than pc we can not pick it so copy from stamp-1
					dp[stamp][pc] = dp[stamp-1][pc];
				}
			}
		}
		
		System.out.println("Having Stamps " + Arrays.toString(stamps) + " Min Stamps need (DP) "+dp[dp.length-1][dp[0].length-1] + " for postal "+postalCharges);
		
		printStamps(stamps, dp);
	}
	
	private static void printStamps(int[] stamps,int[][] dp) {
		
		int i = dp.length-1;
		int j = dp[0].length-1;
		ArrayList<Integer> stampList = new ArrayList<Integer>();
		while(i>0 && j >0) {
			if(j == stamps[i-1]) {
				stampList.add(stamps[i-1]);
				j = j - stamps[i-1];
				i--;
			}else if(dp[i][j]==dp[i-1][j]) {
				i--;
			}else if(stamps[i-1] < j) {
				stampList.add(stamps[i-1]);
				j = j - stamps[i-1];
				i--;
			}
		}
		
		System.out.println("Stamps used are "+stampList);
	}
	
	
	
}
