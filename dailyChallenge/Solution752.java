import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/*
* 方法1：BFS
* 看到求最少次数，求最短路径之类的问题要立马想到BFS
* BFS算法核心就是遍历图，把起始节点放入queue中，再从queue中依次取出节点，每次将被取出节点的相邻节点再加入queue中；当找到结果时return
* BFS代码框架：
    public int BFS(){
        Queue<Node> queue = new LinkedList<>();   //核心数据结构，队列
        HashSet<Node> visitedSet = new HashSet<>();  //记录已经被加入过queue的节点，防止走回头路
        queue.add(起始点);    //将起始点添加到queue中
        int step = 0;    //记录步数
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
//                判断是否找到终点，找到就返回步数
                if(node is target){
                    return step;
                }
//                如果不是终点，就将node的所有相邻点加入queue
                for(相邻点 : node的所有相邻点){
                    queue.offer(相邻点)；
//                    别忘了也要加入visitedSet
                    visitedSet.add(相邻点);
                }
            }
            step++;
        }
        return 未找到;
    }
* 此道题中起点就是"0000",终点就是target;
* 主要任务就是找到相邻点；"0000"的相邻点就是每位数字向上或者向下拨动；
* 此题还有个额外需要处理的死亡密码，当遍历到死亡密码直接跳过即可
* 时间复杂度分析：图的节点个数*找零点的复杂度；本题中图的节点个数为密码的总可能个数10的4次方O(10^4) ；找零点的复杂度为O(2 * 4);
* 空间复杂度：队列和visited中存储10^4个长度为4的字符串O(2 * 10^4 * 4);deadSet存n个长度为4的字符串O(n * 4),n为deadSet的长度
* 代码如下：
* public class Solution752 {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>();
        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                if(deadSet.contains(str)){
                    continue;
                }
                if(str.equals(target)){
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String plus = puls(str, j);
                    if(!visited.contains(plus)){
                        queue.offer(plus);
                        visited.add(plus);
                    }
                }
                for (int j = 0; j < 4; j++) {
                    String minus = minus(str, j);
                    if(!visited.contains(minus)){
                        queue.offer(minus);
                        visited.add(minus);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    public String puls(String string, int i){
        char[] ch = string.toCharArray();
        if(ch[i] == '9'){
            ch[i] = '0';
        }else{
            ch[i] += 1;
        }
        return new String(ch);
    }
    public String minus(String string, int i){
        char[] ch = string.toCharArray();
        if(ch[i] == '0'){
            ch[i] = '9';
        }else{
            ch[i] -= 1;
        }
        return new String(ch);
    }
}
*
* 方法2：双向BFS
* 当知道起点和终点的时候我们可以用BFS，但也应该立即想到双向BFS来优化搜索次数
* 双向BFS代码框架如下：
    public int bidirectionalBFS(){
//        双向遍历用集合不用队列，主要是因为集合可以快速判断元素是否存在，也就是方便判断双向BFS是否有交点了
        HashSet<Node> q1 = new HashSet<>();
        HashSet<Node> q2 = new HashSet<>();
//        访问过的节点
        HashSet<Node> visited = new HashSet<>();
//        q1.add(起点)
        q1.add(start);
//        q2.add(终点)
        q2.add(target);
        int step = 0;    //记录步数
        while (!q1.isEmpty() && !q2.isEmpty()){
//            先搜索元素少的集合
            if(q1.size() > q2.size()){
                HashSet<Node> tempForSwap = q1;
                q1 = q2;
                q2 = tempForSwap;
            }
//            temp存储q1中搜索到的元素相邻的节点
            HashSet<Node> temp = new HashSet<>();
//            搜索q1中所有的节点进行扩散
            for(cur : q1){
//                判断是否达到终点，也就是双向BFS是否产生了交点
                if(q2.contains(cur)){
                    return step;
                }
//                记录已经搜索过的节点
                visited.add(cur);
//                如果还没找到终点，就将该节点的所有相邻点加入q1;
                for(相邻点 : node的所有相邻点){
                    if(!visited.contains(相邻点)){
                        q1.add(相邻点)；
                    }
                }
            }
            step++; //别忘记增加步数
        }
        return 找不到;
    }
* */
public class Solution752 {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>();
        Collections.addAll(deadSet, deadends);
        HashSet<String> visited = new HashSet<>();
        HashSet<String> q1 = new HashSet<>();
        HashSet<String> q2 = new HashSet<>();
        q1.add("0000");
        q2.add(target);
        int step = 0;
        while (!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size() > q2.size()){
                HashSet<String> tempForSwap = q1;
                q1 = q2;
                q2 = tempForSwap;
            }
            HashSet<String> temp = new HashSet<>();
            for (String s : q1) {
                if(deadSet.contains(s)){
                    continue;
                }
                if(q2.contains(s)){
                    return step;
                }
                visited.add(s);
                for (int i = 0; i < 4; i++) {
                    String plus = puls(s, i);
                    q1.add(plus);
                    String minus = minus(s, i);
                    q1.add(minus);
                }
            }
            step++;
        }
        return -1;
    }
    public String puls(String string, int i){
        char[] ch = string.toCharArray();
        if(ch[i] == '9'){
            ch[i] = '0';
        }else{
            ch[i] += 1;
        }
        return new String(ch);
    }
    public String minus(String string, int i){
        char[] ch = string.toCharArray();
        if(ch[i] == '0'){
            ch[i] = '9';
        }else{
            ch[i] -= 1;
        }
        return new String(ch);
    }

    public static void main(String[] args) {
        Solution752 solution752 = new Solution752();
        solution752.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202");
    }
}
