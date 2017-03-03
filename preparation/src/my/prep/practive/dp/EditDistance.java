package my.prep.practive.dp;

/**
 *  Convert String a to String b by either 
 *  Insert
 *  Remove
 *  Replace
 *  Assuming all have same cost.
 *  
 *   EditDistance(s1,s2) = min {  EditSistance(s1-1,s2-1) + cost where 
 *   												cost = 0 if s1 == s2 else 1
 *								  ,
 *								  EditDistance(s1,s2-1) + 1  // if we can convert s1 to s2-1 and we need to Insert last character
 *
 *								  ,
 *								  EditDistance(s1-1,s2) + 1	// if we can covert s1-1 to s2 and we just need to remove last char from s1	   								
 *   								
 *   }
 * Application: 
 *    a. Used in spell check program to find closest word.
 *    b. In computational biology to align two genes.
 *    c. In speech recognition,  machine translations and information extraction.
 *    
 * Time complexity: O(n^2)
 */

public class EditDistance {

	//Time complexcity O(3^min(m,n))
	public static int editDistance(char[] a,char[] b,int l1,int l2) {
		
		if(l2 ==0)  return l1;
		
		if(l1==0) return l2;
	
		if(a[l1-1] == b[l2-1]) {
			return editDistance(a, b, l1-1, l2-1);
		}
		
		return 1 + min(
						editDistance(a, b, l1-1, l2-1),
						editDistance(a, b, l1, l2-1),
						editDistance(a, b, l1-1, l2)
					);
		
	}
	
	public static int editDistanceDP(char[] a,char[] b) {
		
		int dpmatrix[][] = new int[a.length+1][b.length+1];
		
		for(int i=0; i<dpmatrix.length;i++) {
			for(int j=0;j<dpmatrix[0].length;j++) {
				
				if(i==0) dpmatrix[i][j] = j; // if i =0 means empty string -> string having j character require j times add
				
				else if(j==0) dpmatrix[i][j] = i; // from i char long string to empty string we need to delete i char.
				
				else if(a[i-1] == b[j-1]) { // means char at i-1 and j-1 are equal
					//now we need to worry about converting i-1 chars to j-1 which is available 
					//at dpmatrix[i-1][j-1]
					dpmatrix[i][j] = dpmatrix[i-1][j-1]; //copy from diagonal  upper - left
				}else{
					dpmatrix[i][j] = 1 + min (
							dpmatrix[i-1][j-1],
							dpmatrix[i][j-1],
							dpmatrix[i-1][j]
											);
				}
				
			}
		}
		return dpmatrix[a.length][b.length];
	}
	
	private static int min(int a,int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
	public static void main(String[] args) {
		String a = "sunday";
		String b = "saturday";
		System.out.println("Edit Distance = "+editDistanceDP(a.toCharArray(), b.toCharArray()));
		System.out.println("Edit Distance = "+editDistance(a.toCharArray(), b.toCharArray(),a.length(),b.length()));
	}
}
