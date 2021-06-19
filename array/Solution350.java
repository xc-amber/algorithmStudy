import java.util.Arrays;
import java.util.HashMap;

/*
* Leetcode No.350
* 方法1：哈希表
* 先将长度较长的数组元素存入哈希表map，Key为数组元素值，Value为数组元素的次数
* 再遍历长度较短的数组，如果在map中存在，则在结果中记录；注意：记录一次结果后，map的value需要减1；如果value == 0，需要删除key
* 时间复杂度分析：map的操作时间复杂度为O(1)，遍历两个数组的长度 O(m + n)   m,n分别为数组的长度；
* 空间复杂度分析：额外使用一个map(长度为长的数组)，一个arr数组（长度为短的数组）；O(m + n)；该方法要优化空间的话，把短数组存入map
* 代码如下：
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if(len1 > len2){
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[len1];
        int index = 0;
        for (int num : nums2) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (int num : nums1) {
                if (map.get(num) != null) {
                    arr[index++] = num;
                    map.put(num, map.get(num) - 1);
                    if (map.get(num) == 0) {
                        map.remove(num);
                    }
                }
            }
            return Arrays.copyOfRange(arr, 0, index);

*
*方法2：排序+双指针
*将两个数组进行排序
* 创建指针index1指向nums1的起始
* 创建指针index2指向nums2的起始
* 比较nums1[index1]和nums2[index2],如果相等，index1++，index2++；如果不相等，元素较小的对应的指针++右移
* 当任何一个指针超出边界就停止循环
* 时间复杂度分析：对两个数组的排序O(mlogm + nlogn), m,n为数组长度；遍历两个数组O(m + n),所以总的时间复杂度为O(mlogm + nlogn)
* 空间复杂度分析，创建了一个arr[Math.min(len1, len2)]，所以为O(min(m,n))
*代码如下：
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] arr = new int[Math.min(len1, len2)];
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        while (index1 < len1 && index2 < len2){
            if(nums1[index1] > nums2[index2]){
                index2++;
            }else if(nums1[index1] == nums2[index2]){
                arr[index3++] = nums1[index1];
                index1++;
                index2++;
            }else {
                index1++;
            }
        }
        return Arrays.copyOfRange(arr, 0, index3);
    }
* */

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] arr = new int[Math.min(len1, len2)];
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        while (index1 < len1 && index2 < len2){
            if(nums1[index1] > nums2[index2]){
                index2++;
            }else if(nums1[index1] == nums2[index2]){
                arr[index3++] = nums1[index1];
                index1++;
                index2++;
            }else {
                index1++;
            }
        }
        return Arrays.copyOfRange(arr, 0, index3);
    }
}
