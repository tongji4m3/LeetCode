> 题目出自LeetCode
>
> [62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

示例 1:
```
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右

```
示例 2:
```
输入: m = 7, n = 3
输出: 28
```

提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 10 ^ 9


# 思路
标准动态规划
```
dp[i][j]代表有多少种路径到该点
最后求dp[m-1][n-1]
p[i][0]=1; dp[j][0]=1
dp[i][j]=dp[i-1][j]+dp[i][j-1]
```
# 细节

1. m,n代表的长宽与常识上不太相同,但是不影响
2. 先写成标准的动态规划,再简化空间开销就比较容易


# 代码

```java
//标准动态规划
public int uniquePaths(int m, int n)
{
    int dp[][] = new int[m][n];
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;
    for (int i = 1; i < m; i++)
    {
        for (int j = 1; j < n; j++)
        {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    return dp[m - 1][n - 1];
}
```

```java
//节约空间 改成一维数组
public int uniquePaths(int m, int n)
{
    int dp[] = new int[n];
    for (int i = 0; i < n; i++) dp[i] = 1;
    for (int i = 1; i < m; i++)
    {
        for (int j = 1; j < n; j++)
        {
            dp[j] = dp[j] + dp[j - 1];
        }
    }
    return dp[n - 1];
}
```



# 复杂度分析
## 时间复杂度

$O(N^2)$

## 空间复杂度
$ O(N^2) $
节约空间,则为$ O(N) $