import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
* 方法1：BFS
* 遍历relation数组，记录第i个小伙伴可以传信息的相邻人
* BFS框架直接套上，起点为0，目标点为n - 1；记录传递的次数step；
* 当到目标点时，且step == k时，方案+1
* 时间复杂度分析：遍历一次relation数组O(m)，m为m为relation数组长度；BFS循环体内最多需要遍历 k 层，每层遍历最多有 O(n)个分支。
* 所以时间复杂度为O(m + n^k),即为O(n^k);
* 空间复杂度分析：O(n+m+n^k)。其中 n 为小伙伴人数，m为relation数组的长度。由于每层遍历最多有O(n)个分支，因此遍历到 k 层时，队列的大小为 O(n^k)。
* 代码如下：
* public class SolutionLCP07 {
    public int numWays(int n, int[][] relation, int k) {
//        记录第i个小伙伴可以传信息的相邻人
        List<Integer>[] temp = new List[n];
        for (int[] rel : relation)
        {
            if(temp[rel[0]] == null){
                temp[rel[0]] = new ArrayList<>();
            }
            temp[rel[0]].add(rel[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int step = 0; //传递信息的次数
        int res = 0;  //方案次数
//        当队列不为空且传递次数小于等于k时添加邻居
        while (!queue.isEmpty() && step <= k){
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                int a = queue.poll();
                if(a == (n - 1)){
                    if(step == k){
                        res++;
                    }
                }
                if(temp[a] != null){
                    for (Integer ans : temp[a])
                    {
                        queue.offer(ans);
                    }
                }
            }
            step++;
        }
        return res;
    }
}
*
* 方法2：DFS
* 可以从小伙伴0开始一直往下传递，传递到无法传递，判断传递的过程中是否存在传递了k次到达第n - 1个小伙伴
* 时间复杂度分析：遍历一次relation数组O(m)，m为m为relation数组长度；BFS循环体内最多需要遍历 k 层，每层遍历最多有 O(n)个分支。
* 空间复杂度分析：O(n+m)。其中 n 为小伙伴人数，m为relation数组的长度。
* 代码如下：
* public class SolutionLCP07 {
        int num = 0;
        int target;
        int k;
        List<Integer>[] temp;
        public int numWays(int n, int[][] relation, int k) {
            this.target = n - 1;
            this.k = k;
//        记录每个小伙伴可以传信息的相邻人
            temp = new List[n];
            for (int[] rel : relation)
            {
                if(temp[rel[0]] == null){
                    temp[rel[0]] = new ArrayList<>();
                }
                temp[rel[0]].add(rel[1]);
            }
            backtrack(0, 0);
            return num;
        }
        public void backtrack(int start, int step){
            if(step > k){
                return;
            }
            if(start == target && step == k){
                num++;
                return;
            }
            if(temp[start] != null){
                for (Integer ans : temp[start])
                {
                    backtrack(ans, step + 1);
                }
            }
        }
}
 */
public class SolutionLCP07 {
        int num = 0;
        int target;
        int k;
        List<Integer>[] temp;
        public int numWays(int n, int[][] relation, int k) {
            this.target = n - 1;
            this.k = k;
//        记录每个小伙伴可以传信息的相邻人
            temp = new List[n];
            for (int[] rel : relation)
            {
                if(temp[rel[0]] == null){
                    temp[rel[0]] = new ArrayList<>();
                }
                temp[rel[0]].add(rel[1]);
            }
            backtrack(0, 0);
            return num;
        }
        public void backtrack(int start, int step){
            if(step > k){
                return;
            }
            if(start == target && step == k){
                num++;
                return;
            }
            if(temp[start] != null){
                for (Integer ans : temp[start])
                {
                    backtrack(ans, step + 1);
                }
            }
        }
}
