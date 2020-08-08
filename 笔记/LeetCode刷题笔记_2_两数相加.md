> 题目出自LeetCode
>
> 2.两数相加
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
```
示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

```



# 思路

可以在循环中不断计算两个指针对应的值之和,然后两个指针再指向链表下一个位置(同时考虑是否有进位的情况,以及链表是否为空的情况),计算直到两个指针为空并且没有进位 
```java
//伪代码
while(链表1!=null || 链表2!=null || 有进位)
{
	对链表1,链表2如果为null时的特殊处理
	value=链表1+链表2+进位
	value考虑是否进位的处理
	构造新的结果链表
}
return 结果链表
```





# 细节

1.  因为都是逆序存储,所以从链表头部计算相当于从两个数的低位开始计算,就算不对齐也不会影响结果.例如2->4->3与4->5->6->2,就是342+2654
2. 因为非空链表,而且非负整数,每个节点存储一位数字,所以不用讨论很多特殊情况,且要考虑进位
3.  也可以采用递归,但是能用循环还是用循环
4.  链表总是得考虑null值情况的处理
5.  当l1==null,l2==null但是carryOver(进位)==1的情况,还要构造新的链表,所以while循环有三个条件
6.  `carryOver = value / 10`的计算必须在`value = value % 10`之前,因为后者已经把value降为10以下

# 代码
```java
//ListNode
public class ListNode
{
    public int val;
    public ListNode next;

    public ListNode(int x)
    {
        val = x;
    }
}
```
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2)
{
    //第一个元素只是为了方便编程
    //result代表结果链表的头部,current则构造该链表
    ListNode result = new ListNode(-1);
    ListNode current = result;

    int carryOver = 0;//进位标志
    //三者只要其中一个满足条件,就能计算下去
    while (l1 != null || l2 != null || carryOver != 0)
    {
        //需要判断l1,l2是否为null
        int value1 = 0, value2 = 0;
        if (l1 != null)
        {
            value1 = l1.val;
            l1 = l1.next;
        }
        if (l2 != null)
        {
            value2 = l2.val;
            l2 = l2.next;
        }

        int value = value1 + value2 + carryOver;
        //是否进位
        //顺序不能颠倒,否则value已经<10,carryOver一定为0
        carryOver = value / 10;
        value = value % 10;

        //构造结果链表
        current.next = new ListNode(value);
        current = current.next;
    }
    //链表第二个元素起才有实际的意义
    return result.next;
}
```


# 复杂度分析
## 时间复杂度
设m,n分别为链表1,链表2的长度,因为循环的次数是max(m,n),所以时间复杂度为O(max(m,n))
## 空间复杂度
O(max(m,n)),因为两个链表的值相加,最后链表长度最大为max(m,n)+1(最前面进位1的情况)