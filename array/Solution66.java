/*
Leetcode No.66
* 思路：最简单的想法是将原数组转化为数字，再将数字进行+1后再转为数组；
* 但想到如果原数组很长，转化为的数字必然整数越界，所以不是一个好的方法
* 所以想办法直接在原数组上修改
* 方法1：
* 当原数组的最后一个元素小于9的时候，只需要把最后一个元素+1，再返回原数组即可
* 例如原数组为{1,2,3}，直接返回{1,2,4}
* 当原数组的最后一个元素为9的时候
* 例如原数组为{1,2,9}，最后一个元素变为0，且前一个元素需要+1，即为{1,3,0}
* 但如果原数组的第二个元素也为9的时候依然要加1进1；
* 所以从最后一个元素下标len - 1开始起比较这个元素是否为9，如果不为9的话就直接加1,然后return修改后的数组结束；
* 如果为9的话，再进行前一位元素的比较，(len - 1)--，(len - 1) < 0 时跳出循环；(len - 1)-- == len--;
* 以上比较结束后还没return的话，就是原数组每个元素都为9；所以新建一个原数组长度+1的数组，第一个元素为1，后面每个元素为0
*代码如下：
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        while (len - 1 >= 0){
            if(digits[len - 1] != 9){
                digits[len - 1] ++;
                return digits;
            }else {
                digits[len - 1] = 0;
                len--;
            }
        }
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
    }
*

* */
public class Solution66 {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        while (len - 1 >= 0){
            if(digits[len - 1] != 9){
                digits[len - 1] ++;
                return digits;
            }else {
                digits[len - 1] = 0;
                len--;
            }
        }
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
    }
}
