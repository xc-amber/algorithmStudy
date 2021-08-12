
/*
* 方法1：DP
* 题解：参考：https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/shu-ju-jie-gou-he-suan-fa-dong-tai-gui-h-e79i/
* */

public class Solution516 {
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        //这里i要从最后一个开始遍历
        for (int i = length - 1; i >= 0; i--) {
            //单个字符也是一个回文串
            dp[i][i] = 1;
            //j从i的下一个开始
            for (int j = i + 1; j < length; j++) {
                //下面是状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }
}
