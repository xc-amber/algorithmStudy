/*
* Leetcode No.26
* 题目意思是根据返回的长度, 它会按如下方式打印出该长度范围内 的所有元素，所以只需要修改长度范围的值
for (int i = 0; i < len; i++) {
print(nums[i]);
}
* 采用双指针的思路：
* index指针从下标0开始
* start指针从下标1开始，当start< nums.length时，比较nums[start] 和 nums[index]是否相等
* if(nums[start] != nums[index])，index就加1，然后再把nums[start]的值赋给nums[index]
* start指针再继续加1右移，接下来再与nums[index]进行比较
* 当start已经超出数组右边界，返回index + 1即为去重后的长度；

* */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int index = 0;
        int start = 1;
        while (start < nums.length){
            if(nums[start] != nums[index]){
                index++;
                nums[index] = nums[start];
            }
            start++;
        }
        return index + 1;
    }
}
