================================================================================
https://leetcode.com/problems/add-two-numbers/description/
================================================================================
Test
------------------------------------------------------------
Java
Accepted.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        int tenbit = 0;

        while ((l1 != null) || (l2 != null)) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + tenbit;
            tenbit = sum / 10;
            sum %= 10;
            ListNode temp = new ListNode(sum);

            if (head == null) {
                head = current = temp;
            } else {
                current.next = temp;
                current = temp;
            }

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (tenbit > 0) {
            current.next = new ListNode(tenbit);
        }

        return head;
    }
}
------------------------------------------------------------
Java
Accepted.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        int tenbit = 0;

        while ((l1 != null) || (l2 != null)) {
            int sum = tenbit;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            tenbit = sum / 10;
            ListNode temp = new ListNode(sum % 10);

            if (head == null) {
                head = current = temp;
            } else {
                current.next = temp;
                current = temp;
            }
        }

        if (tenbit > 0) {
            current.next = new ListNode(tenbit);
        }

        return head;
    }
}
------------------------------------------------------------
Java
Accepted.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        int sum = 0;

        while ((l1 != null) || (l2 != null) || (sum > 0)) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            ListNode temp = new ListNode(sum % 10);
            sum /= 10;

            if (head == null) {
                head = current = temp;
            } else {
                current.next = temp;
                current = temp;
            }
        }

        return head;
    }
}
------------------------------------------------------------
Python
Accepted.

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        head = None
        current = None
        sum = 0
        
        while l1 or l2 or (sum > 0):
            if (l1 is not None):
                sum += l1.val
                l1 = l1.next
            if (l2 is not None):
                sum += l2.val
                l2 = l2.next

            temp = ListNode(int(sum % 10))
            sum = int(sum / 10)

            if (head is None):
                head = current = temp
            else:
                current.next = temp
                current = temp

        return head
------------------------------------------------------------
C++

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* head = NULL;
        ListNode* current = NULL;
        int sum = 0;

        while ((l1 != NULL) || (l2 != NULL) || (sum > 0)) {
            if (l1 != NULL) {
                sum += l1->val;
                l1 = l1->next;
            }
            if (l2 != NULL) {
                sum += l2->val;
                l2 = l2->next;
            }

            ListNode* temp = new ListNode(sum % 10);
            sum /= 10;

            if (head == NULL) {
                head = current = temp;
            } else {
                current->next = temp;
                current = temp;
            }
        }

        return head;
    }
};
================================================================================
Other solutions
------------------------------------------------------------
================================================================================
