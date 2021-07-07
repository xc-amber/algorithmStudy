/*
* 方法1：递归
* 时间复杂度：O(log3 n)
* 空间复杂度：O(1)
* */

public class Solution326 {
    public boolean isPowerOfThree(int n) {
//        如果是1返回true，3的0次方
        if(n == 1){
            return true;
        }
//        如果都不能被3整除，那肯定不是3的幂次方
        if(n % 3 != 0){
            return false;
        }
//        如果n/3>=1开始递归
        if(n / 3 >= 1){
            return isPowerOfThree(n / 3);
        }
//        如果n/3< 1了，就不是3的幂次方
        return false;
    }
}
