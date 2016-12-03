package my.prep.practice.linklist;

import java.util.HashSet;
import java.util.Stack;

public class LinkListUtil {
	
	public static void main(String[] args) {
		
		int arr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		//DoublyLinkList dList = new DoublyLinkList();
		LinkList list = new LinkList();
		for(int i:arr) {
			list.addNode(i);
		}
		
//		dList.print();
//		DoublyLinkListNode root = convertDLLToBST(dList.getHead());
//		printInorderOrderTraversalHelper(root);
//		
		list.print();
		list.setNewHead(rotateRight(list.getHead(), 14));
		list.print();
		list.setNewHead(swapPairs(list.getHead()));
		list.print();
		list.setNewHead(swapPairs(list.getHead()));
		list.print();
	}

	public boolean isLoopInLinkLIst(LinkListNode head) {
		LinkListNode slow = head;
		LinkListNode fast = head;
		while(fast!=null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			
			if(slow == fast) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}
	
	//Reference : http://javabypatel.blogspot.in/2015/12/detect-loop-in-linked-list.html
	
	public LinkListNode getStartNodeOfLoop(LinkListNode head) {
		//first find out loop 
		
		LinkListNode slow = head;
		LinkListNode fast = head;
		while(fast!=null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			
			if(slow == fast) {
				slow = head;
				while(slow != fast) {
					slow = slow.getNext();
					fast = fast.getNext();
				}
				
				return slow;
			}
		}
		
		return null; //indication of no loop
	}
	
	//Q: Number is represented in linked list such that each digit corresponds to a node in linked list. 
	//Add 1 to it. For example 1999 is represented as : (1->9 -> 9 -> 9 ) + 1 => (2->0->0->0)
	public LinkListNode addNumberToLinkList(LinkListNode head, int n) {
		LinkListNode node = addNumber(head,n);
		if(node != head && node.getValue() != -1) {
			//added extra node at the front.
			node.setNext(head);
			head = node;
		}
		return head;
	}
	
	
	public LinkListNode addNumber(LinkListNode head, int n) {
		if(head == null) return head;
		if(n < 0) throw new IllegalArgumentException("Negative number is not allowed ");
		if(n >= 10) throw new IllegalArgumentException(" only 0-9 numbers are allowed ");
		if(head.getNext()!=null) {
			LinkListNode node = addNumber(head.getNext(), n);
			if(node.getValue() == -1) { 
				return head; // no carry
			}else{
				return addToValue(head, node.getValue());
			}
		}else{
			return addToValue(head, n);
		}
		
	}
	
	private LinkListNode addToValue(LinkListNode head, int n) {
		int value = head.getValue();
		if(value + n > 9) {
			head.setValue((value + n) % 10);
			return new LinkListNode((value + n)/10);
		}else{
			head.setValue(value + n);
			return new LinkListNode(-1);
		}
	}
	
	//Q: Devise an algorithm to determine the Nth-to-Last element in a singly linked list of unknown length. If N = 0, then your algorithm must return the last 
	// Things to ask 
	// n positive/negative?
	// n > linklist size return null
	// n =0 return last element;
	//Approach 1: move the pointer N steps then start another pointer from head and move both the pointer step by step
	//First pointer will reach to null and the second pointer will point to nth last element.
	
	
	//Approach 2: Use recursion: once you reach at last node 
	//  	Then check n is 0 return a result {node, 0}
	//		else result {null,n-1}
	// and when the recursion folds back 
	
	public LinkListNode NtheLastElement(LinkListNode head,int n) {
		if(head == null) return null;
		
		LinkListNode current = head;
		for(int i=0 ; i<n;i++) {
			if(current.getNext() != null) {
				current = current.getNext();
			}else{
				return null;//means linklist is smaller then n
			}
		}
		//
		LinkListNode nthLastNode = head;
		while(current.getNext() != null) {
			current = current.getNext();
			nthLastNode = nthLastNode.getNext();
		}
		return nthLastNode;
		
	}
	
	//Q : convert sorted doubly link list into balanced Binary search tree
	// O(n + log n) -> O(n)
	public static DoublyLinkListNode convertDLLToBST(DoublyLinkListNode head) {
		
		if(head == null) return null;
		
		if(head.getPrev() == null || head.getNext() == null)
			return head;
		
		DoublyLinkListNode middle = getMiddleNode(head);
	    middle.getPrev().setNext(null);
	    DoublyLinkListNode leftBST = convertDLLToBST(head);
	    middle.getNext().setPrev(null);
	    DoublyLinkListNode rightBST = convertDLLToBST(middle.getNext());
	    
	    middle.setPrev(leftBST);
	    middle.setNext(rightBST);
		
	    return middle;	
		
	}
	
	private static DoublyLinkListNode getMiddleNode(DoublyLinkListNode head) {
		
		if(head == null) return null;
		
		DoublyLinkListNode fast = head;
		DoublyLinkListNode slow = head;
		
		while(fast != null && fast.getNext()!= null ) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return slow;
	}
	
	//Q Find intersection of two Linked Lists
	//Q1 - Are both list of same size or could be different ?
	//Q2 - intersection point could the first or last one.
	//Q3 - Is intersection point is always there?
	
	//O(m * n)
	public LinkListNode getIntersectionNode(LinkListNode list1, LinkListNode list2) {
		if(list1 == null || list2 ==null) return null;
		for(LinkListNode fnode = list1;fnode!=null;fnode = list1.getNext()) {
			for(LinkListNode snode = list2;snode!=null;snode = list2.getNext()) {
				if(fnode == snode) return fnode;
			}
		}
		return null;
	}
	
	//Another solution is to iterate first list and store in hash and then iterate second and check in the hash
	//O(m+n)
	//Space complexity O(m)
	public LinkListNode getIntersectionNode2(LinkListNode list1, LinkListNode list2) {
		if(list1 == null || list2 ==null) return null;
		LinkListNode temp = list1;
		HashSet<LinkListNode> nodeSet = new HashSet<LinkListNode>();
		
		while(temp!=null)  {
			nodeSet.add(temp);
			temp = temp.getNext();
		}
		temp = list2;
		while(temp!=null) {
			if(nodeSet.contains(temp)) {
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}
	
	//Solution 2: Find the length of list1 and list 2 by iterating.
	
	//if last node must be common in both the lists if that is not the case then there is no intersection 
	//find the diff = len1 - len2 if(len1>len2)
	//move list1 by diff first
	//then move both one by one and check if they meet.
	//O(m+n)
	//O(1) space complexity
	public LinkListNode getIntersectionNode3(LinkListNode list1, LinkListNode list2) {
		if(list1 == null || list2 == null) return null;
		
		if(list1 == list2) return list1;
		int len1 = 0;
		LinkListNode temp = list1;
		while(temp != null) {
			temp = temp.getNext();
			len1 ++;
		}
		
		
		int len2 = 0;
		temp = list1;
		while(temp != null) {
			temp = temp.getNext();
			len2 ++;
		}
		int diff = 0;
		if(len1>len2) {
			diff = len1 - len2;
			moveHead(list1,diff);
		}else{
			diff = len2 - len1;
			moveHead(list2,diff);
		}
		
		return findIntersectionOfSameSizeLinkList(list1,list2);
		
	}
	
	private void moveHead(LinkListNode head,int n) {
		for(int i=0;i<n;i++) {
			head = head.getNext();
		}
	}
	
	private LinkListNode findIntersectionOfSameSizeLinkList(LinkListNode list1, LinkListNode list2) {
		
		while (list1 !=null && list2 != null) {
			if(list1 == list2) return list1;
			
			list1 = list1.getNext();
			
			list2 = list2.getNext();
		}
		
		return null;
	}
	
	
	
	
	
	private static void printInorderOrderTraversalHelper(DoublyLinkListNode root) {
		if (root == null) {
		    return;
		}
		
		printInorderOrderTraversalHelper(root.getPrev());
		System.out.print(root.getValue() + " " );
		printInorderOrderTraversalHelper(root.getNext());
	}

	//Dup?
	//We are traversing both the lists and in worst case if both the list are of same size then 
	//Time complexity O(N) + O(M)
	//M and N is the length of the lists.
	public static LinkListNode mergeTwoSortedList(LinkListNode head1, LinkListNode head2) {
		if(head1 ==null) return head2;
		if(head2 == null) return head1;
		LinkListNode result = null;
		if(head1.getValue() < head2.getValue()) {
			result = head1;
			result.setNext(mergeTwoSortedList(head1.getNext(), head2));
		}else if(head1.getValue() > head2.getValue()){
			result = head2;
			result.setNext(mergeTwoSortedList(head1, head2.getNext()));
		}else{
			//both have same value
			result = head1;
			//move both
			result.setNext(mergeTwoSortedList(head1.getNext(), head2.getNext()));
		}
		return result;
	}
	
	//Q. Delete duplicate-value nodes from a sorted linked list
	public LinkListNode deleteDups(LinkListNode head) {
		if(head == null) return null;
		LinkListNode ptr = head;
	    while(ptr !=null && ptr.getNext() !=null) {
	    	LinkListNode nextPtr = ptr.getNext();
	        while(nextPtr!=null && nextPtr.getValue() == ptr.getValue()) {
	            ptr.setNext(nextPtr.getNext());
	            nextPtr = nextPtr.getNext();
	        }
	        ptr = ptr.getNext();
	    }
	    return head;
	}
	//How many rotations? more then the length of the list?
	//O(N)
	public static LinkListNode rotateRight(LinkListNode head, int rotations) {
	    if(head == null) return null;
	    
	    int length = getLength(head);
	    rotations = rotations % length;
	    return rotateToRight(head,rotations);
	    
	}
	
	private static  LinkListNode rotateToRight(LinkListNode head, int rotations) {
		if(head == null) return null;
		if(rotations <= 0) return head;
		
		LinkListNode behindPtr = head;
	    LinkListNode aheadPtr = head;
	    Stack<LinkListNode> stack = new Stack<LinkListNode>();
	    for(int i=0;i<rotations;i++) {
	        aheadPtr = aheadPtr.getNext();;
	    }
	    while(aheadPtr.getNext() != null) {
	        aheadPtr = aheadPtr.getNext();
	        //stack.push(aheadPtr);
	        behindPtr = behindPtr.getNext();
	    }
	    
	    LinkListNode temp = behindPtr.getNext();
	    behindPtr.setNext(null);
	    aheadPtr.setNext(head);
	    head = temp;
	    return head;
	}
	private static int getLength(LinkListNode head) {
		if(head == null) return 0;
		int length = 0;
		LinkListNode node = head;
		while(node!=null) {
			length++;
			node = node.getNext();
		}
		return length;
	}
	//Q Given a linked list, swap every two adjacent nodes and return its head.
	//what should I do if there are odd number of nodes? Leave the last node as it is? I assume
	//O(N)
	public static LinkListNode swapPairs(LinkListNode a) {
	    
	    if(a == null) return a;
	    LinkListNode head = a;
	    LinkListNode temp = null;
	    LinkListNode prev = null;
	    while(head!=null && head.getNext()!=null) {
	       temp =  head.getNext().getNext();
	       head.getNext().setNext(head);
	      
	       if(prev!=null) {
	            
	           prev.setNext(head.getNext());
	       }else{
	           prev = head;
	           a = head.getNext();
	       }
	       head.setNext(temp);
	       prev = head;
	       head = temp;
	    }
	    return a;
	    
	}
	
}

