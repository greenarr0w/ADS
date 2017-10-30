package ch.zhaw.ads.praktikum5;

public class TreeNode<T extends Comparable<T>> {
	public T element;
	public TreeNode<T> left, right;
	public int height;
	
	public TreeNode(T element){
		this.element = element;
	}
	public TreeNode(T element, TreeNode<T> left, TreeNode<T> right){
		this(element); this.left = left; this.right = right;
	}
	
	public T getValue(){return element;}
	public TreeNode getLeft(){return left;}
	public TreeNode getRight(){return right;}
}