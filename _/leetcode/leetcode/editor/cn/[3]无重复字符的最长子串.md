//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
// 示例 1:
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2: 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3: 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// 提示：
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 6996 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        /**
         * 使用双指针做滑动窗口，使用哈希表记录出现过的字符
         */
        Set<Character> recorder = new HashSet<Character>();
        int len = s.length();
        // right 滑动窗口右侧,初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1, maxLen = 0;
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                recorder.remove(s.charAt(i - 1));
            }
            while (right + 1 < len && !recorder.contains(s.charAt(right + 1))) {
                // 不断地移动右指针直到遇到重复字符
                recorder.add(s.charAt(right + 1));
                right++;
            }
            // 滑动窗口内为无重复字符子串
            maxLen = Math.max(maxLen, right - i + 1);
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
