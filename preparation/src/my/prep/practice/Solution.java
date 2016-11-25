package my.prep.practice;

import java.util.ArrayList;

public class Solution {
	
	public static void main(String[] args) {
		System.out.println(letterCombinations("2"));
	}
	public static  ArrayList<String> letterCombinations(String a) {
	    if(a == null|| a.isEmpty()) return null;
	    ArrayList<String> result = new ArrayList<String>();
	    char[][] digitmap = {
	                            {'0','0','0','#'}, //0
	                            {'1','1','1','#'},//1
	                            {'a','b','c','#'},//2
	                            {'d','e','f','#'},//3
	                            {'g','h','i','#'},//4
	                            {'j','k','l','#'},//5
	                            {'m','n','o','#'},//6
	                            {'p','q','r','s'},//7
	                            {'t','u','v','#'},//8
	                            {'w','x','y','z'}//9
	                        };
	  
	   int val = Integer.valueOf(a);
	   
	   return comb(digitmap,a,0);
        	                        
	}
	
	private static ArrayList<String> comb(char[][] digitmap,String str,int index) {
	    
	    if(str.length() == index) return null;
	    
	    int val = Integer.valueOf(str.charAt(index)+"");
	    
	    ArrayList<String> result = new ArrayList<String>();
	    
	    ArrayList<String> list = getList(digitmap,val);
	    
	    ArrayList<String> sublist = comb(digitmap,str,++index);
	    if(sublist != null) {
	            for(String i:list) {
	                for(String j:sublist) {
	                    result.add(i+j);
	                }
	            }
	    }else{
	        result = list;
	    }
	    return result;
	}
	
	private static ArrayList<String> getList(char[][] digitmap,int v) {
	    
	    ArrayList<String> result = new ArrayList<String>();
	    
	    if(v <= 1){
	        result.add(String.valueOf(v));
	        
	    }else if(v < 10) {
	        for(int i=0;i<digitmap[v].length;i++) {
	            if(digitmap[v][i] != '#')
	                result.add(String.valueOf(digitmap[v][i]));
	        }
	        return result;
	    }
	    
	    return result;
	}
}