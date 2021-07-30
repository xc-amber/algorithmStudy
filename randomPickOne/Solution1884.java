/*
* 方法1：DP
* 1、本道题的核心思想就是：寻找到第一次应该从哪个楼层扔鸡蛋
* 2、假设从第i层扔第一次，如果第一个蛋碎了，那么还有一个蛋必须从1楼开始逐渐往上扔才能找到f，那么就是至少需要扔i次
* 3、如果第一个蛋没碎的话，那么问题就转化为了楼层从n变为了n - i层；那么结果就是twoEggDrop(n - i) + 1次
* 4、最后n层楼找f的结果就是Math.max(twoEggDrop(n - i) + 1, i)了,
* 5、因为第三步转换为了求子问题的形式，采用动态规划思想将1~n-1的结果使用dp[]记录，就可以是复杂度变为O(1)
* 6、那么这个i从哪开始取值，不妨令i = (n + 1) /2;如果dp[n - i] > i - 1的话,dp[n] Math.max(dp[n - i] + 1, i);
*  如果dp[n - i] > i - 1的话，i就向下找i--，直到dp[n - i] > i - 1，就找到了起始的i，dp[n] Math.max(dp[n - i] + 1, i);（这一步可以多画画图看下规律）
* 时间复杂度分析：O(n*n)
* 空间复杂度O(n)
* */
public class Solution1884 {
    public int twoEggDrop(int n) {
        if(n == 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++)
        {
            int mid = (i + 1) / 2;
            while (dp[i - mid] < mid - 1){
                mid --;
            }
            dp[i] = Math.max(dp[i - mid] + 1, mid);
        }
        return dp[n];
    }
}
