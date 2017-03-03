package com.prep.practice.trie;

public class RefdataCountFinder {
	private TrieNode root;
	
	public void addOrUpdateRefDataCount(String refdata) {
		if(refdata == null) throw new IllegalArgumentException("refdata is null");
		int startingPos = 0;
		char[] refArray = refdata.toCharArray();
		if(root == null) {
			root.setValue(refArray[startingPos]);
			root.setCount(1);
			startingPos = 1;
		}
		TrieNode temp = root;
		for(;startingPos<refArray.length;startingPos++) {
			int c = refArray[startingPos];
			TrieNode cd = temp.getChildren()[c];
			if(cd == null) {
				cd = new TrieNode();
				cd.setValue(refArray[startingPos]);
				cd.setCount(1);
				temp.getChildren()[c] = cd;//add new 
			}else{
				cd.setCount(cd.getCount() + 1); // update the count
			}
			
		}//end for
		
	}
	
	public long findClickCount(String prefix) {
		if(prefix == null) throw new IllegalArgumentException("prefix is null");
		if(root == null) return 0;
		char[] prefixArray = prefix.toCharArray();
		TrieNode temp = root;
		for(int startingPos = 0;startingPos<prefixArray.length;startingPos++) {
			int c = prefixArray[startingPos];
			temp = temp.getChildren()[c];
			if(temp == null) break;
		}
		
		if(temp == null) return 0;
		return temp.getCount();
				
	}
	
	
	
}
