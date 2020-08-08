> 题目出自LeetCode
>
> [10. 正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```
示例 2:
```
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```
示例 3:
```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```
示例 4:
```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```
示例 5:
```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

# 思路

动态规划:

定义`dp[i][j]`为s的`[0,i-1]`个字符与p的前`[0,j-1]`个字符是否匹配.

结束:`dp[m][n]`

初始化:

```java
//s为空时,p匹配s的情况:
dp[0][0]=true;
//如果这个位置为*,则还可能为true,即让前个位置字符匹配0次,查看是否相等
//注意越界检查等等
for(j in [1,n])
	if(p[j-1]=='*') dp[0][j]=dp[0][j-2]
	else dp[0][j]=false;

//p为空时,p匹配s的情况:
//无特殊情况
for(i in [1,n])
	dp[0][i]=false;

```
递推公式:
```java
//匹配的情况最好
if(s[i-1]==p[j-1] || p[j-1]=='.') 
{
	dp[i][j]=dp[i-1][j-1];
}
else if(p[j-1]=='*')//是特殊字符*
{
	//分三种情况讨论,分别是匹配0次,1次,n次
	//只要其中一种匹配成功即可,即为||
	//匹配n次比较复杂 要求s[0,i-2]的都匹配了(即dp[i-1][j]匹配),再看s[i-1]是否能匹配上p中要复制的字符,即p.charAt(j - 2)和它相等或者为.的情况(.*)
	dp[i][j]=dp[i][j-2] || dp[i][j-1] || 
	(dp[i-1][j] && (p[j-2]=='.' || p[j-2]==s[i-1]));
}

```



# 细节
1. 要注意很多的数组边界情况
2. s.length()的情况不用考虑
3. `*`匹配多个的逻辑要注意,并且要考虑 `.*`的情况


# 代码
```java
public boolean isMatch(String s, String p)
{
    //look如果判断s.length()==0,会很难处理 因为""与".*"是匹配的
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];

    //初始化 不需要判断s,p的长度是否0,因为dp[0][0]肯定存在
    dp[0][0] = true;
    for (int i = 1; i <= m; i++) dp[i][0] = false;
    //默认*不能出现在最前面
    for (int j = 1; j <= n; j++)
    {
        if (p.charAt(j - 1) == '*' && j >= 2) dp[0][j] = dp[0][j - 2];
        else dp[0][j] = false;
    }

    for (int i = 1; i <= m; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            char charS = s.charAt(i - 1);
            char charP = p.charAt(j - 1);

            if (charS == charP || charP == '.')
            {
                dp[i][j] = dp[i - 1][j - 1];
            }
            else if (charP == '*' && j >= 2)
            {
                //look,匹配多个判断,首先要s[0,i-2]的都匹配了(即dp[i-1][j]匹配),再看s[i-1]是否能匹配上p中要复制的字符
                //即p.charAt(j - 2)和它相等或者为.的情况(.*)
                boolean matchMore = (dp[i - 1][j] && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == charS));
                dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || matchMore;
            }
        }
    }

    return dp[m][n];
}
```

# 复杂度分析

## 时间复杂度

二重循环,$ O(MN) $

## 空间复杂度

dp数组,$ O(MN) $