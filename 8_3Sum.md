```
给定阵列nums的Ñ整数，是否有元件一，b，c ^在nums使得一个 + b + c ^ = 0？找到数组中所有唯一的三元组，它们的总和为零。

注意：

解决方案集不得包含重复的三元组。

例：

给定数组nums = [-1,0,1,2，-1，-4]，

解决方案集是：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

```c++
class Solution 
{
//算法差不多,但是细节要注意,越界问题,重复问题,最好不要分类讨论,如size()<3
public:
	vector<vector<int> > threeSum(vector<int> &nums) 
	{
		vector<vector<int> > result;
		sort(nums.begin(), nums.end());
		for (int i = 0; i != nums.size(); ++i) 
		{
			int target = -nums[i];//找到相反数
			int front = i + 1;
			int back = nums.size() - 1;
			while (front < back) 
			{
				int sum = nums[front] + nums[back];
				if (sum < target) //front++可以使得sum变大
					front++;
				else if (sum > target)
					back--;
				else 
				{
					vector<int> zero_vec(3, 0);
					zero_vec[0] = nums[i];
					zero_vec[1] = nums[front];
					zero_vec[2] = nums[back];
					result.push_back(zero_vec);
					while (front < back && nums[front] == zero_vec[1]) front++;//避免找到重复的
					while (front < back && nums[back] == zero_vec[2]) back--;
				}
			}
			while (i != nums.size() && i + 1 < nums.size() && nums[i + 1] == nums[i])//避免重复的
				++i;
		}
		return result;
	}
};
```
