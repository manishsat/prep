package my.prep.practice.linklist;

public class LinkListNode {
	private int value;
	private LinkListNode next;

	public LinkListNode() {}
	public LinkListNode(int val) {
		this.value = val;
	}
	
	public LinkListNode getNext() {
		return next;
	}
	
	public void setNext(LinkListNode node) {
		this.next = node;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
