/*
* 方法1：二分搜索左边界的问题
* 时间复杂度分析：O(logn)
* 空间复杂度分析:O(1)
*
* */

/*public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(isBadVersion(mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
}*/
