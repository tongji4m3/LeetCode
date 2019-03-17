
```
给定一个整数数组，返回两个数字的索引，使它们相加到特定目标。

您可以假设每个输入只有一个解决方案，并且您可能不会两次使用相同的元素。

例：

给定nums = [2,7,11,15]，target = 9，

因为nums [ 0 ] + nums [ 1 ] = 2 + 7 = 9，
返回[ 0，1 ]。

```  

```c++
//暴力算法,效率较低
static vector<int> twoSum(vector<int> & nums, int target)
	{
		vector<int> result;	
		for (int i = 0; i < nums.size() - 1; i++)
		{
			for (int j = i + 1; j < nums.size(); j++)
			{
				if (nums[i] + nums[j] == target)
				{
					result.push_back(i);
					result.push_back(j);
				}
			}
		}
		return result;
		
	}

```

```c++
static vector<int> twoSum(vector<int>& nums, int target) 
{
	//s需要#include<unordered_map>,unordered_map适用于查找,查找平均为o(1)?
	unordered_map<int, int> map;
	for (int i = 0; i < nums.size(); i++) 
	{
		auto p = map.find(target - nums[i]);
		if (p != map.end()) 
		{
			return { p->second , i  };
			//return {1,2,3,4}可以返回一个vector数组
		}
		map[nums[i]] = i;
	}
}
```
