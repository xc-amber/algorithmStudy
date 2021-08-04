import java.util.Arrays;
/*
* 方法1：排序+二分
* 我们从左向右遍历数组，固定 i, j两个位置，数值分别对应 a, b两条边，查找第三条边 c，只需满足 a + b > c（数组有序，必有 a + c > b, b + c > a）。
* 所以我们利用二分法，找到最后一个小于 a + b的数值位置 index，此时 [j + 1, index]区间内的数值均可作为第三条边。
* 时间复杂度分析：O(n*n*logn)
* 空间复杂度分析：O(logn),logn为排序额外的栈空间
* 代码如下：
* public class Solution611 {
    public int triangleNumber(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                int index = j;
                while (left <= right){
                    int mid = left + (right - left) / 2;
                    if(nums[mid] < nums[i] + nums[j]){
                        index = mid;
                        left = mid + 1;
                    }else {
                        right = mid -1;
                    }
                }
                res += index - j;
            }
        }
        return res;
    }
}
*
* 方法2：排序+双指针
* 在解法 1 中，我们固定 i, j 两个位置作为前两条边，每次二分法搜索第三条边的合法位置。
* 但实际上，由于我们数组有序，所以当我们固定了 i，也就是第一条边后，每次第三条边的合法位置是根据第二条边的增大而右移的。
* 因此，我们可以只固定一个位置 i，并在移动位置 j 的过程中，利用双指针维护第三条边的合法范围。
* 代码中，我们利用 [left, right]维护第三条边的合法位置
* 时间复杂度分析：O(n*n)
* 空间复杂度分析：O(logn),logn为排序额外的栈空间
* */
public class Solution611 {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = 0;
        // [left, right] 为第三条边的合法位置
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; ++i) {
            right = i + 2;
            for (int j = i + 1; j < n; ++j) {
                left = j + 1;
                while(right < n && nums[right] < nums[i] + nums[j]) right++;
                res += Math.max(right - left, 0);
            }
        }
        return res;
    }
}
