/*
* 方法1：顺序比较
* 先找到字符串数组中长度最小的字符串
* 再从该最短字符串的第0为开始依次往右与所有的其他字符串比较
* 时间复杂度分析：找到最短字符串O(n),n为数组长度+遍历比较为O(m * n),m为最短字符串长度；所以综合为O(m*n);
* 空间复杂度O(1)
* 代码如下：
    public  static String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if(len == 0){
            return "";
        }
        if(len == 1){
            return strs[0];
        }
        int minLen = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < strs.length; i++) {
            if(strs[i].length() < minLen){
                minLen = strs[i].length();
                index = i;
            }
        }
        if(minLen == 0){
            return "";
        }
        for (int i = 0; i < minLen; i++) {
            char c = strs[index].charAt(i);
            for (int j = 0; j < len; j++) {
                if(strs[j].charAt(i) != c){
                    return strs[index].substring(0, i);
                }
            }
        }
        return strs[index];
    }
* */
public class Solution14 {
    public  static String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if(len == 0){
            return "";
        }
        if(len == 1){
            return strs[0];
        }
        int minLen = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < strs.length; i++) {
            if(strs[i].length() < minLen){
                minLen = strs[i].length();
                index = i;
            }
        }
        if(minLen == 0){
            return "";
        }
        for (int i = 0; i < minLen; i++) {
            char c = strs[index].charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != c) {
                    return strs[index].substring(0, i);
                }
            }
        }
        return strs[index];
    }

    public static void main(String[] args) {
        longestCommonPrefix(new String[]{"ab", "a"});
//        System.out.println("a".substring(0, 0).equals(""));
    }
}
