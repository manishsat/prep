package com.prep.practice.trie;

public class TrieNode {

	private char value;
	private TrieNode[] children = new TrieNode[40];
	private long count;
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public TrieNode[] getChildren() {
		return children;
	}
	public void setChildren(TrieNode[] children) {
		this.children = children;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
}
