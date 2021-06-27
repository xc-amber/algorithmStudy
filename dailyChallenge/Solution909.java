import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/*
* 方法1：BFS+二维数组
* 从左下角1开始扩散，每次扩散[queue.poll + 1, queue.poll + 6];得到扩散后的值后需要判断在该值是否有梯子
* 根据board的行列数，可以还原出realBoard，记录数字1~row*column
* 再写出根据值取i和j的方法
* 这样我们就能很简单在realBoard中找出扩散后数字的索引，再在board中找到改索引位置是否为-1
* 时间复杂度分析：还原出realBoard  O(n*n)，getX/getY为O(1)，BFS O(n*n)，所以整体时间复杂度O(n*n)
* 空间复杂度分析：O(n*n)
* */
public class Solution909 {
    public int snakesAndLadders(int[][] board) {
        int row = board.length;
        int column = board[0].length;
        int[][] realBoard = getRealBoard(row, column);
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(1);
        visited.add(1);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.poll();
                if(temp == row * column){
                    return step;
                }
                for (int j = temp + 1; j <= temp + 6 ; j++) {
                    if(j <= row * column){
                        int x = getX(realBoard, j);
                        int y = getY(realBoard, j);
                        int newTemp = board[x][y];
                        if(newTemp == -1){
                            if(!visited.contains(j) ){
                                queue.offer(j);
                                visited.add(j);
                            }
                        }else {
                            if(!visited.contains(newTemp) ){
                                queue.offer(newTemp);
                                visited.add(newTemp);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
    public int getX(int[][] board, int a){
        int row = board.length;
        int column = board[0].length;
        return a % column == 0 ? row - a / column : row - 1 - a / column;
    }
    public int getY(int[][] board, int a){
        int row = board.length;
        int column = board[0].length;
        int x = getX(board, a);
        return (row + x) % 2 != 0 ? a - (row - 1 - x) * column - 1 : column - a + (row - 1 - x) * column;
    }
    public int[][] getRealBoard(int row, int column){
        int[][] realBoard = new int[row][column];
        int num = 1;
            for (int i = row - 1; i >= 0; i--) {
                if ((row + i) % 2 != 0) {
                    for (int j = 0; j < column; j++) {
                        realBoard[i][j] = num++;
                    }
                } else {
                    for (int j = column - 1; j >= 0; j--) {
                        realBoard[i][j] = num++;
                    }
                }
            }
        return realBoard;
    }
}

