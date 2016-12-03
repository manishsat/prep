package my.prep.practice.linklist;

public class LinkList {
	private LinkListNode head;
	
	public void addNode(int value) {
		if(head == null) {
			head = new LinkListNode();
			head.setValue(value);
		}else{
			addToLast(value);
		}
		
	}
	
	public void addNode(LinkListNode node) {
		if(head == null) {
			head = node;
		}else{
			
		}
	}
	
	private void addToLast(LinkListNode value) {
		LinkListNode temp = head;
		while(temp.getNext() != null) {
			temp = temp.getNext();
		}
		temp.setNext(value);
	}
	private void addToLast(int value) {
		LinkListNode temp = head;
		while(temp.getNext() != null) {
			temp = temp.getNext();
		}
		
		temp.setNext(new LinkListNode(value));
	}
	
	
	
	public void print() {
		if(head == null) {
			System.out.println("List is empty");
			return;
		}
		LinkListNode  temp = head;
		while(temp != null) {
			System.out.print(temp.getValue() + "->");
			temp = temp.getNext();
		}
		System.out.println();
	}
	
	public LinkListNode getHead() {
		return head;
	}
	public void setNewHead(LinkListNode head) {
		this.head = head;
	}
}
