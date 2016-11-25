package my.prep.practice.linklist;

public class DoublyLinkListNode {

	private int value;
	private DoublyLinkListNode prev;
	private DoublyLinkListNode next;
	
	public DoublyLinkListNode() {}
	
	public DoublyLinkListNode(int value) {this.value = value;}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public DoublyLinkListNode getPrev() {
		return prev;
	}
	public void setPrev(DoublyLinkListNode prev) {
		this.prev = prev;
	}
	public DoublyLinkListNode getNext() {
		return next;
	}
	public void setNext(DoublyLinkListNode next) {
		this.next = next;
	}
	
	
}
