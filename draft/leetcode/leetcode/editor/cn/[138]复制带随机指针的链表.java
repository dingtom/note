//给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。 
// 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random
//指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。 
// 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random
//--> y 。 
//
// 返回复制链表的头节点。 
// 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
// val：一个表示 Node.val 的整数。
// random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为 null 。 
// 你的代码 只 接受原链表的头节点 head 作为传入参数。
//
// 示例 1：
//输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
// 示例 2：
//输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
//
// 示例 3： 
//输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
// 提示：
// 0 <= n <= 1000
// -104 <= Node.val <= 104 
// Node.random 为 null 或指向链表中的节点。 
// 
// Related Topics 哈希表 链表 
// 👍 829 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Node, Node> recorder = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        /**
         *
         * 回溯 + 哈希表
         *
         * 哈希表记录每一个节点对应新节点的创建情况。
         * 我们检查「当前节点的后继节点」和「当前节点的随机指针指向的节点」
         * 任何一个节点的新节点没有被创建，递归地进行创建。
         * 回溯到当前层时，我们即可完成当前节点的指针赋值。
         *
         * 注意一个节点可能被多个其他节点指向，为了防止重复拷贝，
         * 我们需要首先检查当前节点是否被拷贝过，
         * 如果已经拷贝过，我们可以直接从哈希表中取出拷贝后的节点的指针并返回即可。
         *
         * 时间复杂度：O(n)，其中 n 是链表的长度。对于每个节点，
         * 我们至多访问其「后继节点」和「随机指针指向的节点」各一次，均摊每个点至多被访问两次。
         * 空间复杂度：O(n)，其中 n 是链表的长度。为哈希表的空间开销。
         */
        if (head == null) {
            return null;
        }
        if (!recorder.containsKey(head)) {
            Node newHead = new Node(head.val);
            recorder.put(head, newHead);
            newHead.next = copyRandomList(head.next);
            newHead.random = copyRandomList(head.random);
        }
        return recorder.get(head);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
