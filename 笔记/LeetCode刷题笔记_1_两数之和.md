> 题目出自LeetCode
>
> 1.两数之和
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

 

```
示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9

所以返回 [0, 1]
```



# 思路

不断将元素加入Map集合中,key为值,value为数组的索引。这样，不断在map中查找（target-下一个数组元素），如果找到，就可以直接返回两个对应的索引。找不到，则将该元素放入map中，继续数组下一个元素。（因为规定了只有一个，所以不需要考虑覆盖map中相同key的问题）



# 细节

1. 返回的是数组索引

2. 每种输入只会对应一个答案:找到即可返回

3. 数组同一个元素只可使用一次,但是用map不需要考虑这个问题
4. 找不到则返回{-1，-1}

# 代码
```java
public int[] twoSum(int[] nums, int target)
{
    Map<Integer, Integer> map = new HashMap<>();//value,index
    for (int i = 0; i < nums.length; i++)
    {
        if(map.containsKey(target-nums[i])) 
            return new int[]{i, map.get(target - nums[i])};     
        map.put(nums[i], i);
    }
    return new int[]{-1, -1};
}
```


# 复杂度分析
## 时间复杂度
HashMap理想或者均摊情况下的时间复杂度O(1)。最坏情况对数组进行一次循环，总时间复杂度为O（N）
## 空间复杂度
最坏情况把所有数组都放入了Map中。所以空间复杂度为O（N）