package my.prep.practice;

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
 *								  EditDistance(s1,s2-1) + 1  // if we can convert s1 to s2-1 and we need to Insert last charater
 *
 *								  ,
 *								  EditDistance(s1-1,s2) + 1	// if we can covert s1-1 to s2 and we just need to remove last char from s1	   								
 *   								
 *   }
 * 
 */
public class EditDistance {

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
				
				if(i==0) dpmatrix[i][j] = j;
				
				else if(j==0) dpmatrix[i][j] = i;
				
				else if(a[i-1] == b[j-1]) {
					dpmatrix[i][j] = dpmatrix[i-1][j-1]; //copy from diagnal  upper - left
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
