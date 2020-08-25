> 题目出自LeetCode
>
> [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:
```
输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

# 思路

从左往右遍历,如果大于0,则保留,否则不要。result则每个时刻都要判断

# 细节

因为子数组最少包含一个元素，所以初始化不能为0，而应该是int的最小值


# 代码

```java
//子数组最少包含一个元素
public int maxSubArray(int[] nums)
{
    int N = nums.length;
    int result = Integer.MIN_VALUE,temp=0;//look,因为条件是至少包含
    for (int i = 0; i < N; i++)
    {
        temp = temp > 0 ? temp + nums[i] : nums[i];
        result = Math.max(result, temp);
    }
    return result;
}
```



# 复杂度分析
## 时间复杂度

$O(N)$

## 空间复杂度

$O(1)$