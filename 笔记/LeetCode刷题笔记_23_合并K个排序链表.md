> 题目出自LeetCode
>
> [23. 合并K个排序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```



# 思路

分治法：调用之前写过的`mergeTwoLists`不断两两合并到只剩一条链表

优先队列法：把每个链表的首元素放入优先队列，每次取出最小的，并且把那条链表的下一个元素加入。不断进行直到优先队列为空

# 细节

1. 优先队列法内存消耗小,但比较慢.
2. 优先队列法需要存储每个元素对应的链表,所以就要定义二元组,所以就要重新传入比较器。而且要自己构造链表，注意pre和cur。

# 代码

```java
//归并法
public ListNode mergeKLists(ListNode[] lists)
{
    int n = lists.length;
    if (n == 0) return null;
    while (n > 1)
    {
        for (int i = 0; i < n / 2; i++)
        {
            lists[i] = mergeTwoLists(lists[i], lists[n - 1 - i]);
        }
        n = (n + 1) / 2;//考虑奇数有一个没参与合并,但是要参与下一次运算
    }
    return lists[0];
}

public ListNode mergeTwoLists(ListNode l1, ListNode l2)
{
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    ListNode result = null;

    if (l1.val < l2.val)
    {
        result = new ListNode(l1.val);
        result.next = mergeTwoLists(l1.next, l2);
    }
    else
    {
        result = new ListNode(l2.val);
        result.next = mergeTwoLists(l1, l2.next);
    }
    return result;
}
```

```java
//优先队列法
class Tuple
{
    Integer key;
    Integer value;

    public Tuple(Integer key, Integer value)
    {
        this.key = key;
        this.value = value;
    }
}

public ListNode mergeKLists(ListNode[] lists)
{
    ListNode pre = new ListNode(-1),cur=pre;
    //用优先队列存储每个链表最前面的值,以及他们对应是哪个链表
    Queue<Tuple> queue = new PriorityQueue<>((tuple1,tuple2)->(tuple1.key-tuple2.key));
    for (int i = 0; i < lists.length; i++)
    {
        //注意要非null
        if(lists[i]!=null)
        {
            queue.add(new Tuple(lists[i].val, i));
            lists[i] = lists[i].next;
        }
    }
    while(!queue.isEmpty())
    {
        Tuple tuple = queue.poll();//弹出最小的一个数

        //look,自己创建链表的问题l,不能用pre来算
        cur.next=new ListNode(tuple.key);
        cur = cur.next;

        //look,引用的问题,应该是让数组的引用向下一位
        if(lists[tuple.value]!=null)
        {
            queue.add(new Tuple(lists[tuple.value].val,tuple.value));//tuple.values标识是哪个链表
            lists[tuple.value] = lists[tuple.value].next;
        }
    }
    return pre.next;
}
```





# 复杂度分析
## 时间复杂度

分治法：第一轮合并k/2组链表，每组代价为$O(2n)$,第二轮合并k/4组链表，每组代价为$O(4n)$,一共合并log k次，所以为$O(kn*log k)$

优先队列法:每次优先队列最多只有k个元素，插入，删除代价为$ O(log k) $,最多有kn个节点要操作，所以为$O(kn*log k)$

## 空间复杂度

分治法：$ O(log k) $,递归用到的栈空间

优先队列法:$ O(k) $,优先队列元素不超过k个