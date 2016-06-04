package my.prep.practice;

import java.util.ArrayList;
import java.util.List;

public class BustBalloons {
	/**
	 * 
	 * 	Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
	 * 	You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] nums[i] nums[right] coins. 
	 * 	Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
	 *	Find the maximum coins you can collect by bursting the balloons wisely.
	 *
	 * 	Note: (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them
	 * 
	 * */
	
	/*
	 * Brute force
	 * 
	 *  O(N!)
	 *
	 */
	
	public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }    
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return maxCoints(list);
    }

    public int maxCoints(List<Integer> list) {
        int max = 0;
        if (list.size() == 1) {
            return list.get(0);
        }
        for (int i = 0; i < list.size(); i++) {
            int left = i == 0 ? 1 : list.get(i-1);
            int right = i == list.size()-1 ? 1 : list.get(i+1);
            int n = left * list.get(i) * right;
            List<Integer> tmp = new ArrayList<>(list);
            tmp.remove(i);
            
            max = Math.max(max, n + maxCoints(tmp)); //recursive call for n-i balloons
            
        }
        return max;
    }
    /**
     * This is DP solution, different thinking then recursive, bottom up approach.
     * In recursive we think ith balloon we busted first and get the coins for remains balloon using recursive call and then
     * compare with every possible balloon we bust first.
     * 
     * In this approach we think which balloon we bust last and compare every balloon.
     * And pick the one which we bust last gives us the most conins.
     * 
     * Ref:
     * 1. https://leetcode.com/discuss/94458/c-dp-detailed-explanation
     * 2. https://kennyzhuang.gitbooks.io/algorithms-collection/content/burst_balloons.html
     * 
     * 
     *  foreach k in i to j: dp[j][i] = max(array[j-1]array[k]array[i+1] + dp[j][k-1] + dp[k+1][i], dp[j][i]);
     * 
     * 
     * */
    
    
	public static int max(int[] nums) {
		int[] num = new int[nums.length+2];
        int n = 1;
        for(int x : nums)  num[n++] = x;
        //added 1 at the front and at the last
        num[0] = num[n++] = 1;
        
        int dp[][] = new int[nums.length+2][nums.length+2];
        
        //len of the sub-sequence
        for(int len = 2; len <n; len++){
        	
            for(int left = 0; left + len < n; left++){
            
            	int right = left + len;
                
            	//loop thru for every balloon we consider busted last between left and right
            	//this loop will for sub-range and the actual range.
            	for(int i = left+1; i < right; i++){
            		
                    dp[left][right] = Math.max(dp[left][right], 
                    		
                    		dp[left][i] + dp[i][right] + 
                    		
                    		num[left]*num[i]*num[right]);
                            //Inner loop will run up to right -1 element and we consider right points to a padded 1 (we added at the tail) for the sub range when the sub-range will become
                    		// full range the right will actual points to the 1 which we added at the tail.
                     		//But for sub range it does not point to the padded element it points to actual balloon and when that actual balloon we busted last means that balloon is 
                    		//taken into account in multiplication for the sub-range.
                    
                    		// That is why for the sub range it will looks strange and confusing that why we are multiplying with right and left which does not point to the padded 1s 
                           //But when the sub range becomes the full range, left and right indeed points to padded 1s and we can directly pick sub-range value from dp table for 
                      		//every ith balloon we consider busting last and that ith balloon has contributed in multiplication for the sub-range and we don't need to worry about that.
                    
                    		//Very important thing to remember here, when ith balloon bursted last means left->i already busted and i->right already busted
                           //which means ith balloon contributed in left->i range and i->right range (contributed means accounted during multiplication)
                    		// when inner most loop will run for sub-range then right will point to 
                    		//when sub range will be equal to actual range 0...n then left and right will point to padded 1 (2 extra element we added one at the front and second at the tail.
                    		//then num[left] will be 1 and num [right] = 1 for every i balloon which we consider busted last.
            	}
            }
        }
        return dp[0][n-1];
        
	}
	public static void main(String[] args) {
		int[] a = {1};
		System.out.println("Max value = " +	max(a));
		int[] b = {1,5};
		
		System.out.println("Max value = " +	max(b));
		int[] nums = {1,3,6};
		System.out.println("Max value = " +	max(nums));
		int[] nums1 = {3,1,5,8};
		System.out.println("Max value = " +	max(nums1));
	}

}
