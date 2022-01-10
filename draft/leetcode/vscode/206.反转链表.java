/*
 * @Author: your name
 * @Date: 2022-01-10 10:41:48
 * @LastEditTime: 2022-01-10 12:38:33
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * 
 * @FilePath: \vscode\206.反转链表.java
 */
/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 */

// @lc code=start

// Definition for singly-linked list.

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


 
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode next = null; 
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    // public ListNode reverseList(ListNode head) {
    //     if (head == null || head.next == null) return head;
    //     ListNode newHead = reverseList(head.next);
    //     head.next.next = head;
    //     head.next = null;
    //     return newHead;
    // }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix1 = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix2 = {};
        char[][] matrix3 = {{'0'}};
        char[][] matrix4 = {{'1'}};

        System.out.println(solution.maximalRectangle(matrix1));
        System.out.println(solution.maximalRectangle(matrix2));
        System.out.println(solution.maximalRectangle(matrix3));
        System.out.println(solution.maximalRectangle(matrix4));
    }
}

// @lc code=end

