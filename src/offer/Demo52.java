package offer;

import utils.ListNode;

public class Demo52 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode first = headA, second = headB;
        while (first != second) {
            first = (first == null ? headB : first.next);
            second = (second == null ? headA : second.next);
        }
        return first;
    }
}
