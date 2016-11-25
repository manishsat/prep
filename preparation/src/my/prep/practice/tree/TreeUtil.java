package my.prep.practice.tree;

import java.util.Stack;



public class TreeUtil {

	//convert tree to circular list
	public BinaryTreeNode<Integer> treeToList(BinaryTreeNode<Integer> root) {
		return convertTreeToList(root);
	}
	
	private BinaryTreeNode<Integer> convertTreeToList(BinaryTreeNode<Integer> root) {
		if(root == null) return root;
		BinaryTreeNode<Integer> leftCircularList = convertTreeToList(root.getLeft());
		
		BinaryTreeNode<Integer> rightCircularList = convertTreeToList(root.getRight());
		
		//now make root node as circular before concatenating
		root.setLeft(root);
		root.setRight(root);
		root = concatenate(leftCircularList, root);
		root = concatenate(root, rightCircularList);
		
		return root;
	}
	//the is going to concatenate 2 circular list
	private BinaryTreeNode<Integer> concatenate(BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
		if(a == null) return b;
		if(b== null) return a;
		
		BinaryTreeNode<Integer> aEnd = a.getLeft();
		BinaryTreeNode<Integer> bEnd = b.getLeft();
		a.setLeft(bEnd);
		bEnd.setRight(a);
		aEnd.setRight(b);
		b.setLeft(aEnd);
		
		return a;
	}
	
	//LCS in Binary Tree
	public BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> root,BinaryTreeNode<Integer> a,BinaryTreeNode<Integer> b) {
		Stack<BinaryTreeNode<Integer>> pathToA = findPathTo(root, a);
		
		Stack<BinaryTreeNode<Integer>> pathToB = findPathTo(root, b);
		BinaryTreeNode<Integer> lcsNode = null;
		while(!pathToA.isEmpty() && !pathToB.isEmpty()) {
			if(pathToA.pop().getValue().equals(pathToB.pop().getValue())) {
				lcsNode = pathToA.pop();
			}
		}
		
		return lcsNode;
	}
	private Stack<BinaryTreeNode<Integer>> findPathTo(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> n) {
		if(root == null) return null;
		
		if(root.getValue().equals(n.getValue())) {
			Stack<BinaryTreeNode<Integer>> stack = new Stack<BinaryTreeNode<Integer>>();
			stack.push(n);
			return stack;
		}
		
		Stack<BinaryTreeNode<Integer>> leftstack = findPathTo(root.getLeft(), n);
		Stack<BinaryTreeNode<Integer>> rightstack = null;
		if(leftstack ==null) {
			rightstack = findPathTo(root.getRight(), n);
		}
		
		if(leftstack != null) {
			leftstack.push(root);
			return leftstack;
		}
		if(rightstack != null) {
			rightstack.push(root);
			return rightstack;
		}
		return null;
	}
	
	//LCS in BST
	//O(log n)
	public BinaryTreeNode<Integer> lcsInBST(BinaryTreeNode<Integer> root,BinaryTreeNode<Integer> a,BinaryTreeNode<Integer> b) {
		
		if(root == null) return root;
		
		if(a == null || b == null) return root;
		
		if(a.getValue() < root.getValue() && b.getValue() < root.getValue()) {
			//means LCS is in the left side
			return lcsInBST(root.getLeft(),a,b);
		}else if (a.getValue() > root.getValue() && b.getValue() > root.getValue()) {
			return lcsInBST(root.getRight(),a,b);
		}else{
			return root;
		}
		
		
	}
	
	//Q. convert Binary Tree into Doubly Linklist
	public BinaryTreeNode<Integer> convertToDLL(BinaryTreeNode<Integer> root) {
		if(root == null) return null;
		
		BinaryTreeNode<Integer> leftDLL = convertToDLL(root.getLeft());
		
		if(leftDLL !=null) {
			BinaryTreeNode<Integer> temp = leftDLL;
			addToLast(temp,root);	
		}else{
			leftDLL = root;
		}
		
		BinaryTreeNode<Integer> rightDLL = convertToDLL(root.getRight());
		if(rightDLL !=null) {
			root.setRight(rightDLL);
			rightDLL.setLeft(root);
		}
		
		
		return leftDLL;
	}
	
	private void addToLast(BinaryTreeNode<Integer> head, BinaryTreeNode<Integer> node) {
		if(head == null) head = node;
		else {
			while(head.getRight() != null) {
			 head = head.getRight();	
		    }
			head.setRight(node);
			node.setLeft(head);
		}
	}
	
	//Q. Binary Tree Max path sum
	//Sum of a node = node.val + max sum from left + max sum from right
	//Q1. Is is possible for node to have negative values?
	
	public int maxPathSum(BinaryTreeNode<Integer> root) {
		
		if(root ==null) return 0;
		return root.getValue() + Math.max(maxPathSum(root.getLeft()),maxPathSum(root.getRight()));
		
	}
	
	//Q. maximum depth of a binary tree.
	public int maxDepthBT(BinaryTreeNode<Integer> root) {
		if(root ==null) return 0;
		int leftDepth = maxDepthBT(root.getLeft());
		int rightDepth = maxDepthBT(root.getRight());
		return 1 + (Math.max(leftDepth, rightDepth));
		
	}
}
