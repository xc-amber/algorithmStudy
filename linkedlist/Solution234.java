import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
/*
* 方法1：遍历+栈
* 遍历节点将值转化为字符串，并把值记录下来同时存入栈中
* 再把栈顶的元素依次取出转为字符串
* 判断两个字符串是否一致
*
* */
public class Solution234 {
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = head;
        StringBuilder sb1 = new StringBuilder();
        Deque<Integer> deque = new LinkedList<>();
        while (dummy != null){
            sb1.append(dummy.val);
            deque.offerFirst(dummy.val);
            dummy = dummy.next;
        }
        StringBuilder sb2 = new StringBuilder();
        while (!deque.isEmpty()){
            sb2.append(deque.pollFirst());
        }
        return sb1.toString().equals(sb2.toString());
    }
      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
