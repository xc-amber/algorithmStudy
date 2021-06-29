/*
* (参考了题解)这道题其实就是一个26进制的转换题
* 方法1：迭代
* 进制转换类问题可以用迭代和递归的解法来解决
* 只需要不断地对 columnNumber进行 % 运算取得最后一位，然后对 columnNumber进行 / 运算，
* 将已经取得的位数去掉，直到 columnNumber为 0 即可。
* 注意：本题是从1开始的，所以columnNumber要减1
* 时间复杂度分析：columnNumber为0时停止循环，columnNumber 每次 / 26；所以时间复杂度为O(log26 columnNumber)
* 空间复杂度分析：O(1)
* 代码如下：
* public class Solution168 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
*
* 方法2：递归
* 时间复杂度分析：columnNumber为0时停止循环，columnNumber 每次 / 26；所以时间复杂度为O(log26 columnNumber)
* 空间复杂度分析：O(1)
* 代码如下：
* public class Solution168 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        if(columnNumber > 0){
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
        }
        if(columnNumber / 26 > 0){
//            因为最后返回的结果被反转了，所以递归得到的结果要反转后再append
            StringBuilder stringBuilder = new StringBuilder(convertToTitle(columnNumber / 26));
            stringBuilder.reverse();
            sb.append(stringBuilder);
        }
        sb.reverse();
        return sb.toString();
    }
}
* */
public class Solution168 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        if(columnNumber > 0){
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
        }
        if(columnNumber / 26 > 0){
//            因为最后返回的结果被反转了，所以递归得到的结果要反转后再append
            StringBuilder stringBuilder = new StringBuilder(convertToTitle(columnNumber / 26));
            stringBuilder.reverse();
            sb.append(stringBuilder);
        }
        sb.reverse();
        return sb.toString();
    }
}
