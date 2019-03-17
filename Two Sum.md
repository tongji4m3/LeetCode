
给定一个整数数组，返回两个数字的索引，使它们相加到特定目标。

您可以假设每个输入只有一个解决方案，并且您可能不会两次使用相同的元素。

例：

给定nums = [2,7,11,15]，target = 9，

因为nums [ 0 ] + nums [ 1 ] = 2 + 7 = 9，
返回[ 0，1 ]。

```  

```java
//不成功的解法:(原因是如果是两个相同元素得不到正确的值,例如[3,3])
static vector<int> twoSum(vector<int> & nums, int target)
{
    vector<int> copy_nums(nums);
		sort(copy_nums.begin(), copy_nums.end());
		//排好序,且没有重复元素,只有一个解决方案
		vector<int> result;
		if (copy_nums.size()<2 || target < copy_nums[0] || target>(copy_nums[copy_nums.size()-1]+ copy_nums[copy_nums.size() - 2]))
		{
			return result;
		}
		vector<int>::iterator it;
		for (it=copy_nums.begin(); it!=copy_nums.end()-1; it++)
		{
			int target_i = target - *it;//which we want
			if (binary_search(it+1, copy_nums.end(), target_i))
			{
				vector<int>::iterator result_it;
				result_it = lower_bound(it+1, copy_nums.end(), target_i);
				vector<int>::iterator nums_it= find(nums.begin(), nums.end(), *it);
				vector<int>::iterator nums_end= find(nums.begin(), nums.end(), *result_it);
				int first=distance(nums.begin(), nums_it);
				int second=distance(nums.begin(), nums_end);
				result.push_back(first);
				result.push_back(second);
				
			}
		}
		return result;
 }
```
