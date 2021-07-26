import java.util.*;
/*
* 方法1：贪心 + 二分查找（参考题解）
* 记数组 target 的长度为 n，数组arr 的长度为 m。

根据题意，target 和 arr 这两个数组的公共子序列越长，需要添加的元素个数也就越少。因此最少添加的元素个数为 n 减去两数组的最长公共子序列的长度。

求最长公共子序列是一个经典问题，读者可参考「1143. 最长公共子序列的官方题解」。但是，这一做法的时间复杂度是 O(nm)的，在本题的数据范围下无法承受，我们需要改变思路。

由于 target 的元素互不相同，我们可以用一个哈希表记录 target 的每个元素所处的下标，并将 arr 中的元素映射到下标上，对于不存在于 target 中的元素，由于其必然不会在最长公共子序列中，可将其忽略。

我们使用示例 2 来说明，将 arr 中的元素转换成该元素在 target 中的下标（去掉不在 target 中的元素 7），可以得到一个新数组arr ′=[1,0,5,4,2,0,3]

若将target 也做上述转换，这相当于将每个元素变为其下标，得target ′=[0,1,2,3,4,5]

则求原数组的最长公共子序列等价于求上述转换后的两数组的最长公共子序列。

注意到target ′是严格单调递增的，因此 arr ′在最长公共子序列中的部分也必须是严格单调递增的，因此问题可进一步地转换成求 arr ′的最长递增子序列的长度。这也是一个经典问题，可以参考「300. 最长递增子序列的官方题解」，使用贪心和二分查找的方法得到最长递增子序列的长度。

* */
public class Solution1713 {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            if(map.containsKey(i)){
                list.add(map.get(i));
            }
        }
        int[] newArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArr[i] = list.get(i);
        }
        int[] tails = new int[list.size()];
        tails[0] = Integer.MAX_VALUE;
        int res = 0;
        for (int num : newArr) {
            int left = 0;
            int right = res;
            while (left < right){
                int mid = left + (right - left) / 2;
                if(tails[mid] < num){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }
            tails[left] = num;
            if(right == res){
                res++;
            }
        }
        return target.length - res;
    }
}
