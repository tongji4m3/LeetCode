> 题目出自LeetCode
>
> [48. 旋转图像](https://leetcode-cn.com/problems/rotate-image/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:
```
给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```
示例 2:
```
给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```


# 思路
外围旋转中,只需要把[0,0],到[0,n-2]的依次与对应的四个角旋转即可
因为是方阵,所以定义lo,hi表示遍历的上层,遍历的下层,而且对左右也是有用的
# 细节


# 代码

```java
public void rotate(int[][] matrix)
{
    int lo = 0, hi = matrix.length - 1;
    while(lo<hi)
    {
        for (int i = 0; i < hi - lo; i++)
        {
            int temp=matrix[lo][lo+i];
            matrix[lo][lo + i] = matrix[hi - i][lo];
            matrix[hi - i][lo] = matrix[hi][hi - i];
            matrix[hi][hi - i] = matrix[lo + i][hi];
            matrix[lo + i][hi] = temp;
        }
        ++lo;
        --hi;
    }
}
```



