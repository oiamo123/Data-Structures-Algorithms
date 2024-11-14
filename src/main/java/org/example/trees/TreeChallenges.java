package org.example.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeChallenges {
    // Check to see if two tree's match. Can use breadth-first search
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true; // Both trees are empty
        if (p == null || q == null) return false; // One tree is empty, the other is not

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.add(p);
        queue2.add(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode p1 = queue1.poll();
            TreeNode q1 = queue2.poll();

            // Null checks for values
            if (p1 == null && q1 == null) continue; // Both nodes are null, continue to next nodes
            if (p1 == null || q1 == null || p1.val != q1.val) {
                return false; // One is null or values are different
            }

            // If left children are not null, add them to the queues for the next iteration
            if (p1.left != null && q1.left != null) {
                queue1.add(p1.left);
                queue2.add(q1.left);
            } else if (p1.left != null || q1.left != null) {
                return false; // One tree has left child, the other does not
            }

            // If right children are not null, add them to the queues for the next iteration
            if (p1.right != null && q1.right != null) {
                queue1.add(p1.right);
                queue2.add(q1.right);
            } else if (p1.right != null || q1.right != null) {
                return false; // One tree has right child, the other does not
            }
        }

        // If both queues are empty, the trees are identical
        return queue1.isEmpty() && queue2.isEmpty();
    }

    // Validate if a tree is a binary search tree ie values to left are smaller and values to right are larger
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;  // Null nodes are always valid

        // The current node's value must be within the valid range
        if (root.val <= min || root.val >= max) return false;

        // Check the left subtree with updated max bound (current node's value)
        // Check the right subtree with updated min bound (current node's value)
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
