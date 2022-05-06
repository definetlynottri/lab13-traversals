package bst;

import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {
	
	private static class BSTNode<T extends Comparable<T>>{
		private T data;
		private BSTNode<T> leftChild;
		private BSTNode<T> rightChild;
		
		public BSTNode(T data) {
			this.data = data;
		}
		
		public String toString() {
			return data.toString();
		}
	}
	
	private BSTNode<T> root;
	
	public void insert(T data) {
		root = recursiveInsert(root,data);
	}
	
	private BSTNode<T> recursiveInsert(BSTNode<T> node, T data) {
		if(node == null) {
			return new BSTNode<T>(data);
		}
		
		else if(data.compareTo(node.data)<0) {
			node.leftChild = recursiveInsert(node.leftChild,data);
		}
		else if(data.compareTo(node.data)>0) {
			node.rightChild = recursiveInsert(node.rightChild,data);
		}
		return node;
	}
	
	public void delete(T data) {
		root = recursiveDelete(root,data);
	}
	
	private BSTNode<T> recursiveDelete(BSTNode<T> node,T data){
		if(node == null) {
			return node;
		}
		else {
		if(data.compareTo(node.data)<0) {
			node.leftChild = recursiveDelete(node.leftChild,data);
		}
		else if(data.compareTo(node.data)>0) {
			node.rightChild = recursiveDelete(node.rightChild,data);
		}
		else {//we found the node to delete
			if(node.leftChild==null && node.rightChild == null) {
				return null;
			}
			else if(node.leftChild == null) {
				return node.rightChild;
			}
			else if(node.rightChild == null) {
				return node.leftChild;
			}
			else {//Still need to handle the case with two children
				BSTNode<T> predecessor = getMax(node.leftChild);
				T d = predecessor.data;
				node.data = d;//update data at node
				//remove predecessor node
				node.leftChild = recursiveDelete(node.leftChild,d);
			}
		}
		return node;
		}
	}
	
	//assumes root is not null
	public BSTNode<T> getMax(BSTNode<T> node){
		while(node.rightChild!= null) {
			node = node.rightChild;
		}
		return node;
	}

	//assumes root is not null
	public BSTNode<T> getMin(BSTNode<T> node){
		while(node.leftChild!=null) {
			node = node.leftChild;
		}
		return node;
	}
	
	public boolean contains(T data) {
		return find(data)!=null;
	}
	
	public BSTNode<T> find(T key) {
		return recursiveFind(root,key);
	}
	
	private BSTNode<T> recursiveFind(BSTNode<T> node,T key) {
		//base case, made it to the end or I found it
		if(node == null || key.equals(node.data)) {
			return node;
		}
		if(key.compareTo(node.data)<0) {
			return recursiveFind(node.leftChild,key);
		}
		else {
			return recursiveFind(node.rightChild,key);
		}
		
	}
	
	//Traverse the tree in an preorder fashion
	//Print the current node first and then recurse on the children
	public void preOrder() {
		System.out.println("Sean");
		preOrderRecurse(root); 
	}
	
	private void preOrderRecurse(BSTNode<T> node) {
		
	}
	
	//Traverse the tree in an preorder fashion but using a stack
	//Print the current node first and then recurse on the children
	public void preOrderStack() {
		Stack<BSTNode<T>> pre = new Stack<BSTNode<T>>();
		System.out.println("PreOrder test commit");
	}
		

	//Traverse the tree in an inorder fashion
	//Recursively print the left side of the current node, then the current node, 
	//then recursively print the right side of current node
	//For a bst this will print the values in sorted order from smallest to largest
	public void inOrder() {
		inOrderRecurse(root); 
		System.out.println("InOrder test commit");
	}
	
	public void inOrderRecurse(BSTNode<T> node) {
		if(node==null) {
			return;
		}
		System.out.println(node.data);
		inOrderRecurse(node.leftChild);
		inOrderRecurse(node.rightChild);
	}
	//Traverse the tree in an inorder fashion but using a stack
	public void inOrderStack() {
		Stack<BSTNode<T>> in = new Stack<BSTNode<T>>();
		preOrderaddToStack(root, in);
		while(in.size()>0) {
			System.out.println(in.pop().data);
		}
		
	}
	
	public void preOrderaddToStack(BSTNode<T> node, Stack<BSTNode<T>> in) {
		if(node==null) {
			return;
		}
		preOrderaddToStack(node.leftChild, in);
		preOrderaddToStack(node.rightChild, in);
		in.add(node);
	}
	
	//Traverse the tree in an postorder fashion
	//Recurse on the children and then print the value in the current node
	public void postOrder() {
		postOrderRecurse(root); 
		System.out.println("PostOrder test Commit");
	}
	
	public void postOrderRecurse(BSTNode<T> node) {
		
		
	}
	
	//Traverse the tree in an postorder fashion uses Stacks. 
	//This is more difficult than the other traversals using a Stack
	//I suggest using two stacks. Think about the order you want the elements
	//to appear on the stack you will print.
	public void postOrderStack() {
		Stack<BSTNode<T>> post = new Stack<>();
		Stack<BSTNode<T>> postHelper = new Stack<>();
		if(root!=null) {
			postHelper.push(root);
			while(!postHelper.isEmpty()) {
				//how should post and postHelper be updated?
			}
			
			while(!post.isEmpty()) {
				BSTNode<T> node = post.pop();
				System.out.print(node + " ");
			}
		}

	}
	
	public String toString() {
		return recursiveToString(root, "");		
	}	

	private String recursiveToString(BSTNode<T> node, String indent) {
		
		if(node == null) {return "";}
		else {
			return recursiveToString(node.rightChild,indent + "    ")+ 
			"\n" + indent  +node.data +
			recursiveToString(node.leftChild,indent + "    ");
		}	
	}
	
	public static void main(String[] args) {
		//Test Tree
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(9);
		bst.insert(7);
		bst.insert(11);
		bst.insert(2);
		bst.insert(8);
		bst.insert(15);
		bst.insert(10);
		bst.insert(3);
		System.out.println(bst);
	
		System.out.println("In Order Traversals");
		bst.inOrder();
		System.out.println();
		bst.inOrderStack();
		System.out.println();
		System.out.println("Pre Order Traversals");
		bst.preOrder();
		System.out.println();
		bst.preOrderStack();
		System.out.println();
		System.out.println("Post Order Traversals");
		bst.postOrder();
		System.out.println();
		bst.postOrderStack();
		
		
	}
	

}

