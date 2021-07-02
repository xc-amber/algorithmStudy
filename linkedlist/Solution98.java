
/*
*方法1：递归
* 二叉搜索树的特点就是左节点的值小于根节点小于右节点
* 但我们不能简单的判断root.left的值<root的值<root.right的值，然后进行递归
* 这样的方法不好和原始root的值比较
* 所以借助上届、下届来比较
* 具体思路见代码注释
* 时间复杂度分析：遍历树的所有节点O(n)，n为树节点的个数
* 空间复杂度分析：O(k)，递归过程每次递归分配栈空间，递归次数为树的高度，k为树的层数
* 代码如下：
* public class Solution98 {
    public boolean isValidBST(TreeNode root) {
//        初始的上届定位无穷大，下届为无穷小，定义为long的原因为Treenode的值为int，如果某个节点的值刚好为int的最大或最小值，就会出现错误
        return myIsValid(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    public boolean myIsValid(TreeNode root, long max, long min){
//        当树为空时返回true
        if(root == null){
            return true;
        }
//        当遍历到的节点的值不在上下边界内，返回false
        if(root.val <= min || root.val >= max){
            return false;
        }
//        然后递归该节点左子树，左子树的上届就是该节点的值，下届就是无穷小；递归该节点右子树，右子树的下届就是该节点的值，上届为无穷大
        return myIsValid(root.left, root.val, min) && myIsValid(root.right, max, root.val);
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
* 方法2：中序遍历
* 二叉搜索树的特性直接满足中序遍历从小到大
* 所以直接写中序遍历的模板
* 时间复杂度分析：O(n),n为树节点的个数
* 空间复杂度分析:O(n),deque存储n个树节点
* 代码如下：
* public class Solution98 {
    public boolean isValidBST(TreeNode root) {
        long min = Long.MIN_VALUE;
        if(root == null){
            return true;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        while (root != null || !deque.isEmpty()){
            while (root != null){
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            if(root.val <= min){
                return false;
            }
            min = root.val;
            root = root.right;
        }
        return true;
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
* 方法3：递归+中序遍历
* 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false
* 时间复杂度分析：遍历树的所有节点O(n)，n为树节点的个数
* 空间复杂度分析：O(k)，递归过程每次递归分配栈空间，递归次数为树的高度，k为树的层数
* 代码如下：
* public class Solution98 {
    long min = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        if(!isValidBST(root.left)){
            return false;
        }
        if(root.val <= min){
            return false;
        }
        min = root.val;
        return isValidBST(root.right);
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
*  */

import java.util.Deque;
import java.util.LinkedList;

public class Solution98 {
    long min = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        if(!isValidBST(root.left)){
            return false;
        }
        if(root.val <= min){
            return false;
        }
        min = root.val;
        return isValidBST(root.right);
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
