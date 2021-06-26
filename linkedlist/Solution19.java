/*
* 方法一：计算链表长度，正序删除
* 计算链表总的节点数，删除倒数第n个节点就是删除整数第count - n + 1个节点
*
* */
public class Solution19 {
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre1 = head;
        int count = 0;
//        计算链表总的节点数
        while (pre1 != null){
            count++;
            pre1 = pre1.next;
        }
        ListNode pre2 = new ListNode();
        pre2.next = head;
//        删除倒数第n个节点就是删除整数第count - n + 1个节点
        int target = count - n + 1;
//        记录节点是第几个节点
        int num = 0;
//        当节点是“要删除的节点”的前一个位置时跳出循环
        while (num != target - 1) {
            pre2 = pre2.next;
            num++;
        }
//        如果“要删除的节点”是head的话，head变成head.next
        if(pre2.next == head){
            head = head.next;
        }
//        “要删除的节点”的前一个位置的next指针直接指向“要删除的节点”的后一个位置
        pre2.next = pre2.next.next;
        return head;
    }
}
