/*
* 方法1：迭代
* 判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，
* 当一个节点被添加到结果里之后，将对应链表中的节点向后移一位
* 时间复杂度分析：O(n+m)，其中 n 和 m 分别为两个链表的长度
* 空间复杂度分析：O(1)
* 代码如下：
* public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode temp = pre;
        while (l1 != null || l2 != null){
            if(l1 != null && l2 != null){
                if(l1.val < l2.val){
                    temp.next = l1;
                    l1 = l1.next;
                }else{
                    temp.next = l2;
                    l2 = l2.next;
                }
            }else if(l1 == null){
                temp.next = l2;
                l2 = l2.next;
            }else if(l2 == null){
                temp.next = l1;
                l1 = l1.next;
            }
            temp = temp.next;
        }

        temp.next = null;
        return pre.next;
    }
      public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
*
* 对上述代码进行优化，当有一个链表为空时，我们直接将链表末尾指向未合并完的链表即可
* 代码如下：
* public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode temp = pre;
        while (l1 != null && l2 != null){
                if(l1.val <= l2.val){
                    temp.next = l1;
                    l1 = l1.next;
                }else{
                    temp.next = l2;
                    l2 = l2.next;
                }
                temp = temp.next;
            }
            temp.next = l1 == null ? l2 : l1;
        return pre.next;
    }
      public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
* */
public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode temp = pre;
        while (l1 != null && l2 != null){
                if(l1.val <= l2.val){
                    temp.next = l1;
                    l1 = l1.next;
                }else{
                    temp.next = l2;
                    l2 = l2.next;
                }
                temp = temp.next;
            }
            temp.next = l1 == null ? l2 : l1;
        return pre.next;
    }
      public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
