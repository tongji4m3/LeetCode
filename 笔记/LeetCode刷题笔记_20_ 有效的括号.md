> 题目出自LeetCode
>
> [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
		左括号必须以正确的顺序闭合。
		注意空字符串可被认为是有效字符串。

示例 1:
```
输入: "()"
输出: true
```
示例 2:
```
输入: "()[]{}"
输出: true
```
示例 3:
```
输入: "(]"
输出: false
```
示例 4:
```
输入: "([)]"
输出: false
```
示例 5:
```
输入: "{[]}"
输出: true
```



# 思路

用stack存储左括号,遇到括号则从栈顶取出,如果不匹配,则返回false.一直到最后,如果栈不为空,也返回false

```
//伪代码
for(ch in string)
{
	if(ch=='(' || ch=='[' || ch=='{') 
	{
		stack.push(ch);
	}
	else
	{
		if(stack.isEmpty() || stack.pop()!=ch) return false;
	}
	if(!stack.isEmpty()) return false;
}
```



# 细节

1. 注意匹配条件,不是相等
2. 注意最后还要判断`stack.isEmpty()`


# 代码

```java
public boolean isValid(String s)
{
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s.charAt(i);
        if (ch == '(' || ch == '[' || ch == '{')
        {
            stack.push(ch);
        }
        else
        {
            //look 是匹配,不是相等
            //if(stack.isEmpty() || stack.pop()!=ch) return false;
            if (stack.isEmpty()) return false;

            char temp = stack.pop();
            switch (ch)
            {
                case ')':
                    if (temp != '(') return false;
                    break;
                case ']':
                    if (temp != '[') return false;
                    break;
                case '}':
                    if (temp != '{') return false;
                    break;
            }

        }
    }
    return stack.isEmpty();
}
```



# 复杂度分析
## 时间复杂度

$ O(N) $,遍历字符串,并且每次遍历时只进行$ O(1) $的常数操作

## 空间复杂度

$ O(N) $,最坏情况下要把所有字符串都入栈,例如`((((((((`