> 题目出自LeetCode
>
> 热题100中的第31-40题
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# [75. 颜色分类](https://leetcode-cn.com/problems/sort-colors/)

## 思路

采用快排的partition思想,在数组首尾定义指针,lo标识0的结束(保证lo-1处一定为0),hi标识2的开始(保证hi+1处一定为2).再用指针i来遍历数组

一个比较好的测试用例`2 0 1 0 1 2`

```java
public void sortColors(int[] nums)
{
    int lo=0,hi=nums.length-1;
    int i=0;
    while(i<=hi)
    {
        if(nums[i]==0)
        {
            //exch(nums,i++,lo++); 由于特殊性,化简
            nums[i++] = nums[lo];
            nums[lo++] = 0;
        }
        else if(nums[i]==1)
        {
            ++i;
        }
        else
        {
            //                exch(nums,i,hi--);
            nums[i] = nums[hi];
            nums[hi--] = 2;
        }

    }
}
```

