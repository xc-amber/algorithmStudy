import java.util.Arrays;
import java.util.HashMap;
/*
* 方法1：两个HashMap
* 遍历字符串s，存入map1，key为字符值，value为出现的次数
* 遍历字符串t，存入map2，key为字符值，value为出现的次数
* 比较map1.size 是否和map2.size相等，如果不相等肯定不是异位词
* 相等的情况我们要再遍历一次map1，看所有的key对应的value是否和map2相等，都相等为异位词
* 时间复杂度分析:遍历一次s,遍历一次t,遍历一次map1；O(m + n + m),总体为O(m + n);m,n分别为是字符串s和t的长度
* 空间复杂度O(m + n)
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
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        for (int i = 0; i < len1; i++) {
            if(sArr[i] != tArr[i]){
                return false;
            }
        }
        return true;
    }

}
