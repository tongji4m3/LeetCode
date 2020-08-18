> 题目出自LeetCode
>
>  [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

```
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```



示例 2:

```
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```

# 思路

```java
//伪代码
int lo=0,hi=n-1;
while(lo<=hi)
{
    int mid=lo+(hi-lo)/2;
    if(nums[mid]==target) return mid;
    
    if(target>nums[hi])//target在数组切分后的左边
    {
        //可以简化
        if(nums[mid]>target) hi=mid-1;//mid在左边的上半部分
        else if(nums[mid]>nums[lo]) lo=mid+1;//mid在左边的下半部分
        else  hi=mid-1;//在右边部分
    }
    else//在右边
    {
        //可以简化
        if(nums[mid]<target) lo=mid+1;//mid在右边的下半部分
        else if(nums[mid]>nums[lo]) lo=mid+1;//mid在左边部分
        else  hi=mid-1;//在右边的上半部分
    }
}
```



# 细节

1. 要画图分析,分情况讨论
2. 注意是纯递增的情况。经验是nums[mid]与nums[lo],nums[hi]最接近的一个比较


# 代码

```java
/*
    画图分析!
    关键是先定下target的位置
    不存在重复,那么lo与hi对应的值不会相等(lo!=hi)时
     */
public int search(int[] nums, int target)
{
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi)
    {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) return mid;

        if (target > nums[hi])//target在数组切分后的左边
        {
            if(nums[mid] < target && nums[mid] > nums[lo]) lo = mid + 1;
            else hi = mid - 1;
        }
        else//在右边
        {
            //look 要考虑是纯递增的情况
            if(nums[mid] > target && nums[mid] < nums[hi]) hi = mid - 1;
            else lo = mid + 1;
        }
    }
    return -1;
}
```



# 复杂度分析
## 时间复杂度

$O(log N)$

## 空间复杂度

$O(1)$