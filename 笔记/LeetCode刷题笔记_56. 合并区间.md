> 题目出自LeetCode
>
>  [56. 合并区间](https://leetcode-cn.com/problems/merge-intervals/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给出一个区间的集合，请合并所有重叠的区间。

 

示例 1:
```
输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```
示例 2:
```
输入: intervals = [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
```
提示：

`intervals[i][0] <= intervals[i][1]`

# 思路

```python
按区间的第一个元素排序
for i in N:
    lo=intervals[i][0];
    hi=intervals[i][1];
    while(i+1<N && hi>=intervals[i+1][0]) hi=intervals[++i][1];//扩展该区间
    result.add((lo,hi));

```



# 细节

1. 区间长度要选两个合并区间最大的一个
2. 注意集合和数组的转换操作


# 代码

```java
public int[][] merge(int[][] intervals)
{
    int N = intervals.length;
    List<int[]> result = new LinkedList<>();
    //按第一个元素排序
    Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
    for (int i = 0; i < N; i++)
    {
        int lo=intervals[i][0];
        int hi=intervals[i][1];
        while(i+1<N && hi>=intervals[i+1][0])
        {
            ++i;//表明把下一个区间也纳入到了本区间里了
            hi=Math.max(hi,intervals[i][1]);//look 扩展该区间,选最大的一个
        }
        result.add(new int[]{lo, hi});
    }
    return (int[][]) result.toArray(new int [result.size()][2]);
}
```

# 复杂度分析
## 时间复杂度

$O(N log N)$,主要是排序开销,其他的就是N开销的扫描

## 空间复杂度

$O(logN)$,计算出了答案外的额外空间开销,这里是排序所需的空间复杂度