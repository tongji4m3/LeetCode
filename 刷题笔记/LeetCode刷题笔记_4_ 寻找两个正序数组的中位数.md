> 题目出自LeetCode
>
>   [4. 寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。


```

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0

```


```
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

```

# 思路

选择两个数组中较小的一个,先将之对半分,通过计算,对另一个数组在某位置也切一刀，使得两个数组切下那刀后左右两边数量相同。然后判断切出来的数字是否是中位数。如果不是中位数，则继续二分，直到找到中位数。


```java
选择较小的那个数组;
lo=0,hi=数组长度-1;
while(true)
{
    mid=lo+(hi-lo)/2;
    在另一个数组切一刀，使得切下去后两边元素相等;
    判断是否为中位数，是则退出循环，否则根据大小判断增大lo或者减小hi;
}
```





# 细节

1. 切分也要能在两个数的中间切，不然可能切不平衡。所以开始先要对两个数组扩充成2*n+1的大小(逻辑上扩充，物理上不扩充)
2. 要判断切割后是否是中位数，则要找到切割元素的左右两边的值，所以要定义四个变量标识两个数组的左右两边的值。
3. 计算第二个数组的中位数：mid1,mid2也是在逻辑扩充后的数组上操作.扩充后总共有2（m+n+1)个元素，切分的mid1代表了num1左边元素的个数，mid1+mid2应该等于m+n，所以mid2=m+n-mid1.(可画图分析)
4. 计算切分元素两边的值：如果mid为基数，切的是元素，mid/2即可得到元素，他的左右两边就是他本身。如果是偶数，则是切两个数之间，mid/2就是右边元素，(mid-1)/2就是左边元素。因为（基数-1）/2效果一样，所以两种情况可以合并。
5. 还要考虑左右两边如果是没有数字的问题。
6. 判断往哪边二分可以画图分析.如果找到了值,返回的中位数为四个值中最中间的两个(也可以画图讨论正确性).
7. 标为look的为之前写错后来改的部分

​	

# 代码
```java
public double findMedianSortedArrays(int[] nums1, int[] nums2)
{
    int m = nums1.length, n = nums2.length;
    //只选取小的切分 所以取反,look 直接return,而不是调用
    if (m > n) return findMedianSortedArrays(nums2, nums1);

    int L1, R1, L2, R2;//切割元素左右两边的值
    int mid1, mid2;
    //逻辑上扩充成2*n+1,但是最后一个元素还要-1
    int lo = 0, hi = 2 * m;

    while (lo<=hi)
    {
        mid1 = lo + (hi - lo) / 2;
        mid2 = m + n - mid1;//计算得到
        //计算切分元素两边的值
        //look 首先判断条件是2*m,扩充后的数组
        //且L1,L2为值,不是索引,所以是nums1[(mid1 - 1) / 2]而不是(mid1 - 1) / 2
        L1 = (mid1 == 0 ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2]);
        R1 = (mid1 == 2*m ? Integer.MAX_VALUE : nums1[mid1 / 2]);
        L2 = (mid2 == 0 ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2]);
        R2 = (mid2 == 2*n ? Integer.MAX_VALUE : nums2[mid2 / 2]);

        if(L1 > R2) hi = mid1 - 1;
        else if(L2 > R1) lo = mid1 + 1;
        else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;//look 注意运算优先级
    }
    return -1.0;
}
```


# 复杂度分析
## 时间复杂度
选取最小的数组进行二分,而且二分循环中为常数操作,所以O(min(m,n))
## 空间复杂度
只定义了固定的几个变量,O(1)