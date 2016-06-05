package my.prep.practice;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it is able to trap after raining.
 * 
 * Ref: http://stackoverflow.com/questions/21818044/the-maximum-volume-of-trapped-rain-water-in-3d
 * */
public class TrappedWater {

	public static int maxWater(int[] A) {
		
		int[] leftmost = new int[A.length];
		int[] rightmost = new int[A.length];
		
		//Here we are calculating max height building on lift side for every building
		
		int leftMaxSoFar = 0;
	    for (int i = 0; i < A.length; i++){
	        leftmost[i] = leftMaxSoFar;
	        if (A[i] > leftMaxSoFar) leftMaxSoFar = A[i];
	    }
	    
	  //Here we are calculating max height elevation on the right side for every elevation
	    //going from right->left
	    
	    int rightMaxSoFar = 0;
	    for (int i = A.length - 1; i >= 0; i--){
	        rightmost[i] = rightMaxSoFar;
	        if (A[i] > rightMaxSoFar) rightMaxSoFar = A[i];
	    }
	    
	    //After this we have leftmost and right most elevation reference point for every elevation.
	    //And minimum among them is the max amount of water that elevation can contribute 
	    //example max lieft evelevate for i is 3 and max right elevation for i is 4
	    //min is 3, now that ith elevation point can stack/trap max 3 width water if that ith is 0 otherwise 
	    //water = 3 - A[i] elevation of that ith point
	    //http://i.stack.imgur.com/4oFm2.png
		
	 // Summation, O(n)
	    int max_water = 0;
	    for (int i = 0; i < A.length; i++){
	    	max_water += Math.max(0, Math.min(leftmost[i], rightmost[i]) - A[i]);
	    }
	    
		return max_water;
	}
	
	public static void main(String[] args) {
		int[] A = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println("max water trapped is " + maxWater(A));
	}
}
