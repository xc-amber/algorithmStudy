/*
* 方法1：快慢指针
* 判断链表是否存在环，直接使用快慢指针；
* 慢指针指向head，快指针指向head.next；慢指针一步一步走，快指针两步两步走；如果有环必然相遇
* 时间复杂度分析：遍历一次链表O(n)
* 空间复杂度分析：O(1)
*
*/
public class Solution141 {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
      class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
   }
}
