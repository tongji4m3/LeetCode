> 题目出自LeetCode
>
>  [17. 电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。


![Mon Aug 10 064242 CST 2020](https://tongji4m3.oss-cn-beijing.aliyuncs.com/Mon Aug 10 064242 CST 2020.png)
```java
输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

# 思路

用数组存储数字与字符串的对应关系.

递归,先加入字符,递归到下一层,再移除那个字符

到最后一个的时候,就加入结果,停止本层的递归

```java
if(i==length)
	result.add(stringBuilder.toString());

for(ch in map[i])//每个数字字符对应的字符串
{
	stringBuilder.append(ch);
    recursive(i+1);
    stringBuilder.remove(ch);
}

```



# 细节

1. 把方法局部变量变成成员变量,加快速度
2. 注意传入空串时的返回
3. 用`map[digits.charAt(index)-'0']`转换优化速度


# 代码

```java
public class Demo17
{
    private String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private LinkedList<String> result = new LinkedList<>();
    private StringBuilder stringBuilder = new StringBuilder();


    //方法局部变量变成成员变量,加快速度
    public List<String> letterCombinations(String digits)
    {
        //look 长度为0,返回空,而不是空的字符串串
        if(digits==null || digits.length()==0) return result;
        recursive(digits,0);
        return result;
    }

    //result为结果集合,stringBuilder存储中间字符串,index代表递归到哪个数字字符
    private void recursive(String digits, int index)
    {
        if(index==digits.length())
        {
            result.add(stringBuilder.toString());
            return;
        }

        //优化速度
        String string = map[digits.charAt(index)-'0'];//例如abc
        for (int i = 0; i < string.length(); i++)
        {
            stringBuilder.append(string.charAt(i));//例如加入a
            recursive(digits, index + 1);//继续下一层
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);//删除刚刚加入的
        }
    }
}
```



# 复杂度分析
## 时间复杂度

因为是普通的递归,所以结果有多少种,时间复杂度就是多少.假如x是`234568`的总数量(一个数字对应三个字母),y是`79`数字的总数量(对应四个字符).总时间复杂度为$O(3^x4^y)$ ,(x+y)为输入的总字符串长度.

## 空间复杂度

需要存储这些结果,空间复杂度也为$O(3^x4^y)$