import java.util.*;

/*
* 方法1： BFS
* 题解见注释
*
* */

public class Solution815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
//        先遍历每个车站，记录每个车站有哪些公交车经过
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++)
        {
            for (int num : routes[i])
            {
                List<Integer> list = map.getOrDefault(num, new ArrayList<>());
                list.add(i);
                map.put(num, list);
            }
        }
//        如果起始站或终点站没有公交车经过，直接返回-1；
        if(map.get(source) == null || map.get(target) == null){
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        //        记录已经选择过的车站
        HashSet<Integer> visited = new HashSet<>();
        //        记录已经选择过的公交车
        HashSet<Integer> indexVisited = new HashSet<>();
        queue.offer(source);
        visited.add(source);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++)
            {
                int temp = queue.poll();
                if(temp == target){
                    return step;
                }
                for (Integer index : map.get(temp))       //找到temp公交站还有哪些公交车经过
                {
                    if(!indexVisited.contains(index)){    //如果该线路的公交车没有选择过将其还没选择过的公交站加入queue
                        for (int a : routes[index])
                        {
                            if(!visited.contains(a)){
                                queue.offer(a);
                            }
                        }
                        indexVisited.add(index);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
