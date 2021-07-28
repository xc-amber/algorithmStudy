import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 方法1：DFS+哈希表（参考官解）
* 若将 target 当作树的根结点，我们就能从 target 出发，使用深度优先搜索去寻找与 target 距离为 k 的所有结点，即深度为 k 的所有结点。

由于输入的二叉树没有记录父结点，为此，我们从根结点 root 出发，使用深度优先搜索遍历整棵树，同时用一个哈希表记录每个结点的父结点。

然后从 target 出发，使用深度优先搜索遍历整棵树，除了搜索左右儿子外，还可以顺着父结点向上搜索。

代码实现时，由于每个结点值都是唯一的，哈希表的键可以用结点值代替。此外，为避免在深度优先搜索时重复访问结点，
* 递归时额外传入来源结点 from，在递归前比较目标结点是否与来源结点相同，不同的情况下才进行递归。(重点理解)

时间复杂度：O(n)，其中 n 是二叉树的结点个数。需要执行两次深度优先搜索，每次的时间复杂度均为 O(n)

空间复杂度：O(n)。记录父节点需要 O(n)的空间，深度优先搜索需要 O(n)的栈空间。


* */

public class Solution863 {
    Map<Integer, TreeNode> parents = new HashMap<Integer, TreeNode>();
    List<Integer> ans = new ArrayList<Integer>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 从 root 出发 DFS，记录每个结点的父结点
        findParents(root);

        // 从 target 出发 DFS，寻找所有深度为 k 的结点
        findAns(target, null, 0, k);

        return ans;
    }

    public void findParents(TreeNode root){
        if(root == null){
            return;
        }
        if(root.left != null){
            parents.put(root.left.val, root);
            findParents(root.left);
        }
        if(root.right != null){
            parents.put(root.right.val, root);
            findParents(root.right);
        }
    }

    public void findAns(TreeNode node, TreeNode from, int depth, int k){
        if(node == null){
            return;
        }
        if(depth == k){
            ans.add(node.val);
            return;
        }
        if(node.left != from){
            findAns(node.left, node, depth + 1, k);
        }
        if(node.right != from){
            findAns(node.right, node, depth + 1, k);
        }
        if(parents.get(node.val) != from){
            findAns(parents.get(node.val), node, depth + 1, k);
        }
    }



      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
