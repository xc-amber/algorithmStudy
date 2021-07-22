import java.util.HashMap;
import java.util.Map;

/*
* 方法1：两个Map
* 1、遍历一次原链表，并把节点存入map1，key为原链表的Node节点，value为原链表的节点所处的位置
* 2、在步骤1遍历的时候，建立新的链表，并形成next指针关系，同时把新的节点存入map2，key为新链表的节点所处的位置，value为新链表的Node节点
* 3、再次遍历原链表，依次找出每个节点的random指针指向的节点的索引；对应新链表相同位置节点的random节点应该也指向同样的索引；所以map1和map2的key和value对应属性是相反的，为了方便查找
* 4、最后只要返回map2的第一个节点即可
* 时间复杂度分析：遍历两次原节点O(n)
* 空间复杂度分析：两个map,O(n)
* 代码如下：
* public class Solution138 {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Map<Node, Integer> map1 = new HashMap<>();
        Map<Integer, Node> map2 = new HashMap<>();
        Node dummy1 = head;
        int index1 = 1;
        int index2 = 1;
        Node pre1 = new Node(1);
        while (dummy1 != null){
            map1.put(dummy1, index1++);
            Node temp = new Node(dummy1.val);
            pre1.next = temp;
            pre1 = pre1.next;
            map2.put(index2++, temp);
            dummy1 = dummy1.next;
        }
        Node dummy2 = head;
        Node pre2 = map2.get(1);
        while (pre2 != null){
            int index = map1.getOrDefault(dummy2.random, 0);
            if(index == 0){
                pre2.random = null;
            }else {
                pre2.random = map2.get(index);
            }
            pre2 = pre2.next;
            dummy2 = dummy2.next;
        }
        return map2.get(1);
    }
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
*
* 方法2：也不叫方法2吧，就是优化方法1
* 1、方法1需要遍历两次原链表，是否有办法遍历一次就行
* 2、在第一次遍历的时候就去维护random指针的关系，把random指向的节点也可以先加入map
* 3、在后续遍历遇到已经在map中存在的节点，就证明这个节点肯定在之前是某个节点的random指向的，在之前就被添加了；所以直接跳过添加步骤即可，直接维护next指针和random指针的关系就行
* 时间复杂度分析：一次遍历原链表：O(n)
* 空间复杂度分析：一样是两个map：O(n)
*
* */

public class Solution138 {
    public Node copyRandomList(Node head) {
        if(head == null){
        return null;
        }
        Map<Node, Integer> map1 = new HashMap<>();
        Map<Integer, Node> map2 = new HashMap<>();
        Node dummy1 = head;
        int index1 = 1;
        int index2 = 1;
        Node pre1 = new Node(1);
        while (dummy1 != null){
            if(map1.containsKey(dummy1)){
                int index = map1.get(dummy1);
                pre1.next = map2.get(index);
            }else {
                map1.put(dummy1,index1++);
                Node temp = new Node(dummy1.val);
                pre1.next = temp;
                map2.put(index2++, temp);
            }
            Node cur = dummy1.random;
            if(cur == null){
                pre1.next.random = null;
            }else{
                if(map1.containsKey(cur)){
                    int index = map1.get(cur);
                    pre1.next.random = map2.get(index);
                }else{
                    map1.put(cur, index1++);
                    pre1.next.random = new Node(cur.val);
                    map2.put(index2++, pre1.next.random);
                }
            }
            pre1 = pre1.next;
            dummy1 = dummy1.next;
        }
        return map2.get(1);
    }
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
