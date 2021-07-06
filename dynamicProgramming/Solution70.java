/*
*
* 方法1：动态规划
* 爬到第n层的话只能是从第n-1层再爬1阶或者从第n-2层再爬2阶；所以爬到第n层的方法=爬到第n-2层的方法+爬到第n-1层的方法
* 时间复杂度分析：遍历n，O(n)
* 空间复杂度分析：dp数组O(n)
*
* */

public class Solution70 {
    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int[] dp = new int[n + 1];  //定义dp数组，dp[i]表示爬到第i层的方法总数,因为要计算第n层的结果，所以长度初始化为n + 1；
//        base case
        dp[1] = 1;
        dp[2] = 2;
//        从第三层开始遍历到第n层，第i层的爬楼方法=第i-2层+第i-1层的
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
//        最后返回第n层的结果
        return dp[n];
    }
}
