> 题目出自LeetCode
>
>  [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 

示例：
```
输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]
```

# 思路

回溯法.可以画出解空间树，加剪枝帮助理解。

```java
recursive(n,n)//左括号个数，右括号个数开始都为n
    
recursive(leftCount,rightCount)
{
    if(rightCount<leftCount) return; //限定条件 说明)比(多
    if(rightCount==0 && leftCount==0) result.add(stringBuilder);
    
    if(leftCount>0)//限定条件 说明(用完了
    {
        stringBuilder.append('(');
        recursive(leftCount-1,rightCount);//往左走，即往结果加个（
        移除stringBuilder刚加入的元素，即返回到父节点
    }
    if(rightCount>0) //限定条件 说明)用完了
    {
        stringBuilder.append(')');
        recursive(leftCount,rightCount-1);//往右走，即往结果加个）
        移除stringBuilder刚加入的元素，即返回到父节点
    }
}
```



# 细节

1. 注意n=0的返回值
2. 注意递归返回时，要把stringBuilder刚加入的元素清除。画解空间树就一目了然


# 代码

```java
public class Demo22
{
    private List<String> result = new LinkedList<>();
    private StringBuilder stringBuilder = new StringBuilder();

    public List<String> generateParenthesis(int n)
    {
        if(n==0) return result; //n==0,返回的是空集合,不是空字符串
        dfs(n, n);//左括号个数，右括号个数开始都为n
        return result;
    }

    public void dfs(int leftCount, int rightCount)
    {
        if(rightCount<leftCount) return; //限定条件 说明)比(多
        if(rightCount==0 && leftCount==0) result.add(stringBuilder.toString());

        if(leftCount>0)//限定条件 说明(用完了
        {
            stringBuilder.append('(');
            dfs(leftCount-1,rightCount);//往左走，即往结果加个（
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);//回到上一层,父节点
        }
        if(rightCount>0) //限定条件 说明)用完了
        {
            stringBuilder.append(')');
            dfs(leftCount,rightCount-1);//往右走，即往结果加个）
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);//回到上一层,父节点
        }
    }
}
```



# 复杂度分析
## 时间复杂度

$ O(4^n/\sqrt n) $

## 空间复杂度

空间复杂度：O(n)，除了result之外，所需空间取决于递归栈的深度，每一层递归函数需要 O(1)的空间，最多递归 2n 层，因此空间复杂度为 O(n)。

