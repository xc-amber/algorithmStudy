import java.util.HashMap;
import java.util.Map;

/*
* 方法1：暴力法
* 从下标0开始遍历，往后加几个数可以等于goal
* 时间复杂度：O(n*n)
* 空间复杂度:O(1)
* 代码如下：
* class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int index = i;
            while (sum <= goal && index < nums.length){
                sum += nums[index++];
                if(sum == goal){
                    res++;
                }
            }
        }
        return res;
    }
}
*
* 方法2：哈希表+前缀和（参考了题解）
* 假设原数组的前缀和数组为sum，且子数组 (i,j]的区间和为 goal，那么sum[j]-sum[i]=goal。因此我们可以枚举 j ，每次查询满足该等式的 i的数量。

具体地，我们用哈希表记录每一种前缀和出现的次数，假设我们当前枚举到元素 nums[j]，

我们只需要查询哈希表中元素 sum[j]−goal 的数量即可，这些元素的数量即对应了以当前 j 值为右边界的满足条件的子数组的数量

最后这些元素的总数量即为所有和为goal 的子数组数量。

在实际代码中，我们实时地更新哈希表，以防止出现 i ≥j 的情况
*
* 时间复杂度：O(n)，其中 n为给定数组的长度。对于数组中的每个元素，我们至多只需要插入到哈希表中一次。

空间复杂度：O(n)，其中 n 为给定数组的长度。哈希表中至多只存储 O(n) 个元素。



* */
public class Solution930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }
}
