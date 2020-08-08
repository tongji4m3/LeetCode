> 题目出自LeetCode
>
>  [11. 盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

 

**示例：**

```
输入：[1,8,6,2,5,4,8,3,7]
输出：49
```

# 思路

面积=底*高.显然首尾元素的底最大。所以可以从首尾不断的缩小，找出最大值.

缩小可以让高度较小的那条边缩小，就不会漏掉解

```java
lo=0,hi=n-1;
result=0;
while(lo<hi)
{
    result=max(result,(hi-lo)*min(nums[lo],nums[hi]));
    if(nums[lo]>nums[hi]) --hi;
    else ++lo;
}
```



# 细节

可以进行优化,即选择了小的那个支柱后，往内侧寻找，可以采用while循环的方式。如果都是小于等于支柱的高，就一直循环（注意边界），节省开销。


# 代码

```java
public int maxArea(int[] height)
{
    int n = height.length;
    int lo = 0, hi = n - 1;
    int result = 0;
    while (lo < hi)//lo==hi的话，只有一个，是没有面积的
    {
        result = Math.max(result, (hi - lo) * Math.min(height[lo], height[hi]));
        if (height[lo] > height[hi]) //右边是短板
        {
            //因为底不断变小,所以高小于支柱的可以都淘汰
            int temp = height[hi];
            //必须先强制-1,不然可能死循环 高相等的总面积也小
            //不会越界,最多减小到lo
            while (--hi>lo && height[hi]<=temp);
        }
        else
        {
            int temp = height[lo];
            while(++lo<hi && height[lo]<=temp);
        }

    }
    return result;
}
```



# 复杂度分析
## 时间复杂度

$ O（N) $ 

## 空间复杂度

$ O(1) $