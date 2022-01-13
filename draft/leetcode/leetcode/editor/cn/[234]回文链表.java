//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
//
// 示例 1： 
//输入：head = [1,2,2,1]
//输出：true
// 示例 2：
//输入：head = [1,2]
//输出：false
//
// 提示： 
// 链表中节点数目在范围[1, 105] 内
// 0 <= Node.val <= 9 
// 
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 栈 递归 链表 双指针 
// 👍 1226 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

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
    public boolean isPalindrome(ListNode head) {
//        方法一：将值复制到数组中后用双指针法
//        List<Integer> vals = new ArrayList<Integer>();
//        ListNode cur = head;
//        while (cur != null) {
//            vals.add(cur.val);
//            cur = cur.next;
//        }
//        int left = 0;
//        int right = vals.size() - 1;
//        while (left < right) {
//            if (!vals.get(left).equals(vals.get(right))) return false;
//            ++left;
//            --right;
//        }
//        return true;

//        方法二：递归

    }
}
//leetcode submit region end(Prohibit modification and deletion)
