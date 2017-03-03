package my.prep.practive.dp;
/**
 * Longest common sub sequence between two String S, T
 * 
 * LCS(i,j) = Longest common sub sequence between i and j is
 *          = {
 *                1 + LCS (i,-1,j-1) #S[i] == T[j]
 *                
 *               max (LCS(i-1,j), LCS(i,j-1)) #Otherwise
 * 			 }
 * */
public class LongestCommonSubSeq {

	/*
	 * This is recursive solution
	 * O(2^N)
	 * **/
	public static int LCS(char[] a,int i,char[] b,int j) {
		//base condition 1
		if(i==0||j==0) {
			return 0;
		}
		//recursive condition 1
		if(a[i-1]==b[j-1]) {
			return 1+LCS(a,i-1,b,j-1);
		}
		else{
			return Math.max(LCS(a,i-1,b,j), LCS(a,i,b,j-1));
		}
	}
	
	public static void main(String[] args) {
		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		
		System.out.println("LCS="+LCS(str1.toCharArray(),str1.length(),str2.toCharArray(),str2.length()));
		int[][] dptable = LCSDP(str1.toCharArray(),str1.length(),str2.toCharArray(),str2.length());
		System.out.println("LCS="+dptable[dptable.length-1][dptable[0].length-1]);
		printTable(dptable,str1.toCharArray(),str2.toCharArray());
		
		String str3 = "HIEROGLYPHOLOGY";
		String str4 = "MICHAELANGELO";
		 dptable = LCSDP(str3.toCharArray(),str3.length(),str4.toCharArray(),str4.length());
		System.out.println("LCS="+dptable[dptable.length-1][dptable[0].length-1]);
		printTable(dptable,str3.toCharArray(),str4.toCharArray());
	}
	private static void printTable(int[][] dptable,char[] X,char[] Y) {
		int i= dptable.length-1;
		int j = dptable[0].length-1;
		
		while(i > 0 && j > 0) {
			
			if(X[i-1] == Y[j-1]) {
				System.out.println(X[i-1]);
				i--;j--;
			}else if(dptable[i][j] == dptable[i-1][j]) {
				i--;
			}else {
				j--;
			}
	
		}
	}
	
	public static int[][] LCSDP(char[] a,int length1,char[] b,int length2) {
		int table[][] = new int[length1+1][length2+1];
		//we can run up to "=" of length1 or length2 because table is length1+1 and length2+1
		for(int i=0;i<=length1;i++) {
			for(int j=0;j<=length2;j++) {
				
				if(i==0||j==0) table[i][j] =0;
				
				else if (a[i-1]==b[j-1]) { // ALWAYS remember to -1 we have 1 more element then array a or b
					table[i][j] = 1+table[i-1][j-1];
				}else{
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
				}
			}
		}
		
		return table;
	}
	
}
