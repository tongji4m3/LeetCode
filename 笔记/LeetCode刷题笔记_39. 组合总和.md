> 题目出自LeetCode
>
> [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
		解集不能包含重复的组合。 

```
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```

```
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

```
提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都是独一无二的。
1 <= target <= 500
```



# 思路

采用回溯法

因为元素都是独一无二的且不重复,所以很多特殊情况不用考虑。例如当target等于0时，就不用往该分支下进行了。

而且result中不含重复，所以需要措施跳过重复的（循环中小于父节点的）。

```
//伪代码
sort(nums);
int sum=0;
List temp;


recursive(target)
{
	if(target==0) 
	{
		result.add(temp);
		return;
	}
    for(i in N)
    {
        if(nums[i]>target) return;//剪枝条件
        if(nums[i]<temp.top()) continue;//剪枝条件 避免重复
        

        temp.add(nums[i]);
		recursive(target-nums[i]);		
        temp.remove(nums[i]);
    }
}

```



# 细节

1. 加入结果时，要注意引用问题，重新复制到一个新的list
2. 注意处理重复问题
3. 注意剪枝优化问题


# 代码

```java
private List<Integer> temp = new LinkedList<>();
private List<List<Integer>> result = new LinkedList<>();

public List<List<Integer>> combinationSum(int[] candidates, int target)
{
    Arrays.sort(candidates);
    recursive(candidates, target,0);
    return result;
}
void recursive(int[] candidates,int target,int begin)
{
    if(target==0)
    {
        result.add(new LinkedList<>(temp));//look 这里有引用问题,应该是复制一个新的给result
        return;
    }
    for (int i = begin; i < candidates.length; i++)
    {
        if(candidates[i]>target) return;//剪枝条件
        
        //look 注意越界问题
        //可优化成开始条件
        //if(!temp.isEmpty() && candidates[i]<temp.get(temp.size()-1)) continue;//剪枝条件 避免重复

        temp.add(candidates[i]);//look 不需要sum
        recursive(candidates,target-candidates[i],i);
        temp.remove(temp.size()-1);
    }
}
```



