/*
* 方法1：新数组+双指针
* 双指针分别指向nums1，nums2的最左边，比较指针的数，较小的写入copy数组中并右移
* 时间复杂度分析：O(m + n)
* 空间复杂度分析：O(m + n)
* */
public class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] copy = new int[nums1.length];  //copy数组用来记录排序后的结果
        int index1 = 0; //index1指向nums1的最左边
        int index2 = 0; //index2指向nums2的最左边
        int index3 = 0; //index3指向copy的最左边
        while (index1 < m || index2 < n){
//          当nums1或nums2已经遍历完了，copy数组直接添加另外一个剩余未遍历的，否则较小的写入copy数组中并右移
            if(index1 == m){
                copy[index3++] = nums2[index2++];
            }else if(index2 == n){
                copy[index3++] = nums1[index1++];
            }else if(nums1[index1] <= nums2[index2]){
                copy[index3++] = nums1[index1++];
            }else{
                copy[index3++] = nums2[index2++];
            }
        }
        System.arraycopy(copy, 0, nums1, 0, m + n);
    }
}
