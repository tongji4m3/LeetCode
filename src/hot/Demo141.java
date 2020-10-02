package hot;

import utils.ListNode;

public class Demo141
{
    public boolean hasCycle(ListNode head)
    {
        ListNode fast = head, slow = head;
        while(fast!=null && fast.next!=null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow) return true;
        }
        return false;
    }
}