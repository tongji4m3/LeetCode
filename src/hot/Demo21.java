package hot;

import utils.ListNode;

public class Demo21
{
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

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2)
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
}
