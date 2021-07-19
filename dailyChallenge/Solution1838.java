import java.util.Arrays;
/*
* 方法1：排序+滑动窗口
* 本道题先排序，从最小的值开始往上加到第二小的值，再从第二小的值加到第三小的值，以此类推，只要保证累加的值temp<=k
* 当temp > k时，收缩左边窗口；temp<= k时扩展右边窗口即可
* 时间复杂度分析：排序O(nlogn),滑窗遍历一次O(n)，所以整体为O(n + nlogn)
* 空间复杂度分析：排序额外空间O(logn)
*
* */

public class Solution1838 {
    public static int maxFrequency(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        int max = 1;
        int left = 0;
        int right = 1;
        long temp = 0;
        while (right < len){
            int res = right - left;
            temp += res * (nums[right] - nums[right - 1]);
            while (temp > k){
                temp -= nums[right] - nums[left];
                left++;
            }
            right++;
            max = Math.max(max, right - left);
        }
        return max;
    }
}
