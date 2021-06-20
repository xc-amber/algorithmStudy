import java.util.HashSet;
/*
* 方法1：HashSet
* 每一行都新建一个HashSet；9行可以汇总成一个长度为9的数组row[9]
* 每一列都新建一个HashSet；9列可以汇总成一个长度为9的数组column[9]
* 每一个小正方形都新建一个HashSet；9个小正方形可以汇总成一个长度为9的数组box[9]
* 然后开始遍历数组，当元素不为'.'时，我们需要将元素根据行列记录到对应的HashSet中，当添加返回false时，证明有重复，数独无效；
* 行列的HashSet记录简单，我们可以根据i和j直接找到row[i]和column[j];
* box的index怎么找是难点；这一点我参考了题解：boxIndex = (i / 3 ) * 3 + j / 3;
* 时间复杂度分析：遍历了9*9的数组，O(81) = O(1);
* 空间复杂度分析：row[9]，column[9]，box[9]，有效数独时，每个HashSet长度为9；整体空间复杂度O(1)
* */
public class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        HashSet[] row = new HashSet[9];
        HashSet[] column = new HashSet[9];
        HashSet[] box = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            row[i] = new HashSet<Character>();
            column[i] = new HashSet<Character>();
            box[i] = new HashSet<Character>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if(c != '.'){
                   int boxIndex = (i / 3 ) * 3 + j / 3;
                   if(!row[i].add(c)){
                       return false;
                   }
                   if(!column[j].add(c)){
                       return false;
                   }
                   if(!box[boxIndex].add(c)){
                       return false;
                   }
                }
            }
        }
        return true;
    }
}
