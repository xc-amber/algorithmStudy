import java.util.*;

/*
* 方法1：Map+双端队列
* 用Map存储邻居关系，用队列来构造还原一维数组
* 遍历二维数组，将除去二维数组的第一对值的（第一对记录也行，只是没必要）所有的邻居关系用map存起来，map的数据结构为Map<Integer, List<Integer>>，
* 比如原二维数组是[[4,-2],[1,4],[-3,1]]，记录出的map为map: {1:[4,-3],-3:[1],4:[1]}
* 然后将二维数组的第一对值入队：deque: [-2,4]
* 从队列的顶（记为left）和尾（记为right）去Map中找寻没有出现在队列中的邻居；map.get(-2) = null;map.get(4) = 4:[1];
* 然后再更新left为队列最新的顶 left = deque.peekFirst();更新right为队列最新的尾 right = deque.peekLast();再循环上一步
* 注意上一步骤中需要判断邻居是否出现在队列中，队列不好用O(1)的复杂度判断，就借助visited Set来记录就行
* 时间复杂度O(n)，n为数组长度
* 空间复杂度O(n)，dqueue和HashSet
* */


public class Solution1743 {
    public int[] restoreArray(int[][] adjacentPairs) {
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(adjacentPairs[0][0]);
        deque.offerFirst(adjacentPairs[0][1]);
        int left = adjacentPairs[0][1];
        int right = adjacentPairs[0][0];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(left);
        visited.add(right);
        for (int i = 1; i < adjacentPairs.length; i++) {
            map.putIfAbsent(adjacentPairs[i][0], new ArrayList<>());
            map.get(adjacentPairs[i][0]).add(adjacentPairs[i][1]);
            map.putIfAbsent(adjacentPairs[i][1], new ArrayList<>());
            map.get(adjacentPairs[i][1]).add(adjacentPairs[i][0]);
        }
        while (deque.size() < adjacentPairs.length + 1){
            if(map.get(left) != null){
                List<Integer> list1 = map.get(left);
                for (Integer integer : list1) {
                    if(!visited.contains(integer)){
                        deque.offerFirst(integer);
                        visited.add(integer);
                    }
                }
            }
            if(map.get(right) != null){
                List<Integer> list2 = map.get(right);
                for (Integer integer : list2) {
                    if(!visited.contains(integer)){
                        deque.offerLast(integer);
                        visited.add(integer);
                    }
                }
            }
            left = deque.peekFirst();
            right = deque.peekLast();
        }

        int[] res = new int[adjacentPairs.length + 1];
        int index = 0;
        while (!deque.isEmpty()){
            res[index++] = deque.pollFirst();
        }
        return res;
    }
}
