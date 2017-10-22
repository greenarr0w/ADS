package ch.zhaw.ads.praktikum5;

import java.util.PriorityQueue;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    private void inorder(TreeNode<T> node, Visitor<T> visitor) {
        if(node != null) {
            inorder(node.left, visitor);
            visitor.visit(node.element);
            inorder(node.right, visitor);
        }
    }

    public void inorder(Visitor<T> visitor) {
        // to be done
        inorder(root, visitor);
    }

    private void preorder(TreeNode<T> node, Visitor<T> visitor) {
        if(node != null) {
            visitor.visit(node.element);
            preorder(node.left, visitor);
            preorder(node.right, visitor);
        }
    }

    public void preorder(Visitor<T> visitor) {
        // to be done
        preorder(root, visitor);
    }

    private void postorder(TreeNode<T> node, Visitor<T> visitor) {
        if(node != null) {
            postorder(node.left, visitor);
            postorder(node.right, visitor);
            visitor.visit(node.element);
        }
    }

    public void postorder(Visitor<T> visitor) {
        // to be done
        postorder(root, visitor);
    }

    private void levelOrder(TreeNode<T> node, Visitor<T> visitor) {
        Queue<TreeNode<T>> queue = new PriorityQueue<>();
        if (node != null) queue.add(node);
        while (!queue.isEmpty()){
            node = queue.remove();
            visitor.visit(node.element);
            if (node.left !=null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    public void levelOrder(Visitor<T> visitor) {
        levelOrder(root, visitor);
    }

}