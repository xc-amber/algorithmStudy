/*
* 方法1：动态规划
* 定义dp[]数组，dp[0]表示已经拥有股票的最低成本。dp[1]表示到当天能赚的最多钱
* 所以base case就是dp[0] = 第一天买入-price[0];dp[1] = 第一天的盈利最大为0，也就是不买
* 所以推导状态转移方程：到第i天时已经拥有股票的最低成本；dp[0] = Math.max(-prices[i], dp[0]);
* 到第i天时能赚到的最多的钱：dp[1] = Math.max(prices[i] + dp[0], dp[1]);
* 时间复杂度分析：遍历一次数组O(n)
* 空间复杂度分析dp数组O(1);
* 代码如下：
* public class Solution121 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0){
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(-prices[i], dp[0]);
            dp[1] = Math.max(prices[i] + dp[0], dp[1]);
        }
        return dp[1];
    }
}
*
*
* 方法2：最大值最小值
* 遍历数组，找到遍历到当前的最小值
* 最大值即为max = Math.max(prices[i] - min, max);
* 最后返回max
* 时间复杂度分析：O(n)
* 空间复杂度O(1)
*  */

public class Solution121 {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            max = Math.max(price - min, max);
        }
        return max;
    }
}
