package my.prep.practice;

import java.util.Arrays;

/**
 * Pal(i,j) = Longest palindrome substring starting i ...j
 *         = 0  # a[i] != A[J]
 *         
 *         = 1 IF (Pal(i+1,j-1))
 *         
 * 
 * 
 */
public class LongestPalindromeSubString {

	public static String LPS(String s) {
		char[] a = s.toCharArray();
		int[][] dptable = new int[a.length][a.length];
		for(int i=0;i < a.length;i++) {
			dptable[i][i] = 1;
		}
		int palindromeLength=0, startIndex=0;
		for(int len=2;len <=a.length;len++) {
			
			for(int start=0;start+len <= a.length;start++) {
				int end = start + len -1;
				if(len == 2) {
					if(a[start] == a [end]) {
						dptable[start][end]= 1;
						startIndex = start;
						palindromeLength = len;
					}else{
						dptable[start][end]= 0;
					}
				}else if (a[start] == a [end] && dptable[start+1][end-1] == 1){
					dptable[start][end]= 1;
					startIndex = start;
					palindromeLength = len;
				}else{
					dptable[start][end]= 0;
				}
			}
			
		}
		
		return s.substring(startIndex,palindromeLength+1);
	}
	
	public static void main(String[] args) {
		String s = "bananas";
		System.out.println(LPS(s));
	}
}
