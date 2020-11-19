package hot;

import utils.ListNode;

public class Demo234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //用快慢指针找到链表的中点
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果是奇数,就是正中,偶数则是第二条链表的起始位置

        //对后半段反转链表
        ListNode pre = reverse(slow);

        //前后段链表逐个对比
        ListNode first = head, second = pre;
        while (second != null) {
            if (first.val != second.val) {
                reverse(pre);
                return false;
            }
            first = first.next;
            second = second.next;
        }

        //将后半段链表反转回来
        reverse(pre);
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null, temp = head, cur = head;
        while (cur != null) {
            cur = cur.next;
            temp.next = pre;
            pre = temp;
            temp = cur;
        }
        return pre;
    }
}
