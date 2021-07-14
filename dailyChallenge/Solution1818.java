import java.util.*;
/*
* 方法1：二分搜索
* 借用辅助数组copy复制一份nums1，对copy排序
* 在对每个nums2[i]在copy数组中找到最接近的数字
* 所以结果就是nums1[i] - nums2[i]替换成copy[index] - nums[i],它们俩的差值就为Math.abs(nums1[i] - nums2[i]) - Math.abs(copy[index] - nums2[i]))
* 所以只要找到这个差值的最大值max；把结果减去最大值即为结果了，注意取模运算
* (res - max + 1000000007) % 1000000007;是因为上面已经对res取模过了，所以res - max可能为负数
* 时间复杂度分析：复制数组nums1并进行排序O(nlogn)，n为数组长度；遍历一次数组，每次遍历中进行二分搜索O(n * logn);所以整体复杂度为O(nlogn)
* 空间复杂度分析：辅助数组O(n),n为数组长度,排序额外需要log(n),所以整体为O(n + logn)
* */
public class Solution1818 {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int res = 0;
        int max = 0;
        int[] copy = Arrays.copyOf(nums1, len);
        Arrays.sort(copy);
        for (int i = 0; i < len; i++) {
            int value = Math.abs(nums1[i] - nums2[i]);
            res = (res + value) % 1000000007;
            int index = find(copy, nums2[i]);
            max = Math.max(max, value - Math.abs(copy[index] - nums2[i]));
        }
        return (res - max + 1000000007) % 1000000007;
    }
    public int find(int[] nums1, int target){
        int left = 0;
        int len = nums1.length;
        int right = len - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if(nums1[mid] == target){
              return  mid;
            }else if(nums1[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        if(left > 0){
            if(Math.abs(nums1[left - 1] - target) <= Math.abs(nums1[left] - target)){
                return left - 1;
            }else{
                return left;
            }
        }else{
            return left;
        }
    }
}
