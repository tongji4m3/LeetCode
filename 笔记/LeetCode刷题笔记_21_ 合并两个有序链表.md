> 题目出自LeetCode
>
>  [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)
>
>  其他题解或源码可以访问： [tongji4m3](https://github.com/tongji4m3/LeetCode)



# 描述
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

 

示例：
```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```


# 细节
如果迭代的想要更快,可以直接改指针指向原链表.但是这样指向同一条链表可能不太好

# 代码
```java
//递归
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        if(l1==null) return l2;
        if(l2==null) return l1;

        ListNode result = null;

        if(l1.val<l2.val)
        {
            result = new ListNode(l1.val);
            result.next=mergeTwoLists(l1.next, l2);
        }
        else
        {
            result = new ListNode(l2.val);
            result.next=mergeTwoLists(l1, l2.next);
        }
        return result;
    }
```
```java
//迭代
    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {
        ListNode pre = new ListNode(-1), curr = pre;
        while (l1 != null && l2 != null)
        {
            if (l1.val < l2.val)
            {
                ListNode temp = new ListNode(l1.val);
                curr.next = temp;
                curr = temp;
                l1 = l1.next;
            }
            else
            {
                ListNode temp = new ListNode(l2.val);
                curr.next = temp;
                curr = temp;
                l2 = l2.next;
            }
        }

        while (l1 != null)
        {
            ListNode temp = new ListNode(l1.val);
            curr.next = temp;
            curr = temp;
            l1 = l1.next;
        }

        while (l2 != null)
        {
            ListNode temp = new ListNode(l2.val);
            curr.next = temp;
            curr = temp;
            l2 = l2.next;
        }

        return pre.next;
    }
```


# 复杂度分析
## 时间复杂度
$ O(n + m) $,每次都会递归调用一个节点


## 空间复杂度
递归:$ O(n + m) $,递归调用需要消耗栈空间,递归调用最坏情况 `m+n` 次
		迭代:$ O(1) $ 只需要常数的空间存放若干变量