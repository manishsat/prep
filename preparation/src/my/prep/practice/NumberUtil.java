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
			System.out.println("sqrt "+sqrt(n));
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
		

}
