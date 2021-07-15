import java.util.Arrays;
/*
* 方法1：排序
* 先对arr进行排序，arr[0]固定为1
* 然后从i = 1开始遍历arr，如果Math.abs(arr[i] - arr[i - 1]) > 1，那么arr[i] = arr[i - 1] + 1;
* 最后返回数组最后一个数即可
* 时间复杂度分析：对arr排序O(nlogn)，遍历数组O(n)，所以总体为O(n + nlogn)即为O(nlogn)
* 空间复杂度分析：对arr排序所需的额外空间O(logn)
* 代码如下：
* public class Solution1846 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < len; i++) {
            if(Math.abs(arr[i] - arr[i - 1]) > 1){
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[len - 1];
    }
}
*
* 方法2：计数排序（参考题解）
* 由于第一个元素必须为 1，且任意两个相邻元素的差的绝对值小于等于 1，故答案不会超过 n。所以我们只需要对 arr 中值不超过n 的元素进行计数排序，而对于值超过 n 的元素，由于其至少要减小到 n，我们可以将其视作 n
* 从另一个视角来看，为了尽可能地构造出最大的答案，我们相当于是在用arr 中的元素去填补自身在 [1,n中缺失的元素。

首先，我们用一个长为 n+1 的数组nums 统计 arr 中的元素个数（将值超过 n 的元素视作 n）。然后，从1 到 n 遍历 nums 数组，若 nums[i]=0，则说明缺失元素 i，我们需要在后续找一个大于 i 的元素，将其变更为 i。我们可以用一个变量 miss 记录 nums[i]=0 的出现次数，当遇到 nums>0 时，则可以将多余的 nums[i]−1 个元素减小，补充到之前缺失的元素上。

遍历 nums 结束后，若此时 miss=0，则说明修改后的 arr 包含了 [1,n]内的所有整数；否则，对于不同大小的缺失元素，我们总是优先填补较小的，因此剩余缺失元素必然是 [n−miss+1,n] 这一范围内的miss 个数，因此答案为 n−miss
* 时间复杂度分析：遍历一次arr，遍历一次nums，整体为O(n)
* 空间复杂度分析：nums数组，O(n);
*  */
public class Solution1846 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int len = arr.length;
        int[] nums = new int[len + 1];
        for (int j : arr) {
            if (j > len) {
                nums[len]++;
            } else {
                nums[j]++;
            }
        }
        int miss = 0;
        for (int i = 1; i <= len; i++) {
            if(nums[i] == 0){
                miss++;
            }else{
                miss -= Math.min(miss, nums[i] - 1);
            }
        }
        return len - miss;
    }
}
