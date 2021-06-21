/*
* 方法1：两次遍历
* 新建一个map，key为字符，value为次数
* 第一次遍历，map记录每个字符出现的次数
* 第二次遍历，如果在map中的次数为1，返回i；
* 时间复杂度分析：两次遍历数组：O(2n)即为O(n)
* 空间复杂度分析：一个map，O(n)，n为字符串长度
* 代码如下：
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int index = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            if(map.get(chars[i]) == 1){
                index = i;
                break;
            }
        }
        return index;
    }
*
* 方法2：两个哈希表
* map1，记录key为字符，i为索引
* map2只用来记录已经重复的字符，key为字符，value随意，没有意义；那为什么不用list？
* 遍历的时候看map2中是否存在，存在就跳过继续；因为判断是否存在这个字符。map.containsKey效率高于list.contains
* 如果map2中没有，判断map1中是否存在，不存在的话加入map1；存在的话存入map2，同时map1中也删除；举例“aaab”这种测试用例
* 遍历结束后如果map1.size == 0的话返回-1；如果不为0的话返回map1中最小的value
* 时间复杂度分析：遍历一次字符串，O(n),遍历一次map1, O(n)，整体复杂度为O(n)；但只有字符串全部不重复的情况下map1的长度才为n，所以时间效率肯定优于方法1；
* 空间复杂度，两个map，map1无重复的，map2记录重复的，所以整个空间复杂度O(n)，n为字符串长度
* 代码如下：
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = len - 1; i >= 0 ; i--) {
            if(map2.containsKey(chars[i])){
                continue;
            }
            if(map1.containsKey(chars[i])){
                map2.put(chars[i], i);
                map1.remove(chars[i]);
            }else{
                map1.put(chars[i], i);
            }
        }
        if(map1.size() == 0){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (Character c : map1.keySet()) {
            res = Math.min(res, map1.get(c));
        }
        return res;
    }
* */
import java.util.HashMap;

public class Solution387 {
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (int i = len - 1; i >= 0 ; i--) {
            if(map2.containsKey(chars[i])){
                continue;
            }
            if(map1.containsKey(chars[i])){
                map2.put(chars[i], i);
                map1.remove(chars[i]);
            }else{
                map1.put(chars[i], i);
            }
        }
        if(map1.size() == 0){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (Character c : map1.keySet()) {
            res = Math.min(res, map1.get(c));
        }
        return res;
    }
}
