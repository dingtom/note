```
string.capitalize() 第一个字符大写
string.count(str, beg=0, end=len(string)) 返回 str 在 string 里面出现的次数，beg 、 end 指定范围
string.endswith(obj, beg=0, end=len(string)) 检查字符串是否以 obj 结束
string.find(str, beg=0, end=len(string)) 返回str开始的索引值，否则返回-1
string.index(str, beg=0, end=len(string)) 跟find()方法一样，只不过如果str不在 string中会报一个异常.
string.isalnum() string 至少有一个字符并且所有字符都是字母或数字则返回 True
string.isalpha()  string 至少有一个字符并且所有字符都是字母则返回 True
string.isdecimal()  string 只包含十进制数字则返回 True 
string.isdigit()  string 只包含数字则返回 True
string.islower() string 都是小写，则返回 True
string.isnumeric()  string 中只包含数字字符，则返回 Tru
string.isspace()  string 中只包含空格，则返回 True
string.istitle()  string 是标题化的(见 title())则返回 True
string.isupper() string 都是大写，则返回 True
string.join(seq) 以 string 作为分隔符，将 seq 中所有的元素(的字符串表示)合并为一个新的字符串
string.lower() 转换 string 中所有大写字符为小写.
string.lstrip() 截掉 string 左边的空格
max(str) 返回字符串 str 中最大的字母。
min(str) 返回字符串 str 中最小的字母。
string.replace(str1, str2, num=string.count(str1)) 把 string 中的 str1 替换成 str2,替换不超过 num 次.
string.split(str="", num=string.count(str)) 以 str 为分隔符切片 string，仅分隔 num+ 个子字符串
string.startswith(obj, beg=0,end=len(string)) 检查字符串是否是以 obj 开头，是则返回 True
string.strip([obj]) 在 string 上执行 lstrip()和 rstrip()
string.swapcase() 翻转 string 中的大小写
string.title() 返回"标题化"的 string,就是说所有单词都是以大写开始，其余字母均为小写(见 istitle())
string.translate(str, del="") 根据 str 给出的表(包含 256 个字符)转换 string 的字符,要过滤掉的字符放到 del 参数中
string.upper() 转换 string 中的小写字母为大写
```
## **字符串**

*   [125\. 验证回文串](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/valid-palindrome/)

>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。
输入: "A man, a plan, a canal: Panama"
输出: true

逆字符串
```
class Solution:
    def isPalindrome(self, s: str) -> bool:
        alnum = ''.join(w.lower() for w in s if w.isalnum())
        return alnum == alnum[::-1]
```

使用双指针。初始时，左右指针分别指向两侧，随后我们不断地将这两个指针相向移动，每次移动一步，并判断这两个指针指向的字符是否相同。当这两个指针相遇时，就说明是回文串。
```
class Solution:
    def isPalindrome(self, s: str) -> bool:
        pre = 0
        nex = len(s) - 1
        while pre < nex:
            # 找到下一个字母或数字
            while pre < nex and not s[pre].isalnum():  # 忽略非字母数字
                pre += 1
            while pre < nex and  not s[nex].isalnum():  # 忽略非字母或数字
                nex -= 1
            # 判断
            if pre < nex:
                if s[pre].lower() != s[nex].lower():  # 忽略大小写
                    return False
                pre += 1
                nex -= 1
        return True
```

*   [131\. 分割回文串](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/palindrome-partitioning/)
>给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
返回 s 所有可能的分割方案。
示例:
输入: "aab"
输出:
[ ["aa","b"],  ["a","a","b"] ]

![](https://upload-images.jianshu.io/upload_images/18339009-9f5d4cbf37e76e7d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)







*   [139\. 单词拆分](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/word-break/)



*   [140\. 单词拆分 II](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/word-break-ii/)



*   [208\. 实现 Trie (前缀树)](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/implement-trie-prefix-tree/)
>实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

Trie 是一颗非典型的多叉树模型
一般的多叉树的结点是这样的：
```
struct TreeNode {
    VALUETYPE value;    //结点值
    TreeNode* children[NUM];    //指向孩子结点
};
```
而 Trie 的结点是这样的(假设只包含'a'~'z'中的字符)：
```
struct TrieNode {
    bool isEnd; //该结点是否是一个串的结束
    TrieNode* next[26]; //字母映射表
};
```
这时字母映射表next 的妙用就体现了，TrieNode* next[26]中保存了对当前结点而言下一个可能出现的所有字符的链接，因此我们可以通过一个父结点来预知它所有子结点的值：



>示例:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true

>说明:
你可以假设所有的输入都是由小写字母 a-z 构成的。
保证所有输入均为非空字符串。



*   [212\. 单词搜索 II](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/word-search-ii/)
>给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

>输入: 
words = ["oath","pea","eat","rain"] 
board =
[ [**'o'**,**'a'**,'a','n'],
    ['e',    **'t'**,     **'a'**,  **'e'**],
    ['i',  **'h'**,  'k',  'r'],
    ['i',  'f',  'l',  'v']  ]
输出: ["eat","oath"]




```

```






*   [242\. 有效的字母异位词](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/valid-anagram/)

>给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
输入: s = "anagram", t = "nagaram"
输出: true

逐个去除b里的a中字符
```
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        s = list(s)
        t = list(t)
        for i in s:
            try:
                t.remove(i)
            except ValueError as e:
                return False
        return True
```
第一次循环哈希表记录，第二次循环删去哈希表记录，最后统计哈希表每个值是否都为0

```
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        word_count = {}
        # 建立哈希表
        for i in s:
            if i not in word_count:
                word_count[i] = 1
            else:
                word_count[i] += 1
        # 清空哈希表
        for i in t:
            if i in word_count:
                word_count[i] -= 1
            else:
                return False
        # all([]) ture any([]) false
        return not any(list(word_count.values()))  
```
*   [387\. 字符串中的第一个唯一字符](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/first-unique-character-in-a-string/)
>给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

哈希表记录每个字符出现次数
```
class Solution:
    def firstUniqChar(self, s: str) -> int:
        #  count = collections.Counter(s)
        word_count = {}
        # 建立哈希表
        for i in s:
            if i not in word_count:
                word_count[i] = 1
            else:
                word_count[i] +=1
         # 根据哈希表判断出现次数
        for index, word in enumerate(s):
            if word_count[word]  == 1:
                return index
        return -1
```


*   [344\. 反转字符串](https://link.zhihu.com/?target=https%3A//leetcode-cn.com/problems/reverse-string/)
>编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
```
# s[:]=s[::-1]
# 双指针
 l,r=0,len(s)-1
        while l<r:
            s[l],s[r]=s[r],s[l]
            l+=1
            r-=1
```

[3.无重复字符的最长子串长度](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)
>示例 1:
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

使用数组作为滑动窗口
```
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s: return 0
        substr = []
        max_len = 0
        for word in s:
            if word not in substr:
                # 扩展窗口
                substr.append(word)
            else:
                # 从窗口中移除重复字符及之前的字符串部分
                substr = substr[substr.index(word)+1:]
                # 扩展窗口
                substr.append(word)
            max_len = max(max_len, len(substr))
        return max_len
```
法1中容器的伸缩涉及内存分配,所以方法2换成位置指针省掉了内存分配

直观的滑动窗口方法需要维护数组的增删，实际上比较耗时。使用双指针（索引），记录滑动窗口起始和结束的索引值，可以减除数组增删操作，提高效率，使用指针位移以及从原数组中截取，代替原来的窗口元素增删操作
```
def lengthOfLongestSubstring(self, s: str) -> int:
        # 字符串为空则返回零
        if not s: return 0
        max_len = 0   
        left_index, right_index = 0, 0  # 双指针
        for word in s:
            # 如果字符不在滑动窗口中，则直接扩展窗口
            if word not in s[left_index:right_index]:
                # 右指针右移一位
                right_index += 1
            else:
                # 左指针右移 word在substr中的索引 位
                left_index += s[left_index:right_index].index(word) + 1
                # 右指针右移一位
                right_index += 1
            max_len = max(right_index - left_index, max_len)
        return max_len 
```





**Hash（字典），滑动窗口，双指针**
使用字典记录任意字符最近的索引值，**字典查询时间复杂度为O(1)，相比数组查询，效率更高**
该算法的难点在于理解word_index[word] > ignore_end_index如果不大于说明word已经被丢弃；大于说明word未被丢弃需要，更新ignore_end_index

```
    def lengthOfLongestSubstring(self, s: str) -> int:
        ignore_end_index = -1          # 指向子串左边一个字符，即丢弃的子串的尾部， 初始值为 -1，还没有开始移动
        max_len = 0          # 记录最大的长度
        word_index = {}          # 滑动窗口，任意字符最后出现位置的索引
        for index, word in enumerate(s): 
             # 如果 word出现过 且  最近一次出现的索引大于ignore_end，意味着需要丢弃这个词前面的部分
            # 如果不大于说明word已经被丢弃；大于说明word未被丢弃需要，更新ignore_end_index                   
            if word in word_index and word_index[word] > ignore_end_index:  
                ignore_end_index = word_index[word]  # 新的子串开始
                word_index[word] = index  # 更新word的索引
            else:
                # word未出现过
                word_index[word] = index  # 子串变长
                max_len = max(max_len, index - ignore_end_index)   # 更新最大长度
        return max_len
```
