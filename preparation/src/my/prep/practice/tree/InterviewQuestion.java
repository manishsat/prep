package my.prep.practice.tree;

import java.util.LinkedList;
import java.util.Queue;

public class InterviewQuestion {

	// This is the text editor interface. 
	// Anything you type or change here will be seen by the other person in real time.

	/*
	Connect a binary tree horizontally at each level from left to right using an extra pointer at each node. Node structure:

	Node {
	 Node left;
	 Node right;
	 int data;
	Node rightNeighbor = null;
	}
	 
	  A
	 B C
	DE FG

	result:
	     A
	    B->C
	 D->E->F->G
	*/ 


	public void connectHorizontially(BinaryTreeNode<Integer> root) {
	    
	    if(root == null) return; // or we can throw an exception
	    
	    Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
	    queue.offer(root);
	    
	    while(!queue.isEmpty()) {
	        
	        int size = queue.size();
	        BinaryTreeNode<Integer> node = queue.poll();
	        
	        addChildren(node,queue);
	        
	        for(int i=1;i<size;i++) {
	        	BinaryTreeNode<Integer> nextNode = queue.poll();
	            node.setRightNeighbor(nextNode);
	            node = nextNode;
	            addChildren(nextNode,queue);
	            //add children
	            
	        }
	        
	        node.setRightNeighbor(null);
	    }
	    
	    
	}

	public void connectHorizontiallyPart2(BinaryTreeNode<Integer> root) {
	    root.setRightNeighbor(null);
	    connectHorizontiallyInternal(root,root.getLeft(),root.getRight());
	}

	private void connectHorizontiallyInternal(BinaryTreeNode<Integer>  parent, BinaryTreeNode<Integer> left,BinaryTreeNode<Integer> right) {
	    if(left == null || right == null) return; 
	    
	    left.setRightNeighbor(right);
	    
	    connectHorizontiallyInternal(left, left.getLeft(),left.getRight());
	    
	    connectHorizontiallyInternal(right, right.getLeft(),right.getRight());
	    
	    left.getRight().setRightNeighbor(
	    		left.getRightNeighbor().getLeft());
	    
	    BinaryTreeNode<Integer> n1 = left.getRight(); BinaryTreeNode<Integer> n2 = right.getLeft();
	    
		    while(n1 !=null && n2 !=null) {
		    	join(n1,n2);
		    	n1 = n1.getRight();
		    	n2 =n2.getLeft();
		    }
	    
	    
	}
	
	private void join(BinaryTreeNode<Integer> from, BinaryTreeNode<Integer> to) {
		from.setRightNeighbor(to);
	}

	private void addChildren(BinaryTreeNode<Integer> node,Queue<BinaryTreeNode<Integer>> queue) {
	    if(node.getLeft() !=null) queue.offer(node.getLeft());
	    if(node.getRight() != null) queue.offer(node.getRight());
	}
}
