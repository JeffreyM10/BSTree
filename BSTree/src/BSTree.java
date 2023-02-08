import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import javax.management.openmbean.OpenMBeanAttributeInfo;

public class BSTree<E> implements Iterable<E>
{
	class Node
	{
		E data;
		Node left;
		Node right;
		Node parent;
		Node (E d)
		{
			this.data = d;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}

	private Node root;
	private Comparator<E> comp;

	public BSTree(Comparator<E> theComp)
	{
		root = null;
		comp = theComp;
	}

	public Node getRoot()
	{
		return root;
	}

	/**
	 * The item is added to the tree.
	 * @param item item that is added to the tree.
	 */
	public void addLoop(E item)
	{	
		Node addNode = new Node(item);
		if(this.isEmpty()) {
			root = addNode;
			root.parent = null;
		}
		else {
			E currData = null;
			Node curr = root;
			while(curr != null && curr.data != item) { //no duplicates
				currData = curr.data;
				if(comp.compare(currData, item) > 0) {
					if(curr.left == null) {
						curr.left = addNode;
						addNode.parent = curr;	
					}
					else {
						curr = curr.left;
					}
				}
				if(comp.compare(currData, item) < 0) {
					if(curr.right == null) {
						curr.right = addNode;
						addNode.parent = curr;
					}
					else {
						curr = curr.right;
					}
				}
			}	
		}	
	}

	/**
	 * Checks if the tree is empty.
	 * @return If the tree is empty.
	 */
	public boolean isEmpty()
	{
		if(root == null) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * The largest value in the tree is returned.
	 * @return The largest value in the tree.
	 */
	public E maxValueLoop()
	{
		return findMaxNodeLoop(root).data;
	}

	/**
	 * The node with the highest value in the sub-tree rooted (starting) at the current node is returned.
	 * @param curr Node to start from.
	 * @return The node with the highest value in the sub-tree rooted (starting) at the current node.
	 */
	public Node findMaxNodeLoop(Node curr)
	{
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		while(curr.right!= null) {
			curr = curr.right;
		}
		return curr;
	}

	/**
	 * The smallest value in the tree is returned.
	 * @return The smallest value in the tree.
	 */
	public E minValueLoop()
	{
		return findMinNodeLoop(root).data;
	}

	/**
	 * The node with the smallest value in the sub-tree rooted (starting) at the current node is returned.
	 * @param curr Node to start from.
	 * @return The node with the smallest value in the sub-tree rooted (starting) at the current node.
	 */
	public Node findMinNodeLoop(Node curr)
	{
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		while(curr.left!= null) {
			curr = curr.left;
		}
		return curr;
	}

	/**
	 * Checks if the given item is found in the tree.
	 * @param item Item to search for in the tree.
	 * @return If the given item is found in the tree.
	 */
	public boolean containsLoop(E item)
	{
		if(this.isEmpty()) {
			return false;
		}
		else if(findNodeLoop(root, item) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * The Node that contains the item given is returned. Looked for in the sub-tree rooted (starting) at the current node.
	 * @param curr Node to start from.
	 * @param item Item to search for in the tree of Nodes.
	 * @return The Node that contains the item given is returned.
	 */
	public Node findNodeLoop(Node curr, E item)
	{
		while(curr != null) {
			E currData = curr.data;
			if(comp.compare(currData, item) == 0) {
				return curr;
			}
			else if(comp.compare(currData, item) > 0 ) {
				curr = curr.left;
			}
			else if(comp.compare(currData, item) < 0 ) {
				curr = curr.right;
			}
		}
		return curr;	
	}

	/**
	 * The item given is added to the tree.
	 * @param item Item to add accordingly in the tree.
	 */
	public void add(E item)
	{
		add(root, item);
	}

	/**
	 * Using Recursion, adds the given item to the sub-tree rooted (starting) at the given curr node.
	 * @param curr Node to start from.
	 * @param item Item to add accordingly to the tree.
	 */
	public void add(Node curr, E item)
	{
		Node addNode = new Node(item);
		if(this.isEmpty()) {
			root = addNode;
			root.parent = null;
		}
		else {
			E currData = null;
			if(curr != null && curr.data != item) { //no duplicates said in class
				currData = curr.data;
				if(comp.compare(currData, item) > 0) {
					if(curr.left == null) {
						curr.left = addNode;
						addNode.parent = curr;	
					}
					else {
						add(curr.left, item);
					}
				}
				if(comp.compare(currData, item) < 0) {
					if(curr.right == null) {
						curr.right = addNode;
						addNode.parent = curr;
					}
					else {
						add(curr.right, item);
					}
				}
			}	
		}
	}

	/**
	 * The largest value in the tree is returned.
	 * @return The largest value in the tree.
	 */
	public E maxValue()
	{
		return findMaxNode(root).data;
	}

	/**
	 * Using recursion, the highest value in the sub-tree rooted (starting) at the current node is returned.
	 * @param curr Node to start from.
	 * @return The highest value in the sub-tree rooted (starting) at the current node.
	 */
	public Node findMaxNode(Node curr)
	{
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		if(curr.right != null) {
			return findMaxNode(curr.right);
		}
		return curr;
	}

	/**
	 * The smallest value in the tree is returned.
	 * @return The smallest value in the tree.
	 */
	public E minValue()
	{
		return findMinNode(root).data;
	}

	/**
	 * Using recursion, the smallest value in the sub-tree rooted (starting) at the current node is returned.
	 * @param curr Node to start from.
	 * @return The smallest value in the sub-tree rooted (starting) at the current node.
	 */
	public Node findMinNode(Node curr)
	{
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		if(curr.left != null) {
			return findMinNode(curr.left);
		}
		return curr;
	}

	/**
	 * Checks if the given item is found within the tree.
	 * @param item Item to search for in the tree.
	 * @return If the given item is found within the tree.
	 */
	public boolean contains(E item)
	{
		if(this.isEmpty()) {
			return false;
		}
		else if(findNode(root, item) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Using recursion, the node that contains the item given is returned. Looked for in the sub-tree rooted (starting) at the current node.
	 * @param curr Node to start from.
	 * @param item Item to search for stored in a Node found in the tree.
	 * @return The node that contains the item given is returned.
	 */
	public Node findNode(Node curr, E item)
	{
		if(curr != null) {
			E currData = curr.data;
			if(comp.compare(currData, item) == 0) {
				return curr;
			}
			else if(comp.compare(currData, item) > 0 ) {
				return findNode(curr.left, item);
			}
			else if(comp.compare(currData, item) < 0 ) {
				return findNode(curr.right, item);
			}
		}
		return curr;	
	}

	/**
	 * The given item is removed from the tree.
	 * @param item Item to search and remove from the tree.
	 * @return If the given item is removed from the tree.
	 */
	public boolean remove(E item)
	{
		Node curr = findNode(root, item);	
		if(curr != null) {
			if(curr.left != null && curr.right != null) {
				removeHasBoth(curr);
			}
			else if(curr.left == null || curr.right == null) {
				removeMissing(curr);
			}	
			return true;
		}	
		return false;
	}

	/**
	 * Removes the given node even if it is missing one or both children.
	 * @param node Node to remove from the tree.
	 */
	public void removeMissing(Node node)
	{
		if(node == root) {
			if(node.left == null && node.right == null) { //missing both 
				root = null;
			}
			else if(node.left == null) { //missing left
				root = node.right;
				node.right.parent = null;
				node = null;
			}			
			else if(node.right == null) { //missing right
				root = node.left;
				node.left.parent = null;
				node = null;
			}
		}
		else if(node.parent.left == node) { // left side of the tree PARENT
			if(node.left == null && node.right == null) { //missing both 
				node.parent.left = null;
				node = null;
			}
			else if(node.left == null) { //missing left
				node.parent.left = node.right;
				node.right.parent = node.parent;
				node = null;
			}			
			else if(node.right == null) { //missing right
				node.parent.left = node.left;
				node.left.parent = node.parent;
				node = null;
			}
		}
		else if(node.parent.right == node) { // right side of the tree PARENT
			if(node.left == null && node.right == null) { //missing both 
				node.parent.right = null;
				node = null;
			}
			else if(node.left == null) {	//missing left
				node.parent.right = node.right;
				node.right.parent = node.parent;
				node = null;
			}			
			else if(node.right == null) {	//missing right
				node.parent.right = node.left;
				node.left.parent = node.parent;
				node = null;
			}
		}
	}

	/**
	 * Removes the node while assuming it has two children (exact).
	 * @param node Node to remove from the tree.
	 */
	public void removeHasBoth(Node node)
	{
		E store = null;
		store = findMaxNode(node.left).data;
		node.data = store;
		removeMissing(findMaxNode(node.left));
	}
	//hw6 starts here
	//no need to submit api...
	/**
	 * Returns a string representation of the tree listing the elements in level-order sequence in the format [a b c d].
	 */
	public String toString()
	{
		Iterator<E> iter = this.iterator();
		String str = "";

		while(iter.hasNext() && !isEmpty()) {
			E store = iter.next(); 
			str += store + " ";
		}
		str = "[" + str.trim() + "]";
		return str;
	}

	/**
	 * Returns a string representation of the tree listing the elements in preorder sequence in the format [a b c d].
	 * @return a string representation of the tree listing the elements in preorder sequence in the format [a b c d].
	 */
	public String toStringPre()
	{
		Iterator<E> iter = this.preorderIterator();
		String str = "";
		while(iter.hasNext() && !isEmpty()) {
			E store = iter.next(); 
			str += store + " ";
		}
		str = "[" + str.trim() + "]";
		return str;
	}

	/**
	 * Returns an iterator over this tree that enumerates the elements in level-order sequence.
	 */
	public Iterator<E> iterator()
	{
		return new iterator();	
	}

	private class iterator implements Iterator<E>
	{
		//level order 
		Queue<Node> q = new LinkedList<Node>();
		public iterator()
		{
			q.add(root);
		}

		@Override
		public boolean hasNext()
		{
			if(!q.isEmpty()) {
				return true;
			}
			return false;
		}

		@Override
		public E next()
		{
			if(hasNext()) {
				Node store = q.poll();
				if(store.left != null) {
					q.add(store.left);
				}
				if(store.right != null) {
					q.add(store.right);
				}
				return store.data;
			}
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns an iterator over this tree that enumerates the elements in preorder sequence (the remove() method is not supported).
	 * @return iterator over this tree that enumerates the elements in preorder sequence (the remove() method is not supported).
	 */
	public Iterator<E> preorderIterator()
	{
		return new preorderIterator();
	}

	private class preorderIterator implements Iterator<E>
	{
		Stack<Node> stack = new Stack<Node>();

		public preorderIterator()
		{
			stack.push(root);
		}
		@Override
		public boolean hasNext()
		{
			if(!stack.isEmpty()) {
				return true;
			}
			return false;
		}

		@Override
		public E next()
		{
			if(hasNext()) {
				Node store = stack.pop();
				if(store.right != null) {
					stack.push(store.right);
				}
				if(store.left != null) {
					stack.push(store.left);
				}
				return store.data;
			}
			throw new NoSuchElementException();
		}
	}

	/**
	 * Performs preorder traversal of this tree.
	 * @param visitor accumulates the string value.
	 */
	public void preorder(Visitor<E> visitor)
	{
		preorder(visitor, root);
	}
	/**
	 * Performs pre-order traversal of the tree rooted at the given node curr.
	 * @param visitor accumulates the string value.
	 * @param curr traversal starts on this node.
	 */
	public void preorder(Visitor<E> visitor, Node curr)
	{
		if(curr == null) {
			return;
		}
		visitor.visit(curr.data);
		preorder(visitor, curr.left);
		preorder(visitor, curr.right);
	}

	/**
	 * Performs in-order traversal of this tree.
	 * @param visitor  accumulates the string value.
	 */
	public void inorder(Visitor<E> visitor)
	{
		inorder(visitor, root);
	}

	/**
	 * Performs in-order traversal of the tree rooted at the given node curr.
	 * @param visitor accumulates the string value.
	 * @param curr traversal starts on this node.
	 */
	public void inorder(Visitor<E> visitor, Node curr)
	{
		if(curr == null) {
			return;
		}
		inorder(visitor, curr.left);
		visitor.visit(curr.data);
		inorder(visitor, curr.right);
	}

	/**
	 * Performs post-order traversal of this tree.
	 * @param visitor accumulates the string value.
	 */
	public void postorder(Visitor<E> visitor)
	{
		postorder(visitor, root);
	}

	/**
	 * Performs post-order traversal of the tree rooted at the given node curr. 
	 * @param visitor accumulates the string value.
	 * @param curr traversal starts on this node.
	 */
	public void postorder(Visitor<E> visitor, Node curr)
	{
		if(curr == null) {
			return;
		}
		postorder(visitor, curr.left);
		postorder(visitor, curr.right);
		visitor.visit(curr.data);
	}

	/**
	 * Returns true if the given object is equal to this tree.
	 */
	public boolean equals(Object obj)
	{
		if(this == obj) {                     // 1. is the other one me?
			return true;
		}
		else if( !(obj instanceof BSTree) ) {    // 2. is the other one a different kind?
			return false;
		}
		else {                                   // 3. the other one is not me, but is a Cat
			BSTree<E> otherBSTree = (BSTree<E>) obj;
			if(equals (this.root, otherBSTree.root)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the trees rooted at the given nodes are equal i.e. the data items at the roots are the same and their left and right subtrees are also equal.
	 * @param root1 tree rooted on this node.
	 * @param root2 tree rooted on this node.
	 * @return true if the trees rooted at the given nodes are equal.
	 */
	public boolean equals(Node root1, Node root2)
	{
		if(root1 == null && root2 == null) {
			return true;
		}
		else if(root1 == null || root2 == null) { 
			return false;
		}
		else if(root1.data != null && root2.data != null) { //otherCat
			if(root1.data == root2.data && equals(root1.left , root2.left) && equals(root1.right , root2.right)) {		
				return true;
			}
		}
		return false;
	}	

	/**
	 * Returns a copy of this tree.
	 */
	public Object clone()
	{
		try {
			BSTree<E> copy = (BSTree<E>) super.clone();
			copy.comp = this.comp;
			copy.root = copyTree(this.root);
			return copy;
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns a copy of the tree rooted at the given node.
	 * @param curr tree rooted on this node.
	 * @return copy of the tree rooted at the given node.
	 */
	public Node copyTree(Node curr)
	{
		if(curr == null) {
			return null;
		}
		Node currLeft = copyTree(curr.left);
		Node currRight = copyTree(curr.right);
		Node newRoot = new Node(curr.data);
		newRoot.left = currLeft;
		newRoot.left = currRight;
		return newRoot;
	}

	/**
	 * Creates the tree from the given preorder array of items.
	 * @param items 
	 * @param comp
	 */
	public BSTree(E[] items, Comparator<E> comp)
	{
		this.comp = comp;
		this.root = rebuildPreorder(items, 0, items.length);
	}

	public Node rebuildPreorder(E[] items, int i, int j)
	{
//		if( i > j || i > items.length ) {
//			return null;
//		}
		if( i == j) {
			return null;
		}
		int m = 0; 
		for(int x = i; x < j; x++) {
			if(comp.compare(items[x], items[i]) > 0) {
				m = x;
				break;
			}
		}
		Node currLeft = rebuildPreorder(items, i +1 , m); //right for post  m+1 j-1
		Node currRight = rebuildPreorder(items, m, j);  //post left and i to m
		Node newRoot = new Node(items[i]);
		newRoot.left = currLeft;
		newRoot.left = currRight;
		return newRoot;
	}
}
