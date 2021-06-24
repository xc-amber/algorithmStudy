/*
* 方法1：递归
* 1、要计算countAndSay(n)，我们要先算出countAndSay(n - 1)，所以很简单就想到了递归；base case就是countAndSay(1) = "1";
* 2、假设知道了countAndSay(n - 1)的结果是字符串string
* 3、建立myCountAndSay方法，对string处理得到countAndSay(n)
* 4、myCountAndSay方法中对string进行遍历处理，假设string为"3322251"，我们先将c记为起始的字符，char c = string.charAt(0);它的作用就是与后面元素对比
*    在从index = 0开始遍历，如果index指向的单个字符 == c，那么个数res++；
*    如果index指向的单个字符串 != c ,那么先直接stringBuilder.append(个数)；也可以将int转为String ，用API String.valueOf(int a),再直接stringBuilder
* .append(c);也可以将字符转字符串：用API String.valueOf(char a), 最后要将被比较的变量c = index指向的单个字符；同时个数res标识为1；最后index++右移遍历即可
* 时间复杂度分析：计算countAndSay(n)；需要递归n - 1次；每次递归中遍历一次countAndSay(n)的结果String；时间复杂度比较魔幻可以认为是O(n * m);m为每个值对应的结果字符串长度
* 空间复杂度分析：O(1)
*
 * */

public class Solution38 {
    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        return myCountAndSay(countAndSay(n - 1));
    }
    public  static String myCountAndSay(String string){
        StringBuilder sb = new StringBuilder();
        int res = 0;
        char c = string.charAt(0);
        int index = 0;
        while (index < string.length()){
            char temp = string.charAt(index);
            if(c == temp){
                res++;

            }else {
                sb.append(res);
                sb.append(c);
                c = temp;
                res = 1;
            }
            if(index == string.length() - 1){
                sb.append(res);
                sb.append(c);
            }
            index++;
        }
        return sb.toString();
    }
}
