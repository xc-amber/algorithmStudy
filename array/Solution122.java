/*
* 方法1：动态规划
* 定义dp[prices.length],dp[i]表示第i天的最大收益
* base case dp[0] = -prices[i];
* 接下来从i = 1开始遍历
* 如果dp[i - 1] < 0的情况，那表示肯定是亏本的，这个时候如果第i天的价格比第i - 1天高的话，dp[i] = prices[i] + dp[i - 1],否则dp[i] = -prices[i];
* 如果dp[i - 1] >= 0的情况,那表示是盈利的，这个时候如果第i天的价格比第i - 1天高的话，dp[i] = dp[i - 1] + prices[i] - prices[i - 1];否则dp[i] = -prices[i];
* 时间复杂度分析：遍历一次数组O(n),n为数组长度
* 空间复杂度分析：定义dp[prices.length]，O(n)
* 代码如下：
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len];
        dp[0] = -prices[0];
        int res = 0;
        for (int i = 1; i < len; i++) {
            if(dp[i - 1] < 0){
                if(prices[i] > prices[i - 1]){
                    dp[i] = prices[i] + dp[i - 1];
                        res += dp[i];
                }else{
                    dp[i] = -prices[i];
                }
            }else{
                if(prices[i] > prices[i - 1]){
                    dp[i] = dp[i - 1] + prices[i] - prices[i - 1];
                    res += prices[i] - prices[i - 1];
                }else{
                    dp[i] = -prices[i];
                }
            }
        }
        return res;
    }
*
*方法2：直接遍历数组
* 从动态规划的方法中得到启示dp[i]我都取决于prices[i] - prices[i - 1]是否> 0;
* 如果大于0，就累加收益，如果小于0就是前一天卖掉，今天买入
* 那整个过程只要遍历一次数组，只要当前元素值比前一个大，这个差值累积到结果上就行
* 时间复杂度分析：遍历一次数组O(n)
* 空间复杂度分析：O(1)
*
*  */
public class Solution122 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int res = 0;
        for (int i = 1; i < len; i++) {
            if(prices[i] > prices[i - 1]){
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
