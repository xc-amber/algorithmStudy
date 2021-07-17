
/*
* 本道题和剑指Offer42题一样
* 解法1：简单一维DP
* 定义dp[],dp[i]表示右边界为i的连续子数组的最大值
* 这是连续子数组的问题，所以对于右边界为i的连续子数组的最大值为max(nums[i], 右边界为i - 1的子数组的和 + nums[i])
* 所以状态转移方程为dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
* base case ：dp[0] = nums[0];
* 初始的最大值max = nums[0]，在遍历nums时，max和dp[i]进行比较维护这个最大值结果
* 时间复杂度分析：O(n)
* 空间复杂度分析：O(n)
* 代码如下：
* public class Solution53 {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
*
* 通过上面的状态转移方程我们可以发现dp[i]只与dp[i - 1] + nums[i]相关
* 也就是说dp[i]只与它前一个数相关，那么可以使用状态压缩优化空间，将dp[i - 1]变为temp就行
* 时间复杂度：O(n)
* 空间复杂度:O(1)
*
*  */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int temp = 0;
        for (int num : nums) {
            temp = Math.max(num, temp + num);
            max = Math.max(max, temp);
        }
        return max;
    }
}
