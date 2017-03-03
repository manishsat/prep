package my.prep.practice.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PointNextPointer {

		public static void main(String[] args) {
			Node root = new Node();
			
			root.val = 35; //root
			
			addChild(root,20);
			addChild(root,60);
			
			//left sub tree
			addChild(root,10);
			addChild(root,30);
			
			addChild(root,4);
			addChild(root,15);
			
			addChild(root,25);
			addChild(root,33);
			
			
			addChild(root,3);
			addChild(root,5);
			
			addChild(root,14);
			addChild(root,17);
			
			addChild(root,21);
			addChild(root,26);
			
			addChild(root,32);
			addChild(root,34);
			
			//right sub tree
			addChild(root,45);
			addChild(root,70);
			
			addChild(root,40);
			addChild(root,50);
			
			addChild(root,65);
			addChild(root,78);
			
			addChild(root,36);
			addChild(root,44);
			
			
			addChild(root,48);
			addChild(root,55);
			
			addChild(root,63);
			addChild(root,68);
			
			addChild(root,75);
			addChild(root,80);
			
			
			
			//pointNextPointer(root);
			pointNextPointerRecursice(root);
			print(root);
					
			
		}
		
		public static Node addChild(Node root, int value) {
			if(root == null) return new Node(value);
			if(value>root.val) root.right = addChild(root.right,value);
			else root.left = addChild(root.left,value);
			return root;
			
		}
		
		private static void addChildren(Node parent,int left,int right) {
			parent.left = new Node(left);
			parent.right = new Node(right);
		}
		//Time O(n)
		//Space O(1)
		public static void pointNextPointerRecursice(Node root) {
			if(root == null) return;
			pointNextPointerRecursiceInternal(root.left,root.right);
		}
		
		private static void pointNextPointerRecursiceInternal(Node left,Node right) {
			if(left==null || right ==null) return;
			
			left.next = right;
			pointNextPointerRecursiceInternal(left.left,left.right);
			
			pointNextPointerRecursiceInternal(right.left,right.right);
			
			Node n1 = left.right;
			Node n2 = left.next.left;
			while(n1!=null) {
				n1.next = n2;
				n1 = n1.right;
				n2 = n2.left;
			}
		}
		//Time O(n)
		//Space O(no of leaf node)
		public static Node pointNextPointer(Node root) {
			
			if(root == null) return null;
			
			Queue<Node> queue = new LinkedList<Node>();
			queue.offer(root);
			
			while(!queue.isEmpty()) {
				int size = queue.size();
				Node current = queue.poll();
				
				addChildrenInQueue(current, queue);
				for(int i=1;i<size;i++) {
					Node nextNode = queue.poll();
					current.next = nextNode;
					current = nextNode;
					addChildrenInQueue(current, queue);
				}
				
				current.next = null;
			}
				
			return root;
		}
		
		private static void addChildrenInQueue(Node node,Queue<Node> queue) {
			if(node.left !=null ) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
		
		public static void print(Node root) {
			Node temp = root;
			while(temp!=null) {
				System.out.print(temp.val + "->");
				temp = temp.next;
			}
			System.out.println("null");
			if(root.left!=null) print(root.left);
		}
		private static class Node {
			private int val;
			private Node left;
			private Node right;
			private Node next;
			
			public Node() {}
			public Node(int val) {
				this.val = val;
			}
			
			public String toString() {
				return String.valueOf(val);
			}
		}
}
