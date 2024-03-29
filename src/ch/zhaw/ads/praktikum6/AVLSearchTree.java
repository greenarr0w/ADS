/**
 * Implements an AVL tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 * Generic K.Rege
 */
package ch.zhaw.ads.praktikum6;


import ch.zhaw.ads.praktikum5.Traversal;
import ch.zhaw.ads.praktikum5.Tree;
import ch.zhaw.ads.praktikum5.TreeNode;
import ch.zhaw.ads.praktikum5.TreeTraversal;

public class AVLSearchTree<T extends Comparable<T>> implements Tree<T> {

	/** The tree root. */
	private TreeNode root;
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	public int size() {
		return size(root);
	}
	
	private static int size(TreeNode p) {
		if (p == null) {
			return 0;
		} else {
			return 1 + size(p.left) + size(p.right);
		}
	}
	
	public int height() {
		return height(root);
	}
	
	/**
	 * Return the height of node t, or -1, if null.
	 */
	private static int height(TreeNode t) {
		return t == null ? 0 : t.height;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * @param element the item to insert.
	 */
	public void add(T element) {
		root = insertAt(root, element);
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param p the item to insert.
	 * @param element the node that roots the tree.
	 * @return the new root.
	 */
	private TreeNode insertAt(TreeNode p, T element) {
		if (p == null) {
			p = new TreeNode<T>(element);
		} else {
			int c = element.compareTo((T) p.element);
			if (c <= 0) {
				p.left = insertAt(p.left, element);
				if (height(p.left) - height(p.right) == 2) {
					if (height(p.left.left) > height(p.left.right)) {
                        p = rotateR(p);
					} else {
						p = rotateLR(p);
					}
				}
			} else if (c > 0) {
				p.right = insertAt(p.right, element);
				if (height(p.right) - height(p.left) == 2) {
					if (height(p.right.right) > height(p.right.left)) {
                        p = rotateL(p);
					} else {
						p = rotateRL(p);
					}
				}
			}
		}
		p.height = Math.max(height(p.left), height(p.right)) + 1;
		return p;
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * @param element the item to remove.
	 */
	public T remove(T element) {
		throw new UnsupportedOperationException();
	}
	
	public T removeLast() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Rotate binary tree node with left child.
	 * For AVL trees, this is a single rotation for case 1.
	 * Update heights, then return new root.
	 */
	private static TreeNode rotateR(TreeNode k2) {
		TreeNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 * For AVL trees, this is a single rotation for case 4.
	 * Update heights, then return new root.
	 */
	private static TreeNode rotateL(TreeNode k1) {
		TreeNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child
	 * with its right child; then node k3 with new left child.
	 * For AVL trees, this is a double rotation for case 2.
	 * Update heights, then return new root.
	 */
	private static TreeNode rotateLR(TreeNode k3) {
		k3.left = rotateL(k3.left);
		return rotateR(k3);
	}

	/**
	 * Double rotate binary tree node: first right child
	 * with its left child; then node k1 with new right child.
	 * For AVL trees, this is a double rotation for case 3.
	 * Update heights, then return new root.
	 */
	private static TreeNode rotateRL(TreeNode k1) {
		k1.right = rotateR(k1.right);
		return rotateL(k1);
	}
	
	public Traversal<T> traversal() {
		return new TreeTraversal<T>(this.root);
	}

    public int calcHeight() {
        return calcHeight(this.root);
    }

    public int calcHeight(TreeNode<T> node) {
        if (node == null) return 0;
        return Math.max(calcHeight(node.getLeft()), calcHeight(node.getRight())) + 1;
    }

    public boolean checkAVL() {
		return checkAVL(this.root);
	}

	// not very efficient, but it works
	private boolean checkAVL(TreeNode<T> node) {
		if (node == null) return true;
		boolean isAVL = Math.abs(calcHeight(node.getLeft()) - calcHeight(node.right)) <= 2;
		return isAVL && checkAVL(node.getLeft()) && checkAVL(node.getRight());
	}
}
