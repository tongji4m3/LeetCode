> 题目出自LeetCode
>
> [32. 最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/)
>
>
> 其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:
```
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
```
示例 2:
```
输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
```

# 思路
## Stack

用Stack存储左括号的位置,并且栈底总是存储最后一个没有被匹配的右括号的下标。这样每次遇到左括号就push。

遇到右括号则先弹出Stack中的一个元素，如果此时栈未空，说明弹栈把最后一个没有被匹配的右括号的下标弹出去的,就说明该右括号是没有左括号匹配的。则将他放入栈底。如果不为空，则说明有左括号匹配，则计算result。

## left，right指针

先正向遍历，适用右括号比左括号多的情况

再反向遍历，适用右括号比左括号少的情况

# 细节

1. 用栈的话要注意初始在栈底放一个元素占位。而且记得计算时要先弹出一个元素
2. 用left，right指针要注意反向遍历时，是左括号比右括号多时才清零


# 代码
```java
//栈
//在栈底放最后一个没有被匹配的右括号的下标
public int longestValidParentheses(String s)
{
    int result = 0;
    Stack<Integer> stack = new Stack<>();
    stack.push(-1);//默认的栈底,这样计算会正确
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s.charAt(i);
        if (ch == '(')
        {
            stack.push(i);
        }
        else
        {
            stack.pop();
            //说明弹栈把最后一个没有被匹配的右括号的下标弹出去的,就说明该)也是没有(匹配的
            if (stack.isEmpty()) stack.push(i);
            else result = Math.max(result, i - stack.peek());
        }
    }
    return result;
}
```

```java
//使用left，right指针
/*
    完美匹配肯定测得出来
    如果是不完美匹配,要么是左括号过多,要么是右括号过多
    如果是右括号过多,则在过多的那个地方,都给清0,就不会影响后续的判断了
    如果是左括号过多,则应在之后反向判断,转为右括号过多,就能正常判断了
 */
public int longestValidParentheses(String s)
{
    int result = 0, left = 0, right = 0;
    //第一轮匹配的是右括号多的情况 即类似())(()))这些
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s.charAt(i);
        if (ch == '(') ++left;
        else ++right;
        if (left == right)
        {
            result = Math.max(result, 2 * left);
        }
        else if (right > left)
        {
            left = 0;
            right = 0;
        }
    }
    //反向遍历 判断左括号过多的情况
    left = 0;
    right = 0;
    for (int i = s.length() - 1; i >= 0; i--)
    {
        char ch = s.charAt(i);
        if (ch == '(') ++left;
        else ++right;
        if (left == right)
        {
            result = Math.max(result, 2 * left);
        }
        else if (right < left) //这里要改变
        {
            left = 0;
            right = 0;
        }
    }
    return result;
}
```



# 复杂度分析

## 时间复杂度

使用栈:$O(N)$
		使用left，right指针：$O(N)$

## 空间复杂度
使用栈:$O(N)$
		使用left，right指针：$O(1)$