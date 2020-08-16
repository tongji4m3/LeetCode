> 题目出自LeetCode
>
>  [31. 下一个排列](https://leetcode-cn.com/problems/next-permutation/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
```
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
```

# 思路
从后往前,找到第一个非递增数(说明他可以换)。换则继续从后往前找到第一个大于它的数，将他们两交换。再将换的索引之前的逆序，即可得到下一个排列。
要注意54321这种全逆序的情况
# 细节

1. 注意什么时候要返回了
2. 注意索引的一些细节，还有反转数组时的索引


# 代码
```java
public void nextPermutation(int[] nums)
{
    int n = nums.length;
    int index;//从倒数第二个元素开始比
    for (index = n - 2; index >= 0; index--)
    {
        if (nums[index] < nums[index + 1]) break;//=号不行
    }
    if (index < 0) //说明是54321这种或者为空,或者只有一个元素
    {
        //颠倒即可
        reverse(nums, 0, n - 1);
        return;
    }
    //找到第一个大于他的
    for (int i = n - 1; i >= 0; i--)
    {
        if (nums[index] < nums[i])//=号不行
        {
            exch(nums, index, i);
            //从index+1,到n-1,反转数组
            reverse(nums, index + 1, n - 1);
            return;//look,做完直接return了
        }
    }
}

//lo,hi为要反转数组的开始,结束索引位置
private void reverse(int[] nums, int lo, int hi)
{
    for (int i = 0; i < (hi - lo + 1) / 2; i++) exch(nums, lo + i, hi - i);
    //look 判断条件这有问题
    //        for (int i = lo; i < (hi - lo + 1) / 2; i++) exch(nums, i, hi - i);
}

private void exch(int[] nums, int i, int j)
{
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```


# 复杂度分析
## 时间复杂度

$O(N)$，最坏情况下只需要对整个数组进行两次扫描，以及一次数组反转

## 空间复杂度

$O(1)$,在原数组上进行交换