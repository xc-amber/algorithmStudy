/*
* 1.暴力法：从1到k次，每次先用tmp变量保存nums[length - 1]的值，然后从数组右边界开始遍历，使得右边的元素=左边的元素；
* 最后使得nums[0] = temp就完成了交换
* 时间复杂度分析：需要循环k次，每次循环需要遍历数组所有元素，所以时间复杂度O(k*n)，n为数组长度；
* 空间复杂度分析：O(1);
* 该方法运行超时；
* 由方法2分析令k %= length处理
* 代码如下：
*     public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        if(k == 0){
            return;
        }
        for (int i = 1; i <= k; i++) {
            int temp = nums[len - 1];
            for (int j = len - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

* 方法2：
* 新建一个数组int[] numsNew长度和原数组相等；
* 要右转k步，所以从原数组的nums[length - k]开始遍历到nums[length - 1],依次赋值给numsNew的前k个元素
* 再从原数组的nums[0]遍历到nums[length - k - 1];依次赋值给numsNew[k]d到numsNew[length - 1];
* 该方法遇到测试用例k大于nums的长度时，就会有数组越界的问题；k大于length时，相当于已经旋转超过一轮了
* 由超过一轮这个想法发现如果k == length的话，相当于没有翻转，直接返回就行
* 所以如果k> length的话，只要令k %= length再处理就行
* 时间复杂度分析:遍历一次数组长度，O(n) n为数组长度；
* 空间复杂度分析：新建了一个数组，O(n)；
* 代码如下：
*     public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] numsNew = new int[len];
        k %= len;
        if(k == 0){
            return;
        }
        System.arraycopy(nums, len - k, numsNew, 0, k);
        System.arraycopy(nums, 0, numsNew, k, len - k);
        System.arraycopy(numsNew, 0, nums, 0, len);
    }
*
* 方法3：看了题解的
* 多次反转
* 现将整个数组反转
* 再反转前k个
* 最后反转剩余的
* 举例：原始数组：1,2,3,4,5,6,7，k=3；
* 全部反转后为7,6,5,4,3,2,1
* 反转前3个：5,6,7,4,3,2,1
* 反转剩余的：5,6,7,1,2,3,4
* 时间复杂度分析：遍历2次数组，O(2n) = O(n) ,n为数组长度；
* 空间复杂度O(1)
* */
public class Solution189 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        if(k == 0){
            return;
        }
        reverse(nums, 0, len - 1);
        reverse(nums, 0 , k - 1);
        reverse(nums, k, len - 1);
    }
    public void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
