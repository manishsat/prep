package my.prep.practice;

/**
 * LP(i,j) = longest palindrome between i...j
 * 
 *  LP(i,j) = {
 *  				1 #i==j we have only 1 char
 *  				
 *  				1 #j=i+1 AND A[i]!=A[j]
 *  				
 *  				2 #j=i+1 AND A[i]==A[j]
 *  
 *  				2 + LP(i+1,j-1) #if A[i]==A[j], means 1st and last characters are same and the length is more then 2 characters
 *  
 *  				Max(  LP(i,j-1),  LP(i+1,j) )  #Otherwise
 *  		}
 *
 */
public class LongestPalindrome {

	/**
	 * Complexity will be exponential. 
	 * O(2^N)
	 * In worst case when the string is not palindrome, 
	 * On each step we divide into 2 sub problems and each sub problem goes up to the point where i==j
	 * which means the depth of the Binary tree would be N-1.
	 * and total nodes would be 2^N and O(1) to process each node.
	 * 
	 * 
	 * 
	 * 
	 */
	public static  int logestPalindrome(char[] a,int i,int j) {
		//Base conditions
		if(i==j) {
			return 1;
		}
		if(j==i+1 && a[i]!=a[j]) {
			return 1;
		}
		
		if(j==i+1 && a[i]==a[j]) {
			return 2;
		}
		//IF we reached here means we have more then 2 characters
		//and both characters are equal
		if(a[i] == a[j]) {
			return 2+logestPalindrome(a,i+1,j-1);
		}
		
		return Math.max(logestPalindrome(a,i,j-1),logestPalindrome(a,i+1,j)); 
	}
	
	public static void main(String[] args) {
		 String seq = "GEEKSFORGEEKS";
		 System.out.println("Longest Palindrome - " +logestPalindrome(seq.toCharArray(),0,seq.length()-1));
		 String seq1 = "ABCD";
		 System.out.println("Longest Palindrome - " +logestPalindrome(seq1.toCharArray(),0,seq1.length()-1));
		 
	}
}
