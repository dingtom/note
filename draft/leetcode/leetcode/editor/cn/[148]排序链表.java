//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
// 示例 1：
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
// 示例 2： 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
// 示例 3：
//输入：head = []
//输出：[]
//
// 提示： 
// 链表中节点的数目在范围 [0, 5 * 104] 内
// -105 <= Node.val <= 105 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// Related Topics 链表 双指针 分治 排序 归并排序 
// 👍 1434 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 时间复杂度是 O(nlogn) 的排序算法包括归并排序、堆排序和快速排序（快速排序的最差时间复杂度是 O(n^2)，
     * 其中最适合链表的排序算法是归并排序。
     * 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，
     * 自顶向下归并排序的空间复杂度是 O(logn)。
     * 如果要达到 O(1) 的空间复杂度，则需要使用自底向上的实现方式。
     *
     */
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
