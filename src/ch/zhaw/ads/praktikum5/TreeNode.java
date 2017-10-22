package ch.zhaw.ads.praktikum5;

class TreeNode<T extends Comparable<T>> {
	T element;
	TreeNode<T> left, right;
	int height;
	
	TreeNode(T element){
		this.element = element;
	}
	TreeNode(T element, TreeNode<T> left, TreeNode<T> right){
		this(element); this.left = left; this.right = right;
	}
	
	T getValue(){return element;}
	TreeNode getLeft(){return left;}
	TreeNode getRight(){return right;}
}