> 题目出自LeetCode
>
> 
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

**示例:**

```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```

# 思路

找左边最高位置:从左往右遍历

找右边最高位置:从右往左遍历

找到每个位置的左边最高和右边最高高度,取他们较小的那个-那个位置的高度.即为该位置接的雨水

把每个位置接的雨水累计即可

# 


# 代码

```java
public int trap(int[] height)
{
    int n = height.length;
    if (n <= 2) return 0;//接不了雨水
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = height[0];
    for (int i = 1; i < n; i++) left[i] = Math.max(left[i - 1], height[i]);

    right[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) right[i] = Math.max(right[i + 1], height[i]);

    int result = 0;

    for (int i = 0; i < n; i++) result += (Math.min(left[i], right[i]) - height[i]);
    return result;
}
```



# 复杂度分析
## 时间复杂度

$O(N)$,三次循环,每次需要N,总共3N

## 空间复杂度

$O(N)$,定义两个大小为N的数组,总共需要空间为2N