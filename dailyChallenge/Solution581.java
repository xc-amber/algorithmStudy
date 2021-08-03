import java.util.Arrays;
/*
* 方法1：排序
* 对nums数组元素排序得到新的数组arr
* 在从左右两个边界对比nums[i]和arr[i]是否相等，如果不相等则找到边界
* 时间复杂度分析：O(nlogn)
* 空间复杂度分析：O(n)
* 代码如下：
* public class Solution581 {
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < nums.length && nums[left] == arr[left]){
            left++;
        }
        while (right >= 0 && nums[right] == arr[right]){
            right--;
        }
        return left <= right ? right - left + 1 : 0;
    }
}
*
* 方法2：
* 这道题要求我们找到一个连续子数组，将它排序后，使整个数组成为一个升序数组，并要求这段子数组的长度最短。数组被分为 A, B, C三段，且有：A, C 都是升序序列。
* B 中任一元素均大于 A 中元素，且小于 C中任一元素。
* 这里以寻找 C 的边界 r（ B 的右边界）为例，上面分析过，C 中元素必须全部大于 B 中元素，也即 C 中的最小值大于等于 C 之前全部元素的最大值
* 那么从右往左，第一个不满足这个条件的元素就是边界。我们利用 max[i] 数组记录 [0, i - 1 区间内元素的最大值，利用 min[i] 数组记录 [i, n - 1]区间内元素的最小值。
* 那么如果我们从后往前遍历，找到的第一个 min[i] < max[i] 的位置 i，就是 B, C之间的边界。寻找 A, B之间边界 l 同理。
* 时间复杂度分析：O(n)
* 空间复杂度分析：O(n)
* */
public class Solution581 {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int[] min = new int[len + 1];
        int[] max = new int[len + 1];
        Arrays.fill(min, Integer.MIN_VALUE);
        Arrays.fill(max, Integer.MAX_VALUE);
        for (int i = 1; i <= nums.length; i++) {
            min[i] = Math.max(nums[i - 1], min[i - 1]);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            max[i] = Math.min(nums[i], max[i + 1]);
        }
        int left = -1;
        int right = -1;
        for (int i = 0; i < len; i++) {
            if(min[i] > max[i]){
                left = i - 1;
                break;
            }
        }
        for (int i = len; i >= 0; i--) {
            if(min[i] > max[i]){
                right = i;
                break;
            }
        }
        return left == -1 ? 0: right - left + 1;
    }
}
