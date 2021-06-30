import java.util.LinkedList;
import java.util.Queue;
/*
* 方法1：BFS
* 先想如何把二叉树序列化成字符串，遍历二叉树的方法有前序、中序、后序、层序（BFS）遍历
* 根据题目例子可以看出是层序遍历，那么我们解法也选择层序遍历（使用BFS框架就行）；反序列化具体思路见注释
* 时间复杂度分析： O(n),n为树节点的个数
* 空间复杂度分析：O(n)
* */
public class Solution297 {
    public String serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                TreeNode node = queue.poll();
                if(node == null){
                    sb.append("null,");
                }else {
                    sb.append(node.val).append(",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
//        删除最后一个逗号
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")){
            return null;
        }
//        先将字符串按“,”分割成数组
        String[] str = data.split(",");
//        建立treeNodes数组，数组中存放值为字符串相应值得节点
        TreeNode[] treeNodes = new TreeNode[str.length];
        for (int i = 0; i < str.length; i++)
        {
            if(!str[i].equals("null")){
                treeNodes[i] = new TreeNode(Integer.parseInt(str[i]));
            }
        }
/*        对于treeNodes数组，如果数组中没有null节点，下标i所在节点和其左右节点满足以下规律
*        treeNodes[i].left =  treeNodes[i * 2 + 1];  treeNodes[i].right=  treeNodes[i * 2 + 2];
*        但当treeNodes数组中有null出现时，这样的规律被破坏，所以假设不存在null，那就需要下标i - null的个数；所以我们记录null出现的个数；即满足以下规律：
*        treeNodes[i].left =  treeNodes[(i - num) * 2 + 1];  treeNodes[i].right =  treeNodes[(i - num) * 2 + 2];
*
*/
        int num = 0;  //记录字符串中null出现的次数
//        遍历数组，进行root和root.left,root.right的关系赋值；
        for (int i = 0; i < treeNodes.length; i++)
        {
            if(treeNodes[i] != null){
                TreeNode root = treeNodes[i];
                if((i - num) * 2 + 1 < treeNodes.length){
                    root.left = treeNodes[(i - num) * 2 + 1];
                }
                if((i - num) * 2 + 2 < treeNodes.length){
                    root.right = treeNodes[(i - num)  * 2 + 2];
                }
            }else {
                num++;
            }
        }
        return treeNodes[0];
    }
      public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
}
