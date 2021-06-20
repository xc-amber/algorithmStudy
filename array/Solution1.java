import java.util.Arrays;
import java.util.HashMap;

/*
* Leetcode No.1
* 方法1：两次循环
* 最简单想到的方法，两次循环遍历数组，如果相加==target，记录下i和j即可
* 时间复杂度分析：遍历两次数组O(n*n)
* 空间复杂度分析：一个长度为2的结果数组O(2)，所以整体空间复杂度为O(1)
* 代码如下：
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

*
*方法2：哈希表
* 建立一个哈希表，key为数组的元素值，value为index下标
* 遍历一次数组，存入哈希表中
* 遍历一次数组，在哈希表中查找target - nums[i]的key是否存在且value不等于i本身
* 如果存在就记录返回
* 时间复杂度分析：遍历了两次数组O(2n)，map操作皆为O(1),所以整体时间复杂度为O(n)
* 空间复杂度分析：一个长度为2的结果数组O(1),一个size为n的map，所以空间复杂度为O(n)
* 代码如下：
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            }
        }
        return result;
    }

* 从上面的代码分析我们遍历了两次数组，第一次是往map中存数据，第二次是查询key是否存在
* 且第二次找到key的时候还要判断是不是元素本身的下标
* 这些操作是否可以在一次遍历就完成呢
* 在第一次遍历数组存数据时，我们直接查找target - nums[i]的key是否存在，如果查到了就是这个元素在之前我们添加过了，也不可能找到元素本身的下标了
* 时间复杂度分析：遍历了两次数组O(n)，map操作皆为O(1),所以整体时间复杂度为O(n)
* 空间复杂度分析：一个长度为2的结果数组O(1),一个size为n的map，所以空间复杂度为O(n)
* 代码如下：
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }
* */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
