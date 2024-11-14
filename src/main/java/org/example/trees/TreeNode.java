package org.example.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public TreeNode left, right;
    public int val;

    public TreeNode(int value) {
        this.val = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.val = value;
        this.left = left;
        this.right = right;
    }

    public void preOrder() {
        System.out.print(val + "");
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this.val + "");
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this.val + "");
    }

    public void breadthFirst() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + "");

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void insert(int val) {
        if (val > this.val) {
            if (this.left == null) {
                this.left = new TreeNode(val);
            } else {
                this.left.insert(val);
            }
        } else {
            if (this.right == null) {
                this.right = new TreeNode(val);
            } else {
                this.right.insert(val);
            }
        }
    }
}
