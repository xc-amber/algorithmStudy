import java.util.Arrays;
/*
* 方法1：排序+贪心+双指针
* 根据题目要求最大数对和的最小值，那么就只能第一大的值对数分配第一小的值，第二大的值对数分配第二小的值，以此类推
* 所以第一步是把nums排序
* 然后按第一步的思路就是比较首尾相加的值了
* 可以用左指针指向左端，右指针指向右端，每次相加与max比较后，left++，right--；依次比较到left = right时退出得到最终结果
* 时间复杂度分析：排序O(nlogn),首尾相加比较最大值O(n / 2);所以最终的复杂度为O(nlogn)
* 空间复杂度分析：排序额外空间O(logn)
*
* */
public class Solution1877 {
    public int minPairSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = len - 1;
        int max = 0;
        while (left < right){
            max = Math.max(nums[left] + nums[right], max);
            left++;
            right--;
        }
        return max;
    }
}
