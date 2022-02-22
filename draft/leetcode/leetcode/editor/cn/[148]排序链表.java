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
     * 方法一：自顶向下归并排序
     * 时间复杂度是 O(nlogn) 的排序算法包括归并排序、堆排序和快速排序（快速排序的最差时间复杂度是 O(n^2)，
     * 其中最适合链表的排序算法是归并排序。
     * 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，
     * 自顶向下归并排序的空间复杂度是 O(logn)。
     * 如果要达到 O(1) 的空间复杂度，则需要使用自底向上的实现方式。
     *
     * 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * 空间复杂度：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。
     */
//    public ListNode sortList(ListNode head, ListNode tail) {
//        if (head == null) {
//            return head;
//        }
//        if (head.next == tail) {
//            head.next = null;
//            return head;
//        }
//        ListNode slow = head, fast = head;
//        // 找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动 22 步，慢指针每次移动 11 步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
//        while (fast != tail) {
//            slow = slow.next;
//            fast = fast.next;
//            if (fast != tail) {
//                fast = fast.next;
//            }
//        }
//        ListNode mid = slow;
//        // 对两个子链表分别排序。
//        ListNode list1 = sortList(head, mid);
//        ListNode list2 = sortList(mid, tail);
//        ListNode sorted = merge(list1, list2);
//        return sorted;
//    }

    /**
     * 方法二：自底向上归并排序
     * 用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。
     * 每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于 subLength），
     * 按照每两个子链表一组进行合并，合并后即可得到若干个长度为 subLength×2 的有序子链表
     * （最后一个子链表的长度可以小于subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做法。
     */






//    将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;
        while(list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return dummyNode.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
