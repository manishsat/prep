package my.prep.practice.linklist;

public class DoublyLinkList {

	private DoublyLinkListNode head;
	
	public void addNode(int value) {
		if(head == null) {
			head = new DoublyLinkListNode();
			head.setValue(value);
		}else{
			addToLast(value);
		}
		
	}
	
	private void addToLast(int value) {
		DoublyLinkListNode temp = head;
		while(temp.getNext() != null) {
			temp = temp.getNext();
		}
		
		temp.setNext(new DoublyLinkListNode(value));
	}
	
	public void print() {
		if(head == null) {
			System.out.println("List is empty");
			return;
		}
		DoublyLinkListNode  temp = head;
		while(temp != null) {
			System.out.print(temp.getValue() + "->");
			temp = temp.getNext();
		}
		System.out.println();
	}
	
	public DoublyLinkListNode getHead() {
		return head;
	}
}
