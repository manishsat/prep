package my.prep.practice.tree;

public class BinarySearchTree<T extends Comparable<T>> {
		BinaryTreeNode<T> root;
		public BinarySearchTree(T value) {
			this.root = new BinaryTreeNode<T>(value);
		}
		
		public void insert(T val) {
			internalAddValue(val, root);
		}
		
		private BinaryTreeNode internalAddValue(T val,BinaryTreeNode<T> root) {
			if(root == null) {
				root = new BinaryTreeNode<T>(val);
				return root;
			}
			if(root.getValue().compareTo(val) > 0) {
				root.setLeft(internalAddValue(val, root.getLeft()));
			}else{
				root.setRight(internalAddValue(val, root.getRight()));
			}
			return root;
		}
		
		public void delete(T val) {
			root = internalDelete(val, root);
		}
		
		private BinaryTreeNode<T> internalDelete(T val, BinaryTreeNode<T> root) {
			if(root == null) {
				return root;
			}
			
			if(root.getValue().compareTo(val) > 0) {
				//When root is greater then val
				root.setLeft(internalDelete(val, root.getLeft()));
				
			}else if (root.getValue().compareTo(val) < 0) {
				//When root is smaller then val
				root.setRight(internalDelete(val, root.getRight()));
			}else{
				//When root is equals to val
				if(root.getLeft() != null && root.getRight() != null) {
					//Root has both children
					//1. Find min from right sub-tree
					//2. replace root's value with min node's value.
					root.setValue(findMin(root.getRight()).getValue());
					//3. Now remove the node (findMin candidate) from right subtree.
					root.setRight(internalDelete(root.getValue(), root.getRight()));
				}else{
					root = (root.getRight()) == null ? root.getLeft() : root.getRight();
				}
			}
			return root;
		}
		
		private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
			if(root == null) {
				return null;
			}else if (root.getLeft() == null) {
				return root;
			}else{
				return findMin(root.getLeft());
			}
			
		}
		
		public void printTree() {
			if(root == null) {
				System.out.println("Tree is empty");
			}
			internalPrint(root);
			
		}
		private void internalPrint(BinaryTreeNode root) {
			if(root != null) {
				internalPrint(root.getLeft());
				System.out.println(root.getValue());
				internalPrint(root.getRight());	
			}
			
		}
		
		public static void main(String[] args) {
			BinarySearchTree tree = new BinarySearchTree<Integer>(10);
			tree.insert(5);
			tree.insert(16);
			tree.insert(2);
			tree.insert(8);
			tree.insert(9);
			tree.insert(1);
			tree.insert(3);
			tree.insert(7);
			tree.insert(6);
			tree.insert(12);
			tree.insert(20);
			tree.insert(11);
			tree.insert(14);
			tree.insert(25);
			
			tree.printTree();
			
		}
}
