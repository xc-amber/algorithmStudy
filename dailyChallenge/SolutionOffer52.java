import java.util.HashSet;

/*
* 方法1：SET+遍历
* 最简单想到的就是先遍历headA，将headA中节点存入set中，再遍历headB将headB中的节点存入set，如果有重复的节点，那么就是要求的结果
* 这样遍历的话需要先遍历完一个链表，再遍历另外一个链表；时间复杂度为O(m + n)，m，n分别为两个链表的长度
* 但是如果两个链表同时遍历，也在遇到重复节点的时候能找到要求的结果；这样的话虽然从时间复杂度上来分析，只需要遍历O(k)，
* k为距离第一个公共节点较远的链表  的头节点到公共节点的长度，虽然最坏情况下时间复杂度也是O(m + n)，但一般能节省运行时间
* 空间复杂度分析：O(m + n)
* 代码如下：
* public class SolutionOffer52 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode dummy = headA;
        ListNode cur = headB;
        HashSet<ListNode> set = new HashSet<>();
        while (dummy != null || cur != null){
            if(dummy == null){
                if(!set.add(cur)){
                    return cur;
                }
                cur = cur.next;
            }else if(cur == null){
                if(!set.add(dummy)){
                    return dummy;
                }
                dummy = dummy.next;
            }else{
                if(!set.add(dummy)){
                    return dummy;
                }
                if(!set.add(cur)){
                    return cur;
                }
                dummy = dummy.next;
                cur = cur.next;
            }
        }
        return null;
    }
      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}
*
* 方法2：双指针
* 找到公共节点，想到的第一方法是类似求链表中是否有环的解法：快慢指针，快指针每次走两步，慢指针每次走一步，最后总会相遇
* 但在这道题里怎么利用双指针呢，通过公共点后都是一样的，那么公共点后的长度也一样；想到如果两个链表长度相等的话，那么我们两个链表从头往尾每次遍历一步，判断是否相等即可
* 这道题里两个链表长度不一定相等；那么可以想办法变为相等；
* 先遍历两个链表，计算出两个链表的长度差值num；较长的链表的指针从头结点往右先移动num个位置；再和较短的节点从头结点开始一起往右遍历，每次判断是否相等即可
* 时间复杂度分析O(m + n)
* 空间复杂度O(1)
* */

public class SolutionOffer52 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode dummy1 = headA;
        ListNode dummy2 = headA;
        ListNode cur1 = headB;
        ListNode cur2 = headB;
        int lengthA = 0;
        int lengthB = 0;
        while (dummy1 != null){
            lengthA++;
            dummy1 = dummy1.next;
        }
        while (cur1 != null){
            lengthB++;
            cur1 = cur1.next;
        }
        int num = lengthA - lengthB;
        if(num > 0){
            while (num-- > 0){
                dummy2 = dummy2.next;
            }
        }else{
            while (num++ < 0){
                cur2 = cur2.next;
            }
        }
        while (dummy2 != null){
            if(dummy2 == cur2){
                return dummy2;
            }else {
                dummy2 = dummy2.next;
                cur2 = cur2.next;
            }
        }
        return null;
    }
      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}
