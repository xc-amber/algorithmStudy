/*
* 方法1：双指针
*翻转数组，其实就是轴对称交换元素值
* 定义left指针指向左边起始，right指针指向数组末尾
* 然后交换两个指针对应的元素值；继而left++右移，right--左移；当left，right相等的时候就可以跳出循环
* 时间复杂度分析，遍历数组的n/2次，O(n/2) 即为O(n)
* 空间复杂度O(1)
*代码如下：
    public void reverseString(char[] s) {
        int len = s.length;
        int left = 0;
        int right = len - 1;
        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

* 此题为简单题，我们再想想能不能提高效率且降低空间；异或运算可以不用引入临时变量就完成交换两个值；
* 所以我们把交换两个值得部分代码修改下
* 代码如下：
*     public void reverseString(char[] s) {
        int len = s.length;
        int left = 0;
        int right = len - 1;
        while (left < right){
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            left++;
            right--;
        }
    }
* */
public class Solution344 {
    public void reverseString(char[] s) {
        int len = s.length;
        int left = 0;
        int right = len - 1;
        while (left < right){
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];
            left++;
            right--;
        }
    }
}
