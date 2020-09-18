package hot;

import utils.ListNode;

import java.util.List;

public class Demo142
{
    public ListNode detectCycle(ListNode head)
    {
        ListNode fast = head, slow = head;
        while (true)
        {
            if(fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) break;
        }
        //必然有循环
        fast = head;
        while (true)
        {
            if(slow==fast) return fast;
            fast = fast.next;
            slow = slow.next;
        }
    }
}
