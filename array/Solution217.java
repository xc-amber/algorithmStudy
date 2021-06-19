import java.util.Arrays;
import java.util.HashSet;

/*
* Leetcode No.217
* 方法1：
* 理解题目意思，数组元素内只要有重复元素就返回true;否则返回false;
* 因为重复元素很快想到数据结构HashSet；
* 遍历数组，将数组的元素依次加入set内
* 最后比较set.size()和nums.length是否相等即可
* 时间复杂度分析：遍历一次数组，O(n),n为数组长度
* 空间复杂度：O(n)；
* 第一次写的代码如下：
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() != nums.length;
    }
*以上遍历数组元素存入set的过程也可以直接利用Java8的 stream 的 distinct 和 count 算子。
代码如下：
    public boolean containsDuplicate(int[] nums) {
        return IntStream.of(nums).distinct().count() != nums.length;
    }
* 后面想了下如果遍历途中遇到了重复元素就没必要再遍历下去了，直接return true就行了；
* 所以优化后代码如下：
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }
为什么不用list，如果用list的话，需要在每次遍历时判断下list.contains(num)，相当于每次需要遍历一次list，时间复杂度会更高
*
* 方法2：
* 先对数组进行排序
* 从index = 1开始遍历数组，如果右边的元素等于左边的元素直接返回true；
* 时间复杂度分析：使用一次Arrays.sort的时间复杂度为O（nlogn）,n为数组长度，循环体中遍历数组，最差情况时间复杂度为O(n);
* 空间复杂度分析使用Arrays.sort的空间复杂度为O(logn)
* 代码如下：
    public boolean containsDuplicate(int[] nums) {
         Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]){
                return true;
            }
        }
        return false;
    }
* */

public class Solution217 {
    public boolean containsDuplicate(int[] nums) {
         Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]){
                return true;
            }
        }
        return false;
    }
}
