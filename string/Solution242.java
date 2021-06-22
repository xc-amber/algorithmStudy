import java.util.Arrays;
import java.util.HashMap;
/*
* 方法1：两个HashMap
* 遍历字符串s，存入map1，key为字符值，value为出现的次数
* 遍历字符串t，存入map2，key为字符值，value为出现的次数
* 比较map1.size 是否和map2.size相等，如果不相等肯定不是异位词
* 相等的情况我们要再遍历一次map1，看所有的key对应的value是否和map2相等，都相等为异位词
* 时间复杂度分析:遍历一次s,遍历一次t,遍历一次map1；O(m + n + m),总体为O(n);m,n分别为是字符串s和t的长度
* 空间复杂度O(n),n最大为小写字母个数26
* 代码如下：
    public boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int len1 = sArr.length;
        int len2 = tArr.length;
        if(len1 != len2){
            return false;
        }
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char value : sArr) {
        map1.put(value, map1.getOrDefault(value, 0)+1);
        }
        for (char value : tArr) {
            map2.put(value, map2.getOrDefault(value, 0)+1);
        }
        if(map1.size() != map2.size()){
            return false;
        }
        for (Character c : map1.keySet()) {
            if(map2.get(c) == null || !map1.get(c).equals(map2.get(c))){
                return false;
            }
        }
        return true;
    }
*方法1优化：如果为异位词map1和map2必然key和value都一致，所以遍历字符串t的时候我们可以直接操作map1,在map1查询value并减一
*代码如下：
*     public boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int len1 = sArr.length;
        int len2 = tArr.length;
        if(len1 != len2){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char value : sArr) {
            map.put(value, map.getOrDefault(value, 0)+1);
        }
        for (char value : tArr) {
            if(map.get(value) == null ){
                return false;
            }
            map.put(value, map.get(value) - 1);
            if(map.get(value).equals(0)){
                map.remove(value);
            }
        }

        return map.size() == 0;
    }
*
* 对于以上解法只需要1个map直接操作key和value，那是不是没必要用map，想到用数组优化
* new int[26];数组长度为26，是因为最多只有26个小写字母
*代码如下：
*    public boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int len1 = sArr.length;
        int len2 = tArr.length;
        if(len1 != len2){
            return false;
        }
        int[] arr = new int[26];
        for (char value : sArr) {
            arr[value - 'a']++;
        }
        for (char value : tArr) {
            if(arr[value - 'a'] == 0 ){
                return false;
            }
            arr[value - 'a']--;
        }
        return true;
    }
* 方法2：排序
* 对字符串s,t分别进行排序
* 如果为异位词，排序后肯定相等
* 时间复杂度分析：两次排序 O(mlogm + nlogn),比较是否相等时间复杂度为O(m)，即总体为O(nlogn);m,n分别为是字符串s和t的长度
* 空间复杂度分析：两个字符数组， O(1),sort排序需要O(log m + logn)，即总体为O(logn)
* 代码如下：
*     public boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int len1 = sArr.length;
        int len2 = tArr.length;
        if(len1 != len2){
            return false;
        }
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
* */
public class Solution242 {
    public boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int len1 = sArr.length;
        int len2 = tArr.length;
        if(len1 != len2){
            return false;
        }
        int[] arr = new int[26];
        for (char value : sArr) {
            arr[value - 'a']++;
        }
        for (char value : tArr) {
            if(arr[value - 'a'] == 0 ){
                return false;
            }
            arr[value - 'a']--;
        }
        return true;
    }

}
