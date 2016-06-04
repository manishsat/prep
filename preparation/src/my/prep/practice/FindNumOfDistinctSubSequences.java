package my.prep.practice;

/*
 * Find distinct number of sub sequences of T[] into S[]
 * 
 *  
 *  F(i,j) = is a function to find out 0..ith char into 0...jth string
 *  
 *  F(i,j) = {
 *  			F(i,j-1)  #if T[i] != S[j] if ith char not matching with j char then ignore j and carry over what ever times 0..i matches 
 *  							in 0...j-1
 *  
 *  			F(i,j-1) + F(i-1,j-1) 
 *  									#if T[i] == S[j], 
 *  											this means if last char of T matches with S[j], which is going to satisfy/extended the sub seq 
 *  													for all matches 
 *  												i-1 in j-1 and i in j-1
 *  
 *  		}
 *  
 *  
 *  References : 
 *  
 *  http://n00tc0d3r.blogspot.com/2013/02/find-number-of-distinct-subsequences.html
 *  http://blog.welkinlan.com/2015/04/29/distinct-subsequences-leetcode-java-dp/
 */
public class FindNumOfDistinctSubSequences {
	
	
	
	//O(n*m)
	
	public static int findDistinctSubSeq(char[] T,char[] S) {
		int dptable[][] = new int[T.length+1][S.length+1];
		
		for(int i=0;i<=T.length;i++) {
			for(int j=0;j<=S.length;j++) { //remember to run up to length dptable has 1 more then the lenght on original stirng
				
				if(i==0) {
					dptable[i][j] = 1; // an empty sub sequence from T  matches with any length of S string.
				}
				else if(j==0) {
					dptable[i][j] = 0; // Non empty sub seq from T does not match in empty S
				}else{
					if(T[i-1] != S[j-1]) {
						dptable[i][j] = dptable[i][j-1]; 
					}else{
						dptable[i][j] = dptable[i][j-1] + dptable[i-1][j-1];
					}
				}
				
			}
		}
		
		return dptable[T.length][S.length];
	}
	
	public static void main(String[] args) {
		String s = "EEACC";
		String t = "EAC";
		System.out.println("Number of distinct sub seq matches = "+findDistinctSubSeq(t.toCharArray(),s.toCharArray()));
		/**		  	null	E		E		A		C		C
		 *	null |	1	| 	1	|	1	|	1	|	1	|	1	|    
		 *		 -------------------------------------------------   	
		 *   E   |	0	|	1	|	2	|	2	|	2	|	2	|
		 *   	 -------------------------------------------------
		 *   A   |	0	|	0	|	0	|	2	|	2	|	2	|
		 *   	 -------------------------------------------------
		 *   C   |	0	|	0	|	0	|	0	|	2	|	4	|
		 * 		-------------------------------------------------
		 */
		 s = "rabbbit";
		 t = "rabbit";
		System.out.println("Number of distinct sub seq matches = "+findDistinctSubSeq(t.toCharArray(),s.toCharArray()));
	}
}
