> 题目出自LeetCode
>
>  [19. 删除链表的倒数第N个节点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：
```
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
```
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

# 思路

设置快慢指针,快指针先走n步,然后快慢指针同步走,当快指针的next为null时,慢指针next为要删除的元素

# 细节

1. 处理列表head为null的情况
2. 处理要删除头节点去情况

# 代码

```java
/*
    边界:如果head=null,返回null
    因为n保证是合理的,所以1<=n<=head.length
    如果n==head.length那么fast为null,此时是删除首元素
     */
public ListNode removeNthFromEnd(ListNode head, int n)
{
    if(head==null) return null;
    if(n<=0) throw new IllegalArgumentException();//必须>0

    ListNode fast=head, slow=head;
    for (int i = 0; i < n; i++)
    {
        if(fast==null) throw new IllegalArgumentException();
        fast = fast.next;
    }
    //如果fast==null,说明删除第一个元素
    if(fast==null)
    {
        ListNode temp = head.next;
        head.next = null;
        return temp;
    }
    //快慢指针一起往前
    while(fast.next!=null)
    {
        fast = fast.next;
        slow = slow.next;
    }
    //此时slow.next指向要删除的元素
    slow.next = slow.next.next;
    return head;
}
```





# 复杂度分析
## 时间复杂度

$O(N)$

## 空间复杂度

$O(1)$