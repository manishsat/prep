package my.prep.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import my.prep.practice.tree.BinaryTreeNode;

public class NumberUtil {
		public static void main(String[] args) {
			int number = 85463;
			System.out.println("Factors ->" + allFactors(number));
			
			int n = 530751374;
			int num=54;
			num %=1003;
		
			System.out.println("num "+num);
			
		}
	
		public static int singleNumber(final List<Integer> a) {
		    int size = a.size();
		    int val = 0;
		    for(int i : a) {
		        val=val ^ i;
		       
		    }
		    return val;
		}
	

	
		public static ArrayList<Integer> allFactors(int a) {
		    
		    int sqrt = (int)Math.sqrt(a);
		    ArrayList list = new ArrayList();
		    for(int num =1;num<=sqrt;num++) {
		        if(a % num == 0) {
		        	System.out.println("1-"+num);
		        	list.add(num);
		        	if(sqrt != a/num) {
		        		System.out.println("2-"+a/num);
			            list.add(a/num);
			        }
		        }
		        
		    }
		   
		    return list;
		}
		
		public static int sqrt(int a) {
		    int sq = 0;
		    for(int i=1;i<=(a/2);i++) {
		        if(i*i <= a) {
		            sq = i; 
		        }else {
		            break;
		        }
		    }
		    return sq;
		}
		
		public boolean isPowerOfTwo(int i) {
			
			if(i==0) {
				return false;
			}
			
			if(i == 1) {
				return true;
			}
			
			if(i % 2 == 1) {
				return false;
			}
			
			return isPowerOfTwo(i/2);
		}
		
		//O(2^n)
		public int fibonacci(int n) {
			if(n ==0)  return 0;
			
			if(n <= 2) return 1;
			
			return fibonacci(n-1) + fibonacci(n-2);
		}
		//median of 2 sorted arrays
		
		public static int median(int[] a,int[] b) {
			return -1;
		}
		//logic is 
		//a. Find median of a -> m1 and median of b->m2
		//if m1==m2 then m1 or m2 is the median
		//if m1<m2 then median is between m1......endA..startB.....m2
		//ifm1>m2 then median is between m2...endB....startA....m1.
		//eventually startA and endA will be adjacent to each other and same for array B 
		//then we can find out 
		private static int median(int[] a,int[] b,int startAIndex,int endAIndex,int startBIndex, int endBIndex) {
			if((endAIndex - startAIndex == 1) && (endBIndex - startBIndex == 1)) {
				return ((Math.max(a[startAIndex], b[startBIndex]) + Math.min(a[endAIndex], b[endBIndex]))/2);
			}
			int midAIndex = (startAIndex + endAIndex)/2;
			int midBIndex = (startBIndex + endBIndex)/2;
			
			int midAValue = a[midAIndex];
			int midBValue = b[midBIndex];
			if(midAValue == midBValue) return midAValue;
			
			if(midAValue < midBValue) {
				startAIndex = midAIndex;
				endBIndex = midBIndex;
			}else{
				endAIndex = midAIndex;
				startBIndex = midBIndex;
			}
			return median (a,b,startAIndex,endAIndex,startBIndex,endBIndex);
		}
}
