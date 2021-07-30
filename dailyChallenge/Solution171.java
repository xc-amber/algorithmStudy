/*
* 方法1：进制转换
* 这就是一个26进制的转换
* 从最后一个字母开始向前遍历，字母对应的数字*26的len - 1 - i的次方累加到结果上即可
* 时间复杂度O(n),n为字符串长度
* 空间复杂度O(1)
* */

public class Solution171 {
    public int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int res = 0;
        for (int i = len - 1; i >= 0; i--)
        {
            char c = columnTitle.charAt(i);
            res += (c - 'A' + 1) * ((int) Math.pow(26, len - 1 - i));
        }
        return res;
    }
}
