> 题目出自LeetCode
>
>  [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

```


示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

```


```
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

```

```
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

```

# 思路



```java
	//伪代码
	maxLength=0,lo=0,hi=0
	Map<字符,index> map
    for(i,ch in string)
    {
        hi=i
        if(map.find(ch)!=-1) lo=max(map.find(ch)+1,lo) //说明有重复的
        map.put(ch, hi);
        maxLength=max(maxLength,hi-lo+1);   
            
    }
    
```





# 细节

1.  因为可能在中间取得最大值,所以每一次都要判断一下`maxLength=max(maxLength,hi-lo+1);`

2.  通过HashMap查看是否有重复的元素,有则更新到重复元素之后,相当于去掉那个重复元素

3.  一定要把最新遍历到的put到map中,这样不管里面有没有,都会更新到最前面的位置

4.  `lo=max(map.find(ch)+1,lo) `是因为例如abba等,在遍历完第二个b时,lo=2,而遍历最后的a,`map.find(ch)+1`值为1,就不符合实际情况。这样做的本质是取历史不重复子串和此次不重复子串的交集。


​	

# 代码
```java
public int lengthOfLongestSubstring(String s)
{
    int maxLength = 0, lo = 0, hi = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s.charAt(i);
        hi = i;
        if(map.containsKey(ch)) lo = Math.max(lo,map.get(ch)+1);//look
        map.put(ch, hi);//look
        maxLength = Math.max(maxLength, hi - lo + 1);
    }
    return maxLength;
}
```
# 优化
因为是字符，所以改用大小为128的数组存储,思路是一样的，但是用数组快一点
```java
public int lengthOfLongestSubstring(String s)
{
    int result = 0,lo=0;
    int[] map = new int[128];
    Arrays.fill(map, -1);//初始化为-1
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s.charAt(i);
        lo = Math.max(lo, map[ch]+1);
        map[ch] = i;
        result = Math.max(result, map[ch] - lo + 1);
    }
    return result;
}
```

# 复杂度分析
## 时间复杂度
对字符串进行一次遍历，循环内操作都可以在常数时间内解决，例如map的put，get方法。所以时间复杂度O(N)
## 空间复杂度
只需要字符集大小的空间存储即可（重复的字符也是如此），所以空间复杂度为O(|E|) 其中的E代表字符集