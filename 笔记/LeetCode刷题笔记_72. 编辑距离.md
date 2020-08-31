> 题目出自LeetCode
>
> [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符


示例 1：
```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```
示例 2：
```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```


# 思路
```
dp[i][j]代表将word1的前i-1个字符与word2的前j-1个字符转换的最小操作
返回dp[m][n]
初始化: dp[0][0]=0 dp[i][0]=i dp[0][j]=j
递推方程:
上边代表增加,左边代表删除,左上代表替换,因为可能不用替换
dp[i][j]=min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+(word1[i-1]==word2[j-1] ? 0:1))
```
# 细节

1. 注意不匹配时要加一的操作
2. 如果节约空间,则可以用一维数组表示,可以从二维上推导,要注意的是推导过程中列的初始化操作的改变,以及用一个变量存储左上角元素时的细节


# 代码

```java
public int minDistance(String word1, String word2)
{
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i < m + 1; i++) dp[i][0] = i;
    for (int j = 0; j < n + 1; j++) dp[0][j] = j;

    for (int i = 1; i < m + 1; i++)
    {
        for (int j = 1; j < n + 1; j++)
        {
            //look,需要+1,并且注意+1的时机
            dp[i][j] = Math.min(dp[i - 1][j] + 1,
                                Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1)));
        }
    }
    return dp[m][n];
}
```

```java
//节约空间
public int minDistance(String word1, String word2)
{
    int m = word1.length(), n = word2.length();

    int[] dp = new int[n + 1];
    for (int j = 0; j < n + 1; j++) dp[j] = j;

    for (int i = 1; i < m + 1; i++)
    {
        int leftTop = dp[0];//look 记录左上角的值 注意和下面一条的顺序
        dp[0] = i;//look 不要忽略这里的初始化
        for (int j = 1; j < n + 1; j++)
        {
            int temp = dp[j];
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[j] = leftTop;
            else dp[j] = Math.min(dp[j], Math.min(dp[j - 1], leftTop)) + 1;
            leftTop = temp;
        }
    }
    return dp[n];
}
```

# 复杂度分析
## 时间复杂度

$O(N^2)$

## 空间复杂度

$O(N^2)$ 空间优化:$O(N)$