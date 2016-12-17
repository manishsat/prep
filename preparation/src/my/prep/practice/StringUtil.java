package my.prep.practice;

public class StringUtil {
	
	public static void main(String[] args) {
		System.out.println(2/5);
		String s = "1234";
		printcombination(s.toCharArray(), 0, s.length()-1);
		String s1 = "";
		printcombination(s1.toCharArray(), 0, s1.length()-1);
		printcombination(null, 0, s1.length()-1);
		
		char[][] digitmap = {
                {'0','0','0',' '}, //0
                {'1','1','1',' '},//1
                {'a','b','c',' '},//2
                {'d','e','f',' '},//3
                {'g','h','i',' '},//4
                {'j','k','l',' '},//5
                {'m','n','o',' '},//6
                {'p','q','r','s'},//7
                {'t','u','v',' '},//8
                {'w','x','y','z'}//9
            };
	}
	
	public static boolean isPalindrome(char[] a) {
		
		for(int i =0,j=a.length-1;i<j;i++,j--) {
			if(a[i]!=a[j]) {
				return false;
			}
		}
		return true;
	}
	
	public static void printcombination(char[] a,int start,int end) {
		
		if(a == null||a.length == 0 ) {
			System.out.println("Empty array");
			return;
			
		}
		
		//Base case
		if(start == end) {
			System.out.println(a);
		}
		//otherwise we loop through elements starting ith position and print combination starting i+1
		for(int i=start;i<=end;i++) {
			if(i!= start  && a[i] == a[i-1]) { //this is to remove duplicates
				continue;
			}
			char t = a[start];
			a[start] = a[i];
			a[i] = t;
			
			printcombination(a, start+1, end); //one you are done swap back to the original position.
			
			t = a[start];
			a[start] = a[i];
			a[i] = t;
			
		}
	}
	//O(MN)
	//M: text length
	//N: pattern length
	public static boolean search(String text,String pat) {
		int pl = pat.length();
		int tl = text.length();
		//pattern can start from any position in text
		for(int i=0;i<=tl-pl;i++) {
			int j;
			for(j=0;j<pl;j++) {
				if(pat.charAt(j)!=text.charAt(i+j)) {
					break;
				}
			}
			if(j==pl) return true;
		}
		return false;
	}
	//Less back up
	public static boolean searchBetter(String text,String pat) {
		int pl = pat.length();
		int tl = text.length();
		int i,j;
		for(i=0,j=0;i<tl && j<pl;i++) {
			if(text.charAt(i) == pat.charAt(j)) {
				j++;
			}else{
				i=i-j;j=0;
			}
		}
		return (j==pl) ? true:false;
	}
	
	
	public static String LRS(String s){
		
		String maxCommonSub = null;
		
		int max = Integer.MIN_VALUE;
		
		//loop through every possible repeated string which start from ith place 
		//and repetition starts from i+1 place
		for(int i=0;i < s.length() -1;i++) {
			
			for(int j=i+1; j < s.length() -1 ; j++) {
				//lets find out of max repeated string 
				//and the sub-string starts from ith and repetition starts from jth place
				int com = commonSubStringLen(s,i,j);
				if(com > max) {
					max = com;
					maxCommonSub = s.substring(i,i+com);
				}
			}
		}
		
		return maxCommonSub;
	}
	
	private static int commonSubStringLen(String s, int start, int end) {
		int temp = 0;
		
		for(;start + temp < s.length() && 
				end + temp < s.length() &&
				s.charAt(start+temp) == s.charAt(end+temp); temp++) ;
		
		return temp;
	}
	
	
	
	
	
	
	

}
