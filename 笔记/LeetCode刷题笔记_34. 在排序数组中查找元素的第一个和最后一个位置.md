> 题目出自LeetCode
>
> [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:
```
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
```
示例 2:
```
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
```
# 思路
1. 第一种:直接找中点,然后向两边逐个找边界.
2. 第二种:先二分查找到左边界,再二分查找右边界,删除即可




# 代码
```java
//二分中心扩展性
public int[] searchRange(int[] nums, int target)
{
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi)
    {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target)
        {
            int left = mid, right = mid;
            while (left - 1 >= lo && nums[left - 1] == nums[mid]) --left;
            while (right + 1 <= hi && nums[right + 1] == nums[mid]) ++right;
            return new int[]{left, right};
        }
        else if (nums[mid] > target) hi = mid - 1;
        else lo = mid + 1;

    }
    return new int[]{-1, -1};
}
```

```java
//查找左右边界
public int[] searchRange(int[] nums, int target)
{
    return new int[]{leftBound(nums, target), rightBound(nums, target)};
}
/*
    寻找左边界
    result的取值范围为[0,N],当target大于所有数组元素时,result取到了N
*/

public int leftBound(int[] nums, int target)
{
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi)
    {
        int mid = lo + (hi - lo) / 2;

        if (nums[mid] > target) hi = mid - 1;
        else if (nums[mid] < target) lo = mid + 1;
        else hi = mid - 1; //不直接返回,往左逼近
    }
    //只要找到了,lo是不会移动的
    if(lo==nums.length || nums[lo]!=target) return -1;
    return lo;
}

public int rightBound(int[] nums, int target)
{
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi)
    {
        int mid = lo + (hi - lo) / 2;

        if (nums[mid] > target) hi = mid - 1;
        else if (nums[mid] < target) lo = mid + 1;
        else lo = mid + 1; //不直接返回,往右边逼近
    }
    //只要找到了,hi是不会移动的 hi取值为[-1,N)
    if(hi==-1 || nums[hi]!=target) return -1;
    return hi;
}
```



# 复杂度分析

## 时间复杂度

第一种方法:平均$O(log N)$,最坏$O(N)$,因为如果有很多重复的话,需要逐个遍历

第二种方法:$O(log N)$,寻找左右边界,分别需要log N的时间

## 空间复杂度

$O(1)$,两种方法都只用了常数的时间