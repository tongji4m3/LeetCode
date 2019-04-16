```
给定一个编码字符串，返回它的解码字符串。

编码规则是：k[encoded_string]，其中方括号内的encoded_string正在重复k次。注意，k保证是正整数。

您可以假设输入字符串始终有效; 没有多余的空白区域，方括号格式正确等。

此外，您可以假设原始数据不包含任何数字，并且该数字仅用于那些重复数字k。例如，不会有像3a或那样的输入2[4]。

例子：

s =“3 [a] 2 [bc]”，返回“aaabcbc”。
s =“3 [a2 [c]]”，返回“accaccacc”。
s =“2 [abc] 3 [cd] ef”，返回“abcabccdcdcdef”。
```

```c++
class Solution 
{
public:
	string decodeString(string s) 
	{
		stack<string> chars;
		stack<int> nums;
		string result="";
		int num = 0;
		for (char c : s) 
		{
			if (isdigit(c)) 
			{
				num = num * 10 + (c - '0');//如果重复了23次等,先读了2,要*10再加3
			}
			else if (isalpha(c)) 
			{
				result+=c;
			}
			else if (c == '[') 
			{
				chars.push(result);
				nums.push(num);
				result = "";
				num = 0;
			}
			else if (c == ']') 
			{
				string temp = result;
				for (int i = 0; i < nums.top() - 1; ++i) 
				{
					result += temp;
				}
				result = chars.top() + result;
				chars.pop(); nums.pop();
			}
		}
		return result;
	}
};
```
