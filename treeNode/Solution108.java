
/*
* 方法1：写几个高度平衡 二叉树可以发现规律，数组中间的树为根节点
* 数组左边的段全为根的左子树
* 数组右边的段全为根的右子树，所以递归
* 时间复杂度分析：O(n)，其中 n 是数组的长度。每个数字遍历一次。
* 空间复杂度：O(log n)，其中 n 是数组的长度。空间复杂度主要取决于递归栈的深度，递归栈的深度是 O(logn)，每次递归是除以2；

* */
public class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return mySortedArrayToBST(nums, 0, nums.length - 1);
    }
    public TreeNode mySortedArrayToBST(int[] nums, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (end + start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = mySortedArrayToBST(nums, start, mid - 1);
        root.right = mySortedArrayToBST(nums, mid + 1, end);
        return root;
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
