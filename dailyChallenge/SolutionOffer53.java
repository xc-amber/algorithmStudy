
/*
* 方法1：直接遍历
* 遍历nums数组，如果等于target就记录结果++；如果大于target就结束，返回结果
* 时间复杂度分析：O(n)
* 空间复杂度分析：O(1)
* 代码如下：
* public class SolutionOffer53 {
    public int search(int[] nums, int target) {
        int res = 0;
        for (int num : nums)
        {
            if(num == target){
                res++;
            }
            if(num > target){
                break;
            }
        }
        return res;
    }
}
*
* 方法2：二分搜索
* 因为nums已经排序，为了减小时间复杂度，可以用二分搜索左边界的方法找到target再遍历
* 时间复杂度O(logn)
* 空间复杂度O(1)
* */

public class SolutionOffer53 {
    public int search(int[] nums, int target) {
        int res = 0;
        for (int num : nums)
        {
            if(num == target){
                res++;
            }
            if(num > target){
                break;
            }
        }
        return res;
    }
}
