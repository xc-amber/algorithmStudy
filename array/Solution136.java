import java.util.Arrays;

/*
* Leetcode No.136
* 找出无重复的元素，题目要求不使用额外空间，所以哈希集合不能使用
* 方法1：
* 先对数组进行排序
* 再遍历数组，只要元素与左边的元素和右边的元素都不相等的时候就找到了
* 时间复杂度分析：使用一次Arrays.sort的时间复杂度为O（nlogn）,n为数组长度，循环体中遍历数组，最差情况时间复杂度为O(n);
* 空间复杂度分析使用Arrays.sort的空间复杂度为O(logn)不满足题目要求
*代码如下：
    public int singleNumber(int[] nums) {
        int len = nums.length;
        int result = nums[0];
        if(len == 1){
            return result;
        }
        Arrays.sort(nums);
        if(nums[0] != nums[1]){
            return nums[0];
        }
        if(nums[len - 1] != nums[len - 2]){
            return nums[len - 1];
        }
        for (int i = 1; i < len - 1; i++) {
            if(nums[i] != nums[i - 1] && nums[i] != nums[i + 1]){
                result = nums[i];
            }
        }
        return result;
    }
    *
    *

* 方法2：异或
* 当不能使用额外空间就要往位运算上想
* 对于这道题，是需要找到无重复的元素，所以采用异或运算
* 异或运算有以下三个性质。
1、任何数和0做异或运算，结果仍然是原来的数，即 a ^ 0 = a
2、任何数和其自身做异或运算，结果是 0，即 a ^ a = 0
3、异或运算满足交换律和结合律（类似于乘法运算），即a ^ b ^ a = a ^ a ^ b = 0 ^ b = b
* 所以根据这个性质，只要遍历一次数组，把所有元素进行异或运算就能得到无重复元素的值
* 时间复杂度分析：O(n)，n为数组长度
* 空间复杂度分析：O(1)
* 代码如下：
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
* */

public class Solution136 {
    public int singleNumber(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }
}
