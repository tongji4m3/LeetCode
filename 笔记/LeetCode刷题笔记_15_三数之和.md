> 题目出自LeetCode
>
>  [15. 三数之和](https://leetcode-cn.com/problems/3sum/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例：

```
给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```





# 思路

```
先排序，然后逐个遍历i，双指针lo=i+1,hi=n-1,找到lo>=hi为止
去除重复：i遇到重复的跳过，nums[i]+nums[lo]+nums[hi]==0时，++lo,--hi同时循环进行
因为可能下一个还是同样值的lo，hi，且一个移动之后，不可能再相等，为了更快两个一起移动
```

```java
sort(nums);

for(i in nums)
{
    lo=i+1,hi=n-1;
    处理第一个元素重复的情况;
    //至少为三元组
    while(lo<hi)
    {
        if(nums[lo]+nums[hi]+nums[i]==0)
        {
            加入result中;
        	注意处理重复的情况;
        }
        else
        {
            根据大小关系--hi或者++lo;
        }
    }
}


```



# 细节

1. 注意第一个处理重复要从1开始,不然会越界
2. 如果第一个就大于0,排序后的肯定找不到.所以可以优化
3. 注意后面两个元素处理重复的技巧


# 代码

```java
public List<List<Integer>> threeSum(int[] nums)
{
    List<List<Integer>> result = new LinkedList<>();
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++)
    {
        if(nums[i]>0) break;//优化
        //处理第一个元素的重复
        if(i>0 && nums[i]==nums[i-1]) continue;

        int lo = i + 1, hi = nums.length - 1;
        while(lo<hi)//至少有三个元素
        {
            if(nums[i]+nums[lo]+nums[hi]==0)
            {
                result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                //去重
                //look,必须至少强制位移一次
                //因为去掉重复,所以一个位移了,另外一个必然是要位移的
                //                    while(lo<hi && nums[lo+1]==nums[lo]) ++lo;
                //                    while(lo<hi && nums[hi]==nums[hi-1]) --hi;
                //look,注意细节,如果先++lo了,那肯定会错,因为两个相等了
                //                    while(lo<hi && nums[++lo]==nums[lo]);
                //                    while(lo<hi && nums[--hi]==nums[hi]);
                while(lo<hi && nums[lo]==nums[++lo]);
                while(lo<hi && nums[hi]==nums[--hi]);
            }
            else if(nums[i]+nums[lo]+nums[hi]>0) --hi;
            else ++lo;
        }

    }
    return result;
}
```



# 复杂度分析
## 时间复杂度

$O(N^2)$,外层遍历一次数组,随后又用lo,hi内层循环遍历

## 空间复杂度

$O(1)$ 忽略返回结果消耗的内存,忽略Arrays.sort()有可能消耗的内存