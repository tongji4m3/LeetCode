package hot;

import utils.TreeNode;

public class Demo101
{
    public boolean isSymmetric(TreeNode root)
    {
        return recursive(root, root);
    }

    private boolean recursive(TreeNode left,TreeNode right)
    {
        if(left==null && right==null)
        {
            return true;
        }
        else if(left==null || right==null)
        {
            return false;
        }
        else
        {
            return left.val == right.val && recursive(left.left, right.right) && recursive(left.right, right.left);
        }
    }
}
