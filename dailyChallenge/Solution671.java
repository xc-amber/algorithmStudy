import java.util.*;

/*
* 方法1：DFS深度优先/BFS广度优先
* DFS遍历整个树，将每个节点值记录在list中，最后找第二小的值
* 时间复杂度分析：O(k),k为树的深度，排序O(nlogn),n为节点个数，找第二小的值O(n);
* 空间复杂度分析:O(n)
* 代码如下：
* public class Solution671 {
    public int findSecondMinimumValue(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        Collections.sort(list);
        for (Integer num : list) {
            if(num > root.val){
                return num;
            }
        }
        return -1;
    }
    public void dfs(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
*
* BFS代码如下：
* 其中求第二小的值换了种方法，没必要用list来存储，实时比较更新就行
public class Solution671 {
    public int findSecondMinimumValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int res = Integer.MAX_VALUE; //记录第二小的值
        Boolean flag = true;  //flag的作用就是标识这个res没有被更改过
        if (root == null){
            return -1;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.val > root.val){
                    res = Math.min(res, node.val); //如果节点值>根节点值，维护第二小的值为最小值
                    flag = false;  //当res被更改后，flag=false标识res变化过，主要防止res实际就为Integer.MAX_VALUE，返回时不好判断
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return res == Integer.MAX_VALUE && flag ? -1 : res;
    }
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
* */

public class Solution671 {
    public int findSecondMinimumValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int res = Integer.MAX_VALUE; //记录第二小的值
        Boolean flag = true;  //flag的作用就是标识这个res没有被更改过
        if (root == null){
            return -1;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.val > root.val){
                    res = Math.min(res, node.val); //如果节点值>根节点值，维护第二小的值为最小值
                    flag = false;  //当res被更改后，flag=false标识res变化过，主要防止res实际就为Integer.MAX_VALUE，返回时不好判断
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return res == Integer.MAX_VALUE && flag ? -1 : res;
    }
     public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
