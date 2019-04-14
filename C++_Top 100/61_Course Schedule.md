```
共有都是ñ课程，你必须采取从标记0到n-1。

有些课程可能有先决条件，例如要学习0，你必须先修课程1，这表示为一对： [0,1]

鉴于课程总数和先决条件对列表，您是否可以完成所有课程？

例1：

输入： 2，[[1,0]] 
输出： true
说明：  共有2门课程。
要修课程1，你应该完成课程0.所以有可能。
例2：

输入： 2，[[1,0]，[0,1]]
输出： false
说明：  共有2门课程。
要修课程1，你应该完成课程0，并且你需要修课程0
也完成了课程1.所以这是不可能的。
注意：

输入先决条件是由边列表而不是邻接矩阵表示的图。详细了解图表的表示方式。
您可以假设输入先决条件中没有重复的边。
```

```c++
class Solution
{
public:
	bool canFinish(int n, vector<pair<int, int>>& pre) 
	{
		vector<vector<int>> adj(n, vector<int>());
		vector<int> degree(n, 0);
		for (auto& p : pre) 
		{
			adj[p.second].push_back(p.first);
			++degree[p.first];//p.first的先决条件又加了一个
		}
		queue<int> q;
		for (int i = 0; i != n; ++i)
			if (degree[i] == 0) q.push(i);//没有先决条件的先加入
		while (!q.empty()) 
		{
			int cur = q.front(); q.pop(); 
			--n;//只剩下n个要学的
			for (auto next : adj[cur])//adj[cur]里面是要先学cur的
				if (--degree[next] == 0) q.push(next);//把cur给学了,如果能学,就加入进去
		}
		return n == 0;//全部能学掉
	}
};
```
