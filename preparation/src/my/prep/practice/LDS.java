package my.prep.practice;

import java.util.ArrayList;
import java.util.List;

//Largest Divisible Subset
/*
 * 
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0. 
 */
public class LDS {
	
	public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> finalResult = new ArrayList();
        if(nums.length == 0) {
            return finalResult;
        }
        
        if(nums.length == 1) {
            finalResult.add(nums[0]);
            return finalResult;
        }
        int[] result = new int[nums.length];
        for(int i=0;i<result.length;i++) {
            result[i] = 1;
        }
        int maxLength = 0;
        int maxIndex = 0;
        int parent[] = new int[nums.length];
        for(int i=0;i<parent.length;i++) {
            parent[i] = -1;
        }
        for(int i=0;i<nums.length;i++) {
            int lastIndex = 0;
            for(int j=0;j<i;j++ ) {
                if((nums[i] % nums[j] == 0) || (nums[j] % nums[i] ==0)) {
                    if(result[i] < result[j] + 1) {
                        result[i] = result[j] + 1;
                        parent[i] = j;
                    }
                }
            }
            if(result[i]>maxLength) {
                maxLength = result[i];
                maxIndex = i;
            }
        }
        System.out.println("maxLength "+maxLength);
        
        System.out.println("maxIndex "+maxIndex);
        //System.out.println("parent " + Arrays.toString(parent));
       //System.out.println("nums " + Arrays.toString(nums));
        while(maxIndex >=0) {
            finalResult.add(nums[maxIndex]);
            maxIndex = parent[maxIndex];
        }
        return finalResult;
        
    }

}
