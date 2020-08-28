> 题目出自LeetCode
>
> [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：
```
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```
示例 2：
```
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```

# 思路

其实就是斐波那契数列




# 代码

```java
public int climbStairs(int n)
{
    if(n==1 || n==2) return n;//正整数,不用考虑这么多

    int first = 1, second = 2;//初始化为爬1楼,2楼的方法数量
    for (int i = 3; i <= n; i++)//i代表楼梯数
    {
        int temp = first + second;
        first = second;
        second = temp;
    }
    return second;
}
```



# 复杂度分析
## 时间复杂度

$O(N)$

## 空间复杂度

$O(1)$