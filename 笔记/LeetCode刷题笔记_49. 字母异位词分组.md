> 题目出自LeetCode
>
> [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:
```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
说明：

所有输入均为小写字母。
		不考虑答案输出的顺序。

# 思路

用一个Map存储索引和索引对应的组。索引是由字母异位词算出来的唯一标识。这样，遍历strs数组，把索引相同的字符串放在同一组中即可

关于索引的计算，可以将每个字母与一个素数绑定。对于一个字符串，则将包含的每个字母对于的素数相乘，得到的和即为索引



第二种写法和上一种相似：map存储字典序字符串和对应的组。每次循环都把字符串转为字典序字符串，再判断

# 细节

map直接存储用于区分组的索引，和List组即可，节约内存


# 代码

```java
//生成素数
private List<Integer> prime()
{
    int N = 26;
    List<Integer> result = new LinkedList<>();

    for (int i = 2; i < 1000; i++)
    {
        int j;
        for (j = i - 1; j > 1; j--)
        {
            if (i % j == 0) break;
        }
        if(j==1) result.add(i);
        if(result.size()==N) break;
    }
    return result;
}
```

```java
//素数可以提前生成好
public List<List<String>> groupAnagrams(String[] strs)
{
    Map<Integer, List<String>> map = new HashMap<>();
    //List<Integer> prime = prime();
    //预先准备好
    int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    for (String string : strs)
    {
        int index = 1;
        for (char ch : string.toCharArray())
        {
            //                index*=prime.get(ch - 'a');
            index*=prime[ch-'a'];
        }
        if(!map.containsKey(index)) map.put(index, new LinkedList<>());
        map.get(index).add(string);
    }
    return new LinkedList<>(map.values());
}
```

```java
//第二种思路
//map存储字典序字符串和对应的组。每次循环都把字符串转为字典序字符串，再判断
public List<List<String>> groupAnagrams1(String[] strs)
{
    Map<String, List<String>> map = new HashMap<>();
    for (int i = 0; i < strs.length; i++)
    {
        //map中只存储字典序字符串
        char[] chars = strs[i].toCharArray();
        Arrays.sort(chars);
        String string = String.valueOf(chars);
        //第一次放入某类型的字母异位词
        if (!map.containsKey(string)) map.put(string, new LinkedList<>());

        map.get(string).add(strs[i]);
    }
    return new LinkedList<>(map.values());
}
```

# 复杂度分析

## 时间复杂度

第一种方法：$O(NK)$ N是数组的长度，K是字符串的最大长度。外部循环需要遍历一遍字符串数组，内部需要对每个字符串进行索引计算。

第二种方法：$O(NK \log K)$   N是数组的长度，K是字符串的最大长度。外部循环需要遍历一遍字符串数组。内部需要对每个字符串进行排序

## 空间复杂度

$O(NK)$ 存储在Map的values中共需要NK的空间