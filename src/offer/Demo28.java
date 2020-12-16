package offer;

import utils.TreeNode;

public class Demo28 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.val != node2.val) return false;
        return dfs(node1.left, node2.right) && dfs(node1.right, node2.left);
    }
}
