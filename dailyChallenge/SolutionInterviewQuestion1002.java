import java.util.*;
/*
* 方法1：排序+Map
* 主要思路将strs中每个字符串转换为char数组，再排序得到新的newStr
* 再建立一个map，key就为排序后的newStr，value就是这个排序后的字符串对应的strs中字符串的集合
* 所以先遍历strs，对str排序，得到新的newStr，加入map的value中即可
* 最后map的所有value构成的集合就是结果
* 时间复杂度分析：遍历strs：O(n),n为strs的长度，每个str要排序一次：O(mlogm),m为strs中每个字符串的长度，所以整体为O(n*mlogm)
* 空间复杂度分析：map：O(n),n为strs的长度，排序额外空间O(logm),m为strs中每个字符串的长度；所以整体为O(n*logm)
* 代码如下：
* public class SolutionInterviewQuestion1002 {
    public  List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String newStr = new String(ch);
            List<String> list = map.getOrDefault(newStr, new ArrayList<>());
            list.add(str);
            map.put(newStr, list);
        }
        for (Map.Entry<String, List<String>> listEntry : map.entrySet()) {
            res.add(listEntry.getValue());
        }
        return res;
    }
}
*
* 方法2：计数+Map
* Map的思路和上面一致；方法2的key从排序后的newStr改为计数拼接的newStr
* 计数拼接的newStr的意思是对str中每个字母进行计数，并按照从小到大的字母顺序+次数来拼接key；例如“tea”，拼接后的key为a1e1t1
* 时间复杂度：遍历strs：O(n),n为strs的长度，每个str要计数拼接：O(m + 26),m为strs中每个字符串的长度，26为freq的长度；所以整体为O(n*(m + 26))
* 空间复杂度：map：O(n),n为strs的长度，ch数组O(m),m为strs中每个字符串的长度，freq数组O(26)；所以整体为O(n*(m + 26))
* */
public class SolutionInterviewQuestion1002 {
    public  List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            int[] freq = new int[26];
            for (char c : ch) {
                freq[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < freq.length; i++) {
                sb.append(((char) (i + 'a')));
                sb.append(freq[i]);
            }
            String newStr = sb.toString();
            List<String> list = map.getOrDefault(newStr, new ArrayList<>());
            list.add(str);
            map.put(newStr, list);
        }
        for (Map.Entry<String, List<String>> listEntry : map.entrySet()) {
            res.add(listEntry.getValue());
        }
        return res;
    }
}
