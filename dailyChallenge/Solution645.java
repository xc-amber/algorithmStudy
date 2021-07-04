import java.util.Arrays;
import java.util.HashMap;

/*
* 方法1： map
* 先遍历原数组记录每个数字出现的次数，当次数大于1时，需要被修改的数字就找到了
* 再遍历1到n，如果map的key中不包含，那么这个值就是修改后的值
* 时间复杂度分析O(2*n)即为O(n)
* 空间复杂度O(n)
* 代码如下：
* class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if(map.get(nums[i]) > 1){
                res[0] = nums[i];
            }
        }
        for (int i = 1; i <= nums.length ; i++) {
            if(!map.keySet().contains(i)){
                res[1] = i;
                break;
            }
        }
        return res;
    }
}
*
* 方法2：排序+二分搜索
* 对原数组排序，然后只要相邻位置数字相等，那么需要被修改的数字就找到了
* 再从1到n,在数组中进行二分搜索，搜不到的数即为修改后的数字
* 时间复杂度分析：sort排序O(nlogn),找相邻位置数字相等O(n),从1到n,在数组中进行二分搜索O(n*logn),所以总的复杂度为O(nlogn + n)
* 空间复杂度分析：sort排序需要的额外空间O(logn)
*
* */

public class Solution645 {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]){
                res[0] = nums[i];
                break;
            }
        }
        for (int i = 1; i <= nums.length ; i++) {
            if(Arrays.binarySearch(nums, i) < 0){
                res[1] = i;
                break;
            }
        }
        return res;
    }
}
