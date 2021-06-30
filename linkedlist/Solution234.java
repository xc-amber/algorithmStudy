import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
/*
* 方法1：遍历+栈
* 遍历节点将值转化为字符串，并把值记录下来同时存入栈中
* 再把栈顶的元素依次取出转为字符串
* 判断两个字符串是否一致
* 时间复杂度分析：遍历一次链表O(n)，n为链表节点总数；栈的操作都是O(1)；把栈顶的元素依次取出转为字符串O(n)；所以总复杂度O(n)
* 空间复杂度O(n)
* 代码如下：
* public class Solution234 {
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
*
* 方法2：遍历+reverse
* 遍历节点将值转化为字符串，直接reverse字符串是否和原来一致
* 代码如下：
* class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = head;
        StringBuilder sb = new StringBuilder();
        while (dummy != null){
            sb.append(dummy.val);
            dummy = dummy.next;
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}
*
* 方法3：遍历+二分
* 遍历节点将值转化为字符串
* 再对字符串进行二分判断左右是否相等
* 时间复杂度分析：O(n + n / 2)，即为O(n)
* 空间复杂度O(n)
* 代码如下：
* public class Solution234 {
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = head;
        StringBuilder sb = new StringBuilder();
        while (dummy != null){
            sb.append(dummy.val);
            dummy = dummy.next;
        }
        int left = 0;
        int right = sb.length() - 1;
        while (left < right){
            if(sb.charAt(left) != sb.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
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
public class Solution234 {
    public boolean isPalindrome(ListNode head) {
        ListNode dummy = head;
        StringBuilder sb = new StringBuilder();
        while (dummy != null){
            sb.append(dummy.val);
            dummy = dummy.next;
        }
        int left = 0;
        int right = sb.length() - 1;
        while (left < right){
            if(sb.charAt(left) != sb.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
