import java.util.Arrays;
import java.util.HashMap;

/*
* 方法1：哈希表
* 遍历一次数组将数组中的元素存入map，map的key为元素值，value为同一个元素值出现的次数
* 在遍历的同时，算出数组中的最大值max；这样就可以在后序计算某个元素和另外一个元素的和是不是2的幂次方时有用；
* 例如数组中的最大值为4，在计算元素1和其他元素的和是不是2的幂次方时只需要计算到2的3次方；因为数组中最大值才为4,元素1和数组中任意元素和小于等于5
* 也就是我们后面遍历2的n次方时，最大值就是当前值加上数组的最大值
* 然后再遍历数组，依次从map中取出遍历的元素cur防止重复计算，再看map中key为“2的n次方-cur”的值就计算到结果中
* 时间复杂度分析：第一次遍历数组O(n),n为数组长度；第二次遍历数组O(n),第二次遍历数组中遍历2的n次方O(log(cur + max)),最坏情况是O(log2*max)
* 所以整体时间复杂度为O(n + n*log(2*max)),本题中max最大为2的20次方
* 空间复杂度分析：O(n)
* */
public class Solution1711 {
    public int countPairs(int[] deliciousness) {
        int res = 0; //用来计算结果的
        int max = Integer.MIN_VALUE; //记录数组的最大值
        HashMap<Integer, Integer> map = new HashMap<>();
//        遍历数组，更新最大值，将数组中的元素值num存入map，map的key为num，value为num出现的次数
        for (int num : deliciousness)
        {
            max = Math.max(max, num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
//        第二次遍历数组
        for (int cur : deliciousness)
        {
//            从map中取出遍历的元素cur，防止重复计算
            map.put(cur, map.get(cur) - 1);
//            遍历2的n次方，最大值为当前值cur+数组最大值max
            for (int i = 1; i <= cur + max; i *= 2) {
//                key为“2的n次方-cur”的值就计算到结果中
                if(map.containsKey(i - cur)){
                    res += map.get(i - cur);
                    res  %= 1000000007;
                }
            }
        }
        return res;
    }
}
