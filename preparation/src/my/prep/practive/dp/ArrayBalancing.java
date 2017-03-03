package my.prep.practive.dp;

import java.util.Arrays;

/*
 * Let A be an array of N non-negative integers, indexed from 0 to N − 1. We assume that the sum of its elements is a multiple of N
 * We would like to balance the array in a minimum number of iterations, 
 * where each iteration transforms the array using a number of allowed transfers described below. 
 * Balancing the array means transforming it into the uniform target array T = [a, . . . , a].
 * 
 * http://www.eecs.berkeley.edu/~walid/teasers/cod.pdf 
 * 
 * Solution:
 * Sum of left array
 * 
 * A.left = Sum (A(k))
 * 			0<=k<=n-1
 * 
 * Target sum of left array
 * 
 * Target Left, which is
 * 
 * T.left = Sum(T(k)) = n.a
 *   		0<=k<=n-1
 *   
 * A.right = Sum(A(k))
 * 			 n+1<=k<=N-1
 * T.right = Sum (T(k))) = (N-n-1).a
 *            n+1<=k<=N-1
 * F.left(n) = Total number of transfer required to balance left array
 * 			 = max(T.left - A.left,0)
 * 
 * F.right(n) = Total number of transfer required to balance right array
 * 			 = max(T.right - A.right,0)
 * 
 * f(n) = f.left + f.right = Total transfer required.
 * 
 * On every iteration n can transfer 1 to the left or 1 to the right, even if the deficit is more then 1.
 */
public class ArrayBalancing {
	
	//Assumption is Sum of array in multiple of a
	//Sum(n0 = N.a
	public static int balance(int a[]) {
	
		int length = a.length;
		
		int sum = 0;
		for(int i=0;i<a.length;i++) sum+=a[i];
		int transfer = 0;
		int value_of_each_element = (sum%a.length == 0 ? sum/a.length :0);
		if(value_of_each_element == 0) {
			System.out.println("ARRAY IS NOT MULTIPLE OF "+ a.length);
			return transfer;
		}
		System.out.println("Original Array " + Arrays.toString(a));
		for (int i = a.length-1;i>=0;) {
			int leftdef,rightdef;
			if(i==a.length-1) {
				leftdef = deficit(a, 0, i-1, value_of_each_element);
				rightdef = 0;
			}else{
				leftdef = deficit(a, 0, i-1, value_of_each_element);
				rightdef = deficit(a, i+1, a.length-1, value_of_each_element);
			}
			if(leftdef > 0) {
				a[i-1]++;
				a[i]--;
				transfer++;
				System.out.println("After " + transfer+ " Array " + Arrays.toString(a));
			}else if(rightdef >0 ) {
				a[i+1]++;
				a[i]--;
				transfer++;
				System.out.println("After " + transfer+ " Array " + Arrays.toString(a));
			}else{
				i--;
			}
			
		}
		
		return transfer;
	}
	
	private static int deficit(int a[], int start,int end,int element) {
		int targetValue = (end-start+1) * element;
		int sum = 0;
		for (int i=start;i<=end;i++) sum+=a[i];
		return (targetValue - sum);
	}
	
	public static void main(String[] args) {
		int a[] = {1,3,4,5,2};
		System.out.println("Total transfers required = "+ balance(a));
	}

}
