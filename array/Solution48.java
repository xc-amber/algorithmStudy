/*
* 方法1：原地修改
* 因为题目要求不能使用辅助数组进行旋转，所以只能硬着来修改
* 开始也没有头绪，再画了很多次图后，发现规律旋转后的第(i,j)位置一定满足如下规律旋转后matrix[i][j] = 旋转前的matrix[n - 1 - j][i]
* 那么开始就按这个规律去改值，因为第一次matrix[i][j]会被修改，所以用temp去记录matrix[i][j]；
* 这个时候反向想下哪个位置的元素修改后是这个temp的值呢：matrix[j][n - 1 - i]
* 但这个时候不能简单的直接matrix[j][n - 1 - i] = temp，因为这样matrix[j][n - 1 - i]被直接修改了
* 旋转后的值依赖于matrix[j][n - 1 - i]这个值的那个位置的元素就取不到原有的值了
* 再画图找下规律反着来，交换值4次后就完成了一次4个相互影响的值得交换
* 所以 第[n - 1 - j][i]个的元素已经使用过了，直接修改成它翻转后得到的值为[n - 1 - i][n - 1 - j]
* 第[n - 1 - i][n - 1 - j]个的元素已经使用过了，直接修改成它翻转后得到的值[j][n - 1 - i]
* 第[j][n - 1 - i]个的元素已经使用过了，直接修改成它翻转后得到的值为temp
* 对上面已经交换过的元素用visited数组记录，visited[i][j] = 1表示第(i,j)个元素已经变化过，遍历到的时候直接continue；
* 时间复杂度分析：遍历一次数组O(n*n)
* 空间复杂度O(n)
* 代码如下：
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == 1){
                    continue;
                }
                int temp = matrix[i][j];
//                第[i][j]个元素修改为第[n - 1 - j][i]个的值
                matrix[i][j] = matrix[n - 1 - j][i];
                visited[i][j] = 1;
//                第[n - 1 - j][i]个的元素已经使用过了，直接修改成它翻转后得到的值
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                visited[n - 1 - j][i] = 1;
//                第[n - 1 - i][n - 1 - j]个的元素已经使用过了，直接修改成它翻转后得到的值
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                visited[n - 1 - i][n - 1 - j]  = 1;
//                第[j][n - 1 - i]个的元素已经使用过了，直接修改成它翻转后得到的值
                matrix[j][n - 1 - i] = temp;
                visited[j][n - 1 - i] = 1;
            }
        }
    }
*
* */
public class Solution48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == 1){
                    continue;
                }
                int temp = matrix[i][j];
//                第[i][j]个元素修改为第[n - 1 - j][i]个的值
                matrix[i][j] = matrix[n - 1 - j][i];
                visited[i][j] = 1;
//                第[n - 1 - j][i]个的元素已经使用过了，直接修改成它翻转后得到的值
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                visited[n - 1 - j][i] = 1;
//                第[n - 1 - i][n - 1 - j]个的元素已经使用过了，直接修改成它翻转后得到的值
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                visited[n - 1 - i][n - 1 - j]  = 1;
//                第[j][n - 1 - i]个的元素已经使用过了，直接修改成它翻转后得到的值
                matrix[j][n - 1 - i] = temp;
                visited[j][n - 1 - i] = 1;
            }
        }
    }
}
