/*
* 方法1：
* 因为题目的方法签名中没有给head节点，所以我们没法用辅助指针来解题；
* 所以只能根据要删除的节点来想
* 假设原来的链表为1->2->3->4，现在需要删除2；那么最容易想到的是将1->3,但是本道题我们无法访问1；所以我们将2修改为3就变成1->3->3->4,
* 再将需要删除的节点next指向4，就变成了1->3->4
* 时间复杂度分析：O(1)
* 空间复杂度分析：O(1)
* */
public class Solution237 {
    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
