/*
* 方法1：迭代
* 思路见注释
* 时间复杂度：O(n)，其中 n 是链表的长度
* 空间复杂度：O(1)。
* */
public class Solution206 {
    public ListNode reverseList(ListNode head) {
//        dummy节点为最前面的节点初始化为空
        ListNode dummy = null;
//        pre节点指向头节点，在循环体内第一次循环就将头结点的next指向null
        ListNode pre = head;
        while (pre != null){
//            cur指向当前pre所在的节点
            ListNode cur = pre;
//            pre往后遍历
            pre = pre.next;
//            cur节点的next指向前一个节点
            cur.next = dummy;
//            dummy节点往后移
            dummy = cur;
        }
        return dummy;
    }
      public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
