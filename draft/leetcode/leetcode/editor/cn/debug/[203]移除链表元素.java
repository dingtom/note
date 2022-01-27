//给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足
// Node.val == val 的节点，并返回 新的头节点 。
//
//
// 示例 1：
//输入：head = [1,2,6,3,4,5,6], val = 6
//输出：[1,2,3,4,5]
// 示例 2：
//输入：head = [], val = 1
//输出：[]
// 示例 3：
//输入：head = [7,7,7,7], val = 7
//输出：[]
//
// 提示：
// 列表中的节点数目在范围 [0, 104] 内
// 1 <= Node.val <= 50
// 0 <= val <= 50
//
// Related Topics 递归 链表


//leetcode submit region begin(Prohibit modification and deletion)
// Definition for singly-linked list.

import java.util.Collection;
import java.util.LinkedList;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

class Solution {
    int ptr;
    p

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        ptr = 0;
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                String digits = getDigits(s);
                stack.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                stack.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stack.peekLast())) {
                    sub.addLast(stack.removeLast());
                }
                Collection.reverse(sub);
                stack.removeLast();
                int time = Integer.parseInt(stack.removeLast());
                StringBuffer sb = new StringBuffer();
                String segment = getString(sub);
                while (time-- > 0) {
                    sb.append(segment);
                }
            }
        }

    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }
    public String getString(LinkedList<String> ls) {
        StringBuffer ret = new StringBuffer();
        for (String s : ls) {
            ret.append(s);
        }
        return ret.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
