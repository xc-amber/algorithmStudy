import java.util.Arrays;

/*
* 方法1：DP
* 求最长递增子序列，动态规划是常用方法
* 定义dp[i] 的值代表 nums 前 i 个数字的最长递增子序列长度
* 初始状态，对于每个位置的元素，它自己本身肯定是一个子序列，所以初始的dp[i]均为1；
* 寻找状态转移方程：
* 转移方程： 设 j∈[0,i)，考虑每轮计算新 dp[i] 时，遍历 [0,i)列表区间，做以下判断：（这段描述参考了题解。不想画图，表达能力差）
1、当 nums[i] > nums[j] 时： nums[i]可以接在 nums[j]之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j]+1 ；
2、当 nums[i] <= nums[j]时： nums[i]无法接在 nums[j]之后，此情况上升子序列不成立，跳过。
上述所有 1. 情况 下计算出的 dp[j]+1 的最大值，为直到 i的最长上升子序列长度（即 dp[i] ）。实现方式为遍历 j 时，每轮执行 dp[i] = max(dp[i], dp[j] + 1)
转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)
* 时间复杂度分析O(n*n),n为数组长度，双重for循环，长度最坏情况都是n
* 空间复杂度分析O(n)
* 代码如下：
* public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = Integer.MIN_VALUE;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 1 : max;
    }
}
*
*
* 方法2：贪心+二分(参考题解了链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/)
* 解题思路：
降低复杂度切入点： 解法一中，遍历计算 dp 列表需 O(N)，计算每个 dp[k] 需 O(N)。

动态规划中，通过线性遍历来计算 dp 的复杂度无法降低；
每轮计算中，需要通过线性遍历 [0,k) 区间元素来得到 dp[k] 。我们考虑：是否可以通过重新设计状态定义，使整个 dp为一个排序列表；这样在计算每个 dp[k]时，就可以通过二分法遍历 [0,k) 区间元素，将此部分复杂度由 O(N) 降至 O(logN)。
设计思路：

新的状态定义：
我们考虑维护一个列表 tails，其中每个元素 tails[k] 的值代表 长度为 k+1的子序列尾部元素的值。
如 [1,4,6] 序列，长度为 1,2,3 的子序列尾部元素值分别为 tails = [1,4,6]。
状态转移设计：
设常量数字 N，和随机数字 x，我们可以容易推出：当 N越小时，N<x 的几率越大。例如： N=0肯定比 N=1000更可能满足 N<x。
在遍历计算每个 tails[k]，不断更新长度为 [1,k] 的子序列尾部元素值，始终保持每个尾部元素值最小 （例如 [1,5,3]， 遍历到元素 5时，长度为 2 的子序列尾部元素值为 5；当遍历到元素 3 时，尾部元素值应更新至 3，因为 3 遇到比它大的数字的几率更大）。
tails 列表一定是严格递增的： 即当尽可能使每个子序列尾部元素值最小的前提下，子序列越长，其序列尾部元素值一定更大。
反证法证明： 当 k < i，若 tails[k] >= tails[i]，代表较短子序列的尾部元素的值 > 较长子序列的尾部元素的值。这是不可能的，因为从长度为 i 的子序列尾部倒序删除 i-1个元素，剩下的为长度为 k的子序列，设此序列尾部元素值为 v，则一定有 v<tails[i]（即长度为 k 的子序列尾部元素值一定更小）， 这和 tails[k]>=tails[i] 矛盾。
既然严格递增，每轮计算 tails[k]时就可以使用二分法查找需要更新的尾部元素值的对应索引 i。
算法流程：

状态定义：

tails[k]的值代表 长度为 k+1子序列 的尾部元素值。
转移方程： 设 res为 tails当前长度，代表直到当前的最长上升子序列长度。设 j∈[0,res)，考虑每轮遍历 nums[k]时，通过二分法遍历 [0,res)列表区间，找出 nums[k]的大小分界点，会出现两种情况：

区间中存在 tails[i] > nums[k] ： 将第一个满足 tails[i] > nums[k] 执行 tails[i] = nums[k] ；因为更小的 nums[k]后更可能接一个比它大的数字（前面分析过）。
区间中不存在 tails[i] > nums[k]： 意味着 nums[k]可以接在前面所有长度的子序列之后，因此肯定是接到最长的后面（长度为 res ），新子序列长度为 res + 1。
初始状态：

令 tails列表所有值 =0。
返回值：

返回 res ，即最长上升子子序列长度。
复杂度分析：
时间复杂度 O(NlogN) ： 遍历 nums 列表需 O(N)O(N)，在每个 nums[i]二分法需 O(logN)。
空间复杂度 O(N) ： tails 列表占用线性大小额外空间。
*
* */

public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] tails = new int[len];
        int res = 0;
        for (int num : nums) {
            int left = 0;
            int right = res;
            while (left < right){
                int mid = left + (right - left) / 2;
                if(tails[mid] < num){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }
            tails[left] = num;
            if(right == res){
                res++;
            }
        }
        return res;
    }
}

