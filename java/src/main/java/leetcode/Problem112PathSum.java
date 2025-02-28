package leetcode;

import leetcode.api.TreeNode;

/**
 * https://leetcode.com/problems/path-sum
 */
public final class Problem112PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else {
            int diff = sum - root.val;
            boolean answer = false;

            if (diff == 0 && root.left == null && root.right == null) {
                return true;
            }

            if (root.left != null) {
                answer = answer || hasPathSum(root.left, diff);
            }

            if (root.right != null) {
                answer = answer || hasPathSum(root.right, diff);
            }

            return answer;
        }
    }
}
