package hot;

import utils.ListNode;

public class Demo206
{
    public ListNode reverseList(ListNode head)
    {
        if(head==null || head.next==null) return head;
        ListNode pre = head, cur = head.next,next=head.next.next;
        head.next = null;
        while(cur!=null)
        {
            cur.next = pre;
            pre = cur;
            cur = next;
            if(next!=null) next = next.next;
        }
        return pre;
    }
}
