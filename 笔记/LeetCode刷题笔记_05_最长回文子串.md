> 题目出自LeetCode
>
>   [5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。


```

示例 1:

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

```


```
示例 2:

输入: "cbbd"
输出: "bb"
```

# 思路
遍历一次数组，对每次遍历，从那个字符为中心开始向左右扩展直到不能扩展为止。扩展分为一个字符扩展和两个字符扩展两种。



```java
for(char ch,int i in string)
{
	int lo=i,hi=i;
	//单向扩展
	while()
	{
		//如果符合条件，则一直扩展
		--lo,++hi;
	}
	
	result=max(result,substring(lo,hi+1));
	//双向扩展
	lo=i,hi=i+1;
	while()
	{
		//如果符合条件，则一直扩展
		--lo,++hi;
	}
}

```



	# 细节

1. 如果不用额外空间，就用两个指针指向要返回字符串的位置，他们的初始值应该都是0。那么这样就先要判断s是否为空字符串。
2. 注意s.substring()截取的是左闭右开区间，自己设计的变量标识是[left,right]

# 代码
```java
public String longestPalindrome(String s)
{
    if(s.length()==0) return s;
    int left = 0, right = 0;//结果

    for (int i = 0; i < s.length(); i++)
    {
        char ch = s.charAt(i);
        int lo = i, hi = i;
        //单向扩展
        while(lo-1>=0 && hi+1<s.length() && s.charAt(lo-1)==s.charAt(hi+1))
        {
            --lo;
            ++hi;
        }
        //其实是right-left+1 < hi-lo+1
        if((right-left)<(hi-lo))
        {
            left = lo;
            right = hi;
        }

        //双向扩展
        lo = i;
        hi = i + 1;
        //如果一开始就不能双向扩展,就不用管了
        if(hi<s.length() && s.charAt(lo)==s.charAt(hi))
        {
            while(lo-1>=0 && hi+1<s.length() && s.charAt(lo-1)==s.charAt(hi+1))
            {
                --lo;
                ++hi;
            }
            if((right-left)<(hi-lo))
            {
                left = lo;
                right = hi;
            }
        }
    }
    return s.substring(left,right+1);
}
```


# 复杂度分析
## 时间复杂度

循环遍历的时间复杂度为O(N),中心扩散的时间复杂度也为O(N),所以总共为$ O(N^2) $


## 空间复杂度

只用了几个变量，和string的长度无关，所以是O(1)