public class Solution122 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len];
        dp[0] = -prices[0];
        int res = 0;
        for (int i = 1; i < len; i++) {
            if(dp[i - 1] < 0){
                if(prices[i] > prices[i - 1]){
                    dp[i] = Math.max(Math.max(dp[i - 1], -prices[i]), prices[i] + dp[i - 1]);
                    if(dp[i] > 0){
                        res += dp[i];
                    }
                }else{
                    dp[i] = -prices[i];
                }
            }else{
                if(prices[i] > prices[i - 1]){
                    dp[i] = dp[i - 1] + prices[i] - prices[i - 1] ;
                    res += prices[i] - prices[i - 1];
                }else{
                    dp[i] = -prices[i];
                }
            }
        }
        return res;
    }
}
