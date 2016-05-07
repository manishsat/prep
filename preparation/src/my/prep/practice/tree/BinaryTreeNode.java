package my.prep.practice.tree;

public class BinaryTreeNode<T extends Comparable> {
	private T value;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	
	public BinaryTreeNode(T value) {
		this.value = value;
	}
	
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T val) {
		this.value =val;
	}
	
	public BinaryTreeNode<T> getLeft() {
		return left;
		
	}
	
	public BinaryTreeNode<T> getRight() {
		return right;
		
	}
}