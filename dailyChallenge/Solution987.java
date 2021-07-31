import java.util.*;
/*
* 方法1：DFS+Map+排序
* Map<Integer, List<TreeNode>> columnToNode，记录列数到节点列表的映射
* Map<TreeNode, Integer> nodeToRow，记录节点到该节点的行数的映射
* 使用DFS遍历二叉树，将节点记录在columnToNode中
* 对columnToNode的keySet进行排序，然后key从小到大也就是列数从左到右取出对应的节点列表
* 将每列的节点列表中的元素按行数在前的排在前，如果行数一样，节点值小的排在前，最后加入结果即可
* 时间复杂度分析: 遍历O(n)，n为节点个数，排序logn，所以整体是O(n*logn)
* 空间复杂度分析：两个map：O(n)
* */
public class Solution987 {
    Map<Integer, List<TreeNode>> columnToNode = new HashMap<>();
    Map<TreeNode, Integer> nodeToRow = new HashMap<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, 0);
        List<Integer> columnList = new ArrayList<>(columnToNode.keySet());
        Collections.sort(columnList);
        for (Integer column : columnList)
        {
            List<TreeNode> nodeList = columnToNode.get(column);
            nodeList.sort((o1, o2) -> {
                if (nodeToRow.get(o1).equals(nodeToRow.get(o2))) {
                    return o1.val - o2.val;
                } else {
                    return nodeToRow.get(o1) - nodeToRow.get(o2);
                }
            });
            List<Integer> temp = new ArrayList<>();
            for (TreeNode node : nodeList)
            {
                temp.add(node.val);
            }
            res.add(temp);
        }
        return res;
    }
    public void dfs(TreeNode node, int row, int column){
        if(node == null){
            return;
        }
        columnToNode.putIfAbsent(column, new ArrayList<>());
        columnToNode.get(column).add(node);
        nodeToRow.put(node, row);
        dfs(node.left, row + 1, column - 1);
        dfs(node.right, row + 1, column + 1);
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
