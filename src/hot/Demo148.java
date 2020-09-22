package hot;

import utils.ListNode;

import java.util.List;

public class Demo148
{
    public ListNode sortList(ListNode head)
    {
        if (head == null || head.next == null) return head;

        //将链表大致平分成head,slow两条
        //look 先将fast向后移动一位,这样切分单数数组就是前面少一个,后面多一个.切分双数正好相等
        //而且这样可以减少处理的麻烦
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode second = slow.next;
        slow.next = null;//为了断开中点前一个节点与中点之间的连接


        head = sortList(head);//look,要接收改变后的链表的引用
        second = sortList(second);

        //对两条有序链表进行合并操作
        ListNode pre = new ListNode(-1);
        ListNode temp = pre;

        while (head != null && second != null)
        {
            int value = 0;
            if (head.val < second.val)
            {
                value = head.val;
                head = head.next;
            }
            else
            {
                value = second.val;
                second = second.next;
            }
            temp.next = new ListNode(value);
            temp = temp.next;
        }
        temp.next = (head == null ? second : head);

        return pre.next;
    }

//    private ListNode merge(ListNode first, ListNode second)
//    {
//        if (first == null) return second;
//        if (second == null) return first;
//        ListNode result;
//        if (first.val > second.val)
//        {
//            result = new ListNode(second.val);
//            result.next = merge(first, second.next);
//        }
//        else
//        {
//            result = new ListNode(first.val);
//            result.next = merge(first.next, second);
//        }
//        return result;
//    }
}
