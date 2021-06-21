/*
* 方法1：递归
* 因为是反转数字，所以我们要先取到个位数，令个位数为temp = x % 10即可
* 又需要把个位数放在第一位，方法就是int res = 0; res = res * 10 + temp;
* 然后就递归reverse(x / 10),当x/10 == 0时终止递归
* 因为要防止整数反转后溢出；怎么判断整数a有没有整数溢出，最简单的方法就是判断(a * 10) / 10 是否等于a;若不等则溢出
* 时间复杂度分析：递归次数x的位数,-231 <= x <= 231 - 1,所以整体可以认为O(1);
* 空间复杂度O(1)
* 代码如下；
public class Solution7 {
    int res = 0;
    public int reverse(int x) {
        if((res * 10) / 10 != res){
            return 0;
        }
        int temp = x % 10;
        res = res * 10 + temp;
        if(x / 10 != 0){
            return reverse(x / 10);
        }
        return res;
    }
}
*
* 如果觉得递归的代码不太好写，我们也可以用循环的代码来实现
* 代码如下：
public class Solution7 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            if((res * 10) / 10 != res){
                return 0;
            }
            int temp = x % 10;
            res = res * 10 + temp;
            x /= 10;
        }
        return res;
    }
}
* */

public class Solution7 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            if((res * 10) / 10 != res){
                return 0;
            }
            int temp = x % 10;
            res = res * 10 + temp;
            x /= 10;
        }
        return res;
    }
}
