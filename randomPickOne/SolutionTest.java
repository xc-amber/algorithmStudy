import java.util.Arrays;
public class SolutionTest {
    public int findLimit(int[] nums, int cnt){
        int len = nums.length;
        int limit = 0;
        Arrays.sort(nums);
//        前缀和数组:arr[i]表示的是第0个位置到第i个位置的总和
        int[] arr = new int[len];
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }
//        如果前缀和数组最后一个元素（也就是nums数组总和）<= cnt，返回-1
        if(arr[len - 1] <= cnt){
            return -1;
        }
//        排序后nums[0]>= cnt,那么limit肯定是cnt的平均数
        if(nums[0] >= cnt){
            return cnt / len;
        }
//        取cnt的平均数，假设limit = cnt / len，那么总和肯定<= cnt;所以该值就是确定的limit最小的值
        int average = cnt / len;
//        开始循环
        while (average <= cnt){
            int count;
//            二分查找averge在排序后的nums的位置index，
            int index = findIndex(nums, average);
//            根据前缀和数组，可以快速得到0~index的和就为arr[index]，（index + 1） ~（len - 1）的和就是average*个数
            count = arr[index] + (len - 1 - index) * average;
//            如果count <= cnt, average++,否则limit = average - 1
            if(count <= cnt){
                average++;
            }else {
                limit = average - 1;
                break;
            }
        }
        return limit;
    }
//  在排序nums数组中，二分查找nums[i] <= target, nums[i + 1] > target,返回i
    public static int findIndex(int[] nums, int target){
        int index = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;  //这么求mid是防止整数溢出
            if(nums[mid] > target){
                right = mid - 1;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else {
                left = mid + 1;
            }
        }
        return right < 0? left : right;
    }
}
