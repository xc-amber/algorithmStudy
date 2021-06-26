import java.util.Deque;
import java.util.LinkedList;

/*
* 方法一：计算链表长度，正序删除
* 计算链表总的节点数count，删除倒数第n个节点就是删除整数第count - n + 1个节点
* 时间复杂度分析：遍历一次链表O(count)，在遍历找到需要删除的节点O(count - n);整体时间复杂度O(2*count - n)，即为O(count)
* 空间复杂度分析：O(1)
* 代码如下：
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
*
* 方法2：栈(看了题解的)
* 我们也可以在遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则，我们弹出栈的第n个节点就是需要删除的节点，
* 并且目前栈顶的节点就是待删除节点的前驱节点
* 时间复杂度分析：遍历一次链表O(count)，找到需要删除的节点O(n);整体时间复杂度O(count + n)，即为O(count)
* 空间复杂度分析：O(1)
* 代码如下：
public class Solution19 {
    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode temp = pre;
        Deque<ListNode> deque = new LinkedList<>();
//        将链表节点依次压入栈中
        while (temp != null){
            deque.push(temp);
            temp = temp.next;
        }
//      从栈顶开始取出n个节点
        for (int i = 0; i < n; i++) {
            deque.pop();
        }
//        此时栈顶的节点即为要删除的节点的前驱节点
        ListNode ans = deque.peek();
        ans.next = ans.next.next;
        return pre.next;
    }
}
*
* 方法3：快慢指针
* 定义left，right两个指针
* 将right指针指向超前left指针n个位置
* 当right指针指向链表末尾时，left指针正好指向“要删除节点”的前驱节点位置
* 时间复杂度分析：遍历一次链表O(count)
* 空间复杂度分析：O(1)
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
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode right = head;
        ListNode left = pre;
//        将right指针指向超前left指针n个位置
        for (int i = 1; i < n; i++) {
            right = right.next;
        }
//        当right指针指向链表末尾时，left指针正好指向“要删除节点”的前驱节点位置
        while (right.next != null){
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return pre.next;
    }
}
