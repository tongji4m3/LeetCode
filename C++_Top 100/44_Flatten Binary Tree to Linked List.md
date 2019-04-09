```
给定二叉树，将其平铺到链接列表中。

例如，给定以下树：

    1
   / \
  2   5
 / \   \
3   4   6
扁平的树应该看起来像：

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

```c++
class Solution 
{
public:
	void flatten(TreeNode* root) 
	{
		if (!root) return;
		TreeNode* newRoot = new TreeNode(root->val);
		TreeNode* current=newRoot;
		stack<TreeNode*> s;
		s.push(root);
		while (!s.empty())
		{
			TreeNode* node = s.top(); s.pop();
			current->right = node;
			current->left = nullptr;
			current = current->right;
			if (node->right) s.push(node->right);
			if (node->left)  s.push(node->left);
		}
		root = newRoot->right;
	}
};
```
