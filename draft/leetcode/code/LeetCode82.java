/*
 * @lc app=leetcode.cn id=82 lang=java
 *
 * [82] 删除排序链表中的重复元素 II
 */

/* 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
示例 1：
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
示例 2：
输入：head = [1,1,1,2,3]
输出：[2,3]*/

// @lc code=start
// *
//  * Definition for singly-linked list.

//public class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public void printList(){
        ListNode tmp=this;
        //遍历输出 next的val
        while(tmp!=null){
            System.out.print(tmp.val);
            if(tmp.next!=null) {
                System.out.print("->");
            }
            tmp=tmp.next;
        }
        System.out.println();
    }
}

public class LeetCode82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        // 链表重复节点的是连续的
        // head 可能重复被删，用dummy指向
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while(cur.next != null && cur.next.next !=null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        LeetCode82 solution = new LeetCode82();
        int[] nums1 = {2,5,6,0,0,1,2}, nums2 = {2,5,6,0,0,1,2};
        int target1 = 3, target2 = 0;
        System.out.println(solution.search(nums1, target1));
        System.out.println(solution.search(nums2, target2));
    }
}
// @lc code=end

