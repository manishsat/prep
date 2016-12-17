package my.prep.practice.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;



public class TreeUtil {
	
	public static void main(String[] agrs) {
		String s = "10,5,3,-1,-1,8,7,-1,-1,-1,2,-1,-1";
		System.out.println("String before serialization "+s);
		BinaryTreeNode<Integer> root = deSerializePreOrder(s);
		System.out.println("After serialization "+serializeBTPreOrder(root));
		//http://javabypatel.blogspot.in/2016/12/print-nodes-at-k-distance-from-leaf.html
		String s1 = "4,2,1,-1,-1,3,9,-1,10,-1,-1,-1,6,5,-1,-1,7,-1,-1";
		BinaryTreeNode<Integer> root1 = deSerializePreOrder(s1);
		System.out.println("String before serialization "+s1);
		System.out.println("After serialization "+serializeBTPreOrder(root1));
		System.out.println("Kth Elements from Leaf " +kthElementsFromLeaf(root1,5));
		
		System.out.println("Diameter of the tree " +diameter(root1));
		
		String s2 = "5,8,6,15,-1,-1,18,11,21,-1,-1,22,-1,-1,-1,7,-1,3,2,-1,-1,1,-1,17,-1,-1,9,-1,11,-1,-1";
		BinaryTreeNode<Integer> root2= deSerializePreOrder(s2);
		System.out.println("Diameter of the tree " +diameter(root2));
		System.out.println("Root to leaf sum " +hasPathEqSum(root2, 25));
		
		printRootToLeafPath(root2);
	}
	
	
	public static List<BinaryTreeNode<Integer>> kthElementsFromLeaf(BinaryTreeNode<Integer> root,int k) {
		List<BinaryTreeNode<Integer>> list = new ArrayList<BinaryTreeNode<Integer>>();
		kthElementsFromLeafInternal(root,list,k);
		return list;
		
	}
	
	private static int[] kthElementsFromLeafInternal(BinaryTreeNode<Integer> root,List<BinaryTreeNode<Integer>> list,int k) {
		
		if(root.getLeft() == null && root.getRight() == null) {
			//leaf node
			if(k==0) {
				list.add(root);
			}
			int[] minmax = new int[2];
			minmax[0] =1;
			minmax[1] =1;
			return minmax;
		}
		
		int[] leftDistance = null;
		int[] rightDistance = null;
		if(root.getLeft() != null) {
			leftDistance = kthElementsFromLeafInternal(root.getLeft(),list,k);
		}
		if(root.getRight() != null) {
			rightDistance = kthElementsFromLeafInternal(root.getRight(),list,k);
			
		}
		int minDistance = Integer.MAX_VALUE;
		int maxDistance = Integer.MIN_VALUE;
		if(leftDistance!=null) {
			minDistance = Math.min(minDistance,Math.min(leftDistance[0], leftDistance[1]));
			maxDistance = Math.max(maxDistance,Math.max(leftDistance[0], leftDistance[1]));
			
			if(leftDistance[0]==k ||leftDistance[1]==k) {
				list.add(root);
			}
		}
		if(rightDistance!=null) {
			minDistance = Math.min(minDistance,Math.min(rightDistance[0], rightDistance[1]));
			maxDistance = Math.max(maxDistance,Math.max(rightDistance[0], rightDistance[1]));
		
			if(rightDistance[0]==k ||rightDistance[1]==k) {
				if(!list.contains(root))list.add(root);
			}
		}
		
		int[] minmax = {minDistance+1,maxDistance+1};
		return minmax;
	}
	
	public static String serializeBTPreOrder(BinaryTreeNode<Integer> root) {
		if(root == null) return null;
		StringBuffer strBuf = new StringBuffer();
		serializeBTPreOrderInternal(root,strBuf);
		return strBuf.toString();
	}
	
	private static void serializeBTPreOrderInternal(BinaryTreeNode<Integer> root, StringBuffer strBuf) {
		if(root == null) {
			strBuf.append("-1");
			strBuf.append(",");
			return;
		}
		strBuf.append(String.valueOf(root.getValue()));
		strBuf.append(",");
		
		serializeBTPreOrderInternal(root.getLeft(),strBuf);
		serializeBTPreOrderInternal(root.getRight(),strBuf);
			
	}
	
	//pre order traversal while serializing.
	public static BinaryTreeNode<Integer> deSerializePreOrder(String data) {
		if(data == null || data.isEmpty()) {
			return null;
		}
		StringTokenizer strToken = new StringTokenizer(data,",");
		return deSerializePreOrderInternal(strToken);
	}
	private static BinaryTreeNode<Integer> deSerializePreOrderInternal(StringTokenizer strToekn) {
		if(!strToekn.hasMoreTokens()) return null;
		String nextToken = strToekn.nextToken();
		if("-1".equals(nextToken)) {
			return null;
		}
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(Integer.parseInt(nextToken));
		root.setLeft(deSerializePreOrderInternal(strToekn));
		root.setRight(deSerializePreOrderInternal(strToekn));
		return root;
	}
	//level order traversal.
	public BinaryTreeNode<Integer> deserializeBinaryTree(String data) {
		if(data == null || data.isEmpty()) return null;
		String arr [] = data.split(",");
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(Integer.parseInt(arr[0]));
		LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
		queue.offer(root);
		
		int position = 1;
		while(!queue.isEmpty()) {
			BinaryTreeNode<Integer> node = queue.remove();
			if(node!=null) {
				if(!arr[position].equals("-1")) {
					root.setLeft(new BinaryTreeNode<Integer>(Integer.parseInt(arr[position])));
					if(!queue.offer(root.getLeft())) {
						throw new RuntimeException();
					}
				}else{
					root.setLeft(null);
					queue.offer(null);
				}
				
				position ++;
				
				if(!arr[position].equals("-1")) {
					root.setRight(new BinaryTreeNode<Integer>(Integer.parseInt(arr[position])));
					if(!queue.offer(root.getLeft())) {
						throw new RuntimeException();
					}
				}else{
					root.setRight(null);
					queue.offer(null);
				}
				
			}
			
			position ++;
		}
		
		return root;
		
	}

	

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
	//LCS in Binary Tree recursive simple solution
	public BinaryTreeNode<Integer> lcaRecursive(BinaryTreeNode<Integer> root,BinaryTreeNode<Integer> a,BinaryTreeNode<Integer> b) {
		if(root == null) {
			return null;
		}
		if(root.getValue().equals(a) || root.getValue().equals(b)) {
			return root;
		}
		BinaryTreeNode<Integer> left = lcaRecursive(root.getLeft(),a,b); // left has a or b
		BinaryTreeNode<Integer> right = lcaRecursive(root.getRight(),a,b); // right has a or b
		if(left!=null && right!=null) return root; //means root is the lca
		
		return (left!=null) ?left: right; //return which ever is not null
		
	}
	//LCS in Binary Tree only work when tree is balanced
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
	//LCS in Binary Tree even un-balanced
	public static int lca(BinaryTreeNode<Integer> root, int val1, int val2) {
	    if(root == null) return -1;
	    Stack<BinaryTreeNode<Integer>> val1Path = new Stack<BinaryTreeNode<Integer>>();
	    Stack<BinaryTreeNode<Integer>> val2Path = new Stack<BinaryTreeNode<Integer>>();
	    pathToNode(val1Path,root,val1);
	    pathToNode(val2Path,root,val2);
	    if(val1Path.empty() || val2Path.empty()) return -1;
	    
	    while(!val1Path.empty()) {
	    	BinaryTreeNode<Integer> foundNode = isAvailableInPath(val2Path,val1Path.pop().getValue()) ;
	        if(foundNode!= null) {
	            return foundNode.getValue();
	        }
	    }
	    return -1;
	}
	
	private static BinaryTreeNode<Integer> isAvailableInPath(Stack<BinaryTreeNode<Integer>> path, int val) {
	    Stack<BinaryTreeNode<Integer>> temp = new Stack<BinaryTreeNode<Integer>>();
	    BinaryTreeNode<Integer> node = null;
	    while(!path.empty()) {
	        node = path.pop();
	        if(node.getValue() == val){
	          break;  
	        } else{
	            temp.push(node);
	        }
	        
	    }
	    while(!temp.empty()) {
	        path.push(temp.pop());
	    }
	    return  (node.getValue() == val) ? node : null;
	}
	private static BinaryTreeNode<Integer> pathToNode(Stack<BinaryTreeNode<Integer>> stack,BinaryTreeNode<Integer> root,int val1) {
	    if(root == null) {
	        return null;
	    }
	    if(root.getValue() == val1) {
	        stack.push(root);
	        return root;
	    }
	    stack.push(root);
	    
	    BinaryTreeNode<Integer> leftSide= pathToNode(stack, root.getLeft(),val1);
	    BinaryTreeNode<Integer> rightSide =null;
	    if(leftSide == null) {
	        rightSide = pathToNode(stack, root.getRight(),val1);
	    }
	    
	    if(leftSide == null && rightSide == null) {
	        stack.pop(); // pop out if this root is not in the path to val
	        return null;
	    }
	    
	    return (rightSide == null)? leftSide : rightSide;
	   
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
	
	public boolean isIdentical(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
		
		if(root1 == null && root2 == null) {
			return true;
		}
		if(root1 == null || root2 == null) {
			return false;
		}
		
		return (root1.getValue() == root2.getValue()) && isIdentical(root1.getLeft(), root2.getLeft()) && isIdentical(root1.getRight(), root2.getRight());
	}
	
	//Diameter of a binary tree.
	//Diameter of a node is left height + right height + 1
	//so every node has a diameter of itself but every node has to compare
	//it left diameter and right diameter and its own diameter and return the max.
	// O (n)
	public static int diameter(BinaryTreeNode<Integer> root) {
		return diameterInternal(root).diameter;
	}
	private static HeightDiaMeterDO diameterInternal(BinaryTreeNode<Integer> root) {
		if(root == null) {
			HeightDiaMeterDO hdDO = new HeightDiaMeterDO();
			hdDO.height = 0;
			hdDO.diameter = 0;
			return hdDO;
		}
		HeightDiaMeterDO leftDO = diameterInternal(root.getLeft());
		HeightDiaMeterDO rightDO = diameterInternal(root.getRight());
		HeightDiaMeterDO hdDO = new HeightDiaMeterDO();
		hdDO.height = Math.max(leftDO.height , rightDO.height) + 1;
		
		hdDO.diameter = Math.max(leftDO.height + rightDO.height + 1, Math.max(leftDO.diameter, rightDO.diameter));
		
		return hdDO;
	}
	
	private static class HeightDiaMeterDO {
		int height;
		int diameter;
	}
	
	//Root to leaf path sum equal to a given number
	
	public static boolean hasPathEqSum(BinaryTreeNode<Integer> root,int sum) {
		if(root == null || root.getValue() > sum) {
			return false;
		}
		
		if(isLeafNode(root) && root.getValue() == sum) {
			return true;
		}
		
		return hasPathEqSum(root.getLeft(), sum-root.getValue()) || hasPathEqSum(root.getRight(), sum-root.getValue());
	}
	
	private static boolean isLeafNode(BinaryTreeNode<Integer> leaf) {
		return (leaf.getLeft() == null && leaf.getRight() == null);
	}
	//Print all root to leaf nodes.
	public static void printRootToLeafPath(BinaryTreeNode<Integer> root) {
		printRootToLeafPathInternal(root,new ArrayList<BinaryTreeNode<Integer>>());
	}
	private static void printRootToLeafPathInternal(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> list) {
		if(root == null) return;
		
		if(isLeafNode(root)) {
			list.add(root);
			System.out.println(list);
			list.remove(root);
		}
		list.add(root);
		printRootToLeafPathInternal(root.getLeft(),list);
		printRootToLeafPathInternal(root.getRight(),list);
		list.remove(root);
		
	}
}
