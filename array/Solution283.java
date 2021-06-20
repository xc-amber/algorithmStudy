/*
* 方法1：双指针
* 定义一个left指针指向index = 0；right指针指向index = 1；
* 当nums[left] != 0时，left++；right++；
* 当nums[left] == 0时，看nums[right]是否为0，nums[right]不为0的话，互换值；然后left++；right++；nums[right]为0的话，right++；
* 时间复杂度分析：遍历一次数组，O(n)
* 空间复杂度分析：在原数组上修改，只有两个指针属性；O(1)
* */
public class Solution283 {
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != 0) {
                left++;
            } else {
                if (nums[right] != 0) {
                    nums[left] = nums[right];
                    nums[right] = 0;
                    left++;
                }
            }
            right++;
        }
    }
}
