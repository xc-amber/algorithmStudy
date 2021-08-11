import javax.naming.NamingEnumeration;
import java.util.HashMap;
import java.util.Map;
/*
* 方法1：动态规划
* 参考题解：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/solution/dong-tai-gui-hua-java-by-liweiwei1419-jc84/
* */
public class Solution446 {
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        if(len < 3){
            return 0;
        }
        int res = 0;
        Map<Long, Integer>[] dp = new Map[len];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashMap<>();
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                long diff = nums[i] - nums[j];
                if (diff > Integer.MAX_VALUE || diff < Integer.MIN_VALUE) {
                    continue;
                }
                if(dp[j].containsKey(diff)){
                    res += dp[j].get(diff);
                }
                int temp = dp[j].getOrDefault(diff, 0);
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + temp + 1);
            }
        }
        return res;
    }
}
