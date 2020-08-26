> 题目出自LeetCode
>
> [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:
```
输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
```
示例 2:
```
输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
```

# 思路

```java
//伪代码
int i=0;//所处位置
int jump=0;//能跳到的最远位置
while(i<=jump)
{
    jump=max(jump,i+nums[i]);//i+nums[i]为通过i位置能跳到的最远处
    if(jump>=nums.length-1) return true;
    ++i;//不断往前走一格
}
return false;
```






# 代码

```java
public boolean canJump(int[] nums)
{
    int i = 0;//所处位置
    int jump = 0;//能跳到的最远位置
    while (i <= jump)
    {
        jump = Math.max(jump, i + nums[i]);//i+nums[i]为通过i位置能跳到的最远处
        if (jump >= nums.length - 1) return true;
        ++i;//不断往前走一格
    }
    return false;
}
```



# 复杂度分析
## 时间复杂度

$O(N)$,只需要遍历一次数组

## 空间复杂度

$O(1)$,只有两个索引