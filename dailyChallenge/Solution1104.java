import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
* 方法：数学找规律
* 1、数字1~n按二叉树之字形排序，这种在二维数组中很常见，所以可以联想到找label所在的行row和列column
* 2、假设根节点所在的行数为1，那么第n行的最大值就是2的n次方-1；第n行的最小值就是2的n次方；第n行的元素个数是2的n次方
* 3、根据以上的规律加上行数的奇偶性可以快速找到label所在的行row和列column；
* 4、接下来就是寻找label的parent；label的parent的位置也很好找，label的parent必然在第row - 1行，lable / 2 列；找到后加入结果列表res
* 5、再接着往上找parent的parent直到根节点，同样符合第四步的规律
* 6、最后一步将res反转顺序就得到了最终结果
* 时间复杂度分析：找到label所在的行需要O(log2 label)；最后向上找所有的parent是O(row)，row其实就等于(log2 label)，所以最终复杂度为O(log2 label)；
* 空间复杂度分析：不算结果res，O(1)
* */
public class Solution1104 {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> res = new ArrayList<>();
        int row = 1;
        while (Math.pow(2, row) - 1 < label){
            row++;
        }
        int column = 0;
        if(row % 2 == 0){
            column = ((int) Math.pow(2, row)) - 1 - label;
        }else {
            column = label - ((int) Math.pow(2, row - 1));
        }
        while (row >= 1){
            int num = get(row, column);
            res.add(num);
            row--;
            column /= 2;
        }
        Collections.reverse(res);
        return res;
    }
    public int get(int n, int i){
        if(n % 2 == 1){
            return ((int) Math.pow(2, n - 1)) + i;
        }else {
            return ((int) Math.pow(2, n)) - 1 - i;
        }
    }
}
