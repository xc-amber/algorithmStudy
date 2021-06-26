import java.util.*;
/*
* 方法1：标准BFS：
* 将二维数组按从左到右从上到下的顺序转换为字符串，使用字符串进行比较来简化
* 本体难度在于找相邻点：
* 1)找到字符串中“0”所在的位置index
* 2)index 和“0”原本在board数组中的位置(x, y)有如下关系：
* 如果index == 0，则x = 0, y = 0;如果index != 0,则x = index / 数组的宽度column; 如果x == 0，则y = index;如果x != 0，则y = index - x *
* column
* 数组中“0”能上下左右移动一个位置；在二维数组中元素i需要上下左右移动通常会借助数组{{1, 0}, {0, 1}, {-1, 0},{0, -1}}来和元素i的索引进行相加操作
* 代码如下：
public class Solution773 {
    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int column = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int[] ints : board) {
            for (int j = 0; j < column; j++) {
                sb.append(ints[j]);
            }
        }
        String start = sb.toString();
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                String str = queue.poll();
                if(str.equals("123450")){
                    return step;
                }
                int index = str.indexOf("0");
                List<Integer> list = get(board, index);
                for (int newIndex : list)
                {
                    String newStr = str;
                    newStr = swap(newStr, index, newIndex);
                    if(!visited.contains(newStr)){
                        queue.offer(newStr);
                        visited.add(newStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public List<Integer> get(int[][] board, int target){
        int[][] ints = new int[][]{{1, 0}, {0, 1}, {-1, 0},{0, -1}};
        List<Integer> list = new ArrayList<>();
        int row = board.length;
        int column = board[0].length;
        int x = 0;
        int y = 0;
        if(target != 0){
            x = target / column;
            if(x == 0){
                y = target;
            }else{
                y = target - x * column;
            }
        }
        for (int[] anInt : ints)
        {
            if((anInt[0] + x) < row && (anInt[0] + x) >= 0 && (anInt[1] + y) < column && (anInt[1] + y) >= 0){
                list.add((anInt[0] + x) * column + (anInt[1] + y));
            }
        }
        return list;
    }
    public String swap(String string, int i, int j){
        char[] ch = string.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }
}
*
* 因为本题为2 * 3的数组，压缩成字符串后，因为长度恒等于6且不大，所以我们可以手动写出字符串每个index对应的邻居的下标
* int[][] arr = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
* 举例：原数组为1, 2, 3  转为字符串后为“123405”,"0"在字符串中的index = 4;arr[4] = {1, 3 ,5};arr[4]中的元素就是“0”原本在board中邻居在字符串中对应的下标；
               4, 0 ,5
* 代码优化后如下：
public class Solution773 {
    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int column = board[0].length;
        StringBuilder sb = new StringBuilder();
        int[][] arr = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        for (int[] ints : board) {
            for (int j = 0; j < column; j++) {
                sb.append(ints[j]);
            }
        }
        String start = sb.toString();
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                String str = queue.poll();
                if(str.equals("123450")){
                    return step;
                }
                int index = str.indexOf("0");
                for (int newIndex : arr[index])
                {
                    String newStr = str;
                    newStr = swap(newStr, index, newIndex);
                    if(!visited.contains(newStr)){
                        queue.offer(newStr);
                        visited.add(newStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }


    public String swap(String string, int i, int j){
        char[] ch = string.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }
}
*
* 方法2：双向BFS
* 因为知道了起点和终点，所以可以改写为双向BFS
* 代码如下：
public class Solution773 {
    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int column = board[0].length;
        StringBuilder sb = new StringBuilder();
        int[][] arr = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        for (int[] ints : board) {
            for (int j = 0; j < column; j++) {
                sb.append(ints[j]);
            }
        }
        String start = sb.toString();
        String target = "123450";
        HashSet<String> q1 = new HashSet<>();
        HashSet<String> q2 = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        q1.add(start);
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size() > q2.size()){
                HashSet<String> tempForSwap = q1;
                q1 = q2;
                q2 = tempForSwap;
            }
            HashSet<String> temp = new HashSet<>();
            for (String s : q1)
            {
                if(q2.contains(s)){
                    return step;
                }
                visited.add(s);
                int index = s.indexOf("0");
                for (int newIndex : arr[index])
                {
                    String str = s;
                    str = swap(str, index, newIndex);
                    if(!visited.contains(str)){
                        temp.add(str);
                    }
                }
            }
            step++;
            q1 = temp;
        }
        return -1;
    }
    public String swap(String string, int i, int j){
        char[] ch = string.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }
}
* */

public class Solution773 {
    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int column = board[0].length;
        StringBuilder sb = new StringBuilder();
        int[][] arr = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        for (int[] ints : board) {
            for (int j = 0; j < column; j++) {
                sb.append(ints[j]);
            }
        }
        String start = sb.toString();
        String target = "123450";
        HashSet<String> q1 = new HashSet<>();
        HashSet<String> q2 = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        q1.add(start);
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size() > q2.size()){
                HashSet<String> tempForSwap = q1;
                q1 = q2;
                q2 = tempForSwap;
            }
            HashSet<String> temp = new HashSet<>();
            for (String s : q1)
            {
                if(q2.contains(s)){
                    return step;
                }
                visited.add(s);
                int index = s.indexOf("0");
                for (int newIndex : arr[index])
                {
                    String str = s;
                    str = swap(str, index, newIndex);
                    if(!visited.contains(str)){
                        temp.add(str);
                    }
                }
            }
            step++;
            q1 = temp;
        }
        return -1;
    }
    public String swap(String string, int i, int j){
        char[] ch = string.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return new String(ch);
    }
}
