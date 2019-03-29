```
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```
```c++
//递归
class Solution 
{
public:
	ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) 
	{
		if (l1 == NULL) return l2;
		if (l2 == NULL) return l1;

		ListNode* current = new ListNode(0);
		ListNode* head = current;
		if (l1->val > l2->val) 
		{
			current->next = new ListNode(l2->val);
			l2 = l2->next;
		}
		else 
		{
			current->next = new ListNode(l1->val);
			l1 = l1->next;
		}
		current = current->next;
		current->next = mergeTwoLists(l1, l2);
		delete head;
		return current;
	}
};
```
```c++
//更快更简单的一种
ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) //归并两个链表
	{
		ListNode *head = new ListNode(-1), *current = head;
		while (l1 && l2) 
		{
			if (l1->val < l2->val) 
			{
				current->next = l1;
				l1 = l1->next;
			}
			else 
			{
				current->next = l2;
				l2 = l2->next;
			}
			current = current->next;
		}
		current->next = l1 ? l1 : l2;
		return head->next;
	}
```
