import java.util.Arrays;
/*
* 方法1：排序+贪心
* 因为要买最多的雪糕，所以应该买最便宜的雪糕；然后再买次便宜的雪糕
* 所以把costs数组从小到大排序，从左到右买就行了
* 时间复杂度分析：sort排序O(nlogn)，买雪糕最大为O(n)，最坏情况是可以买下所有雪糕，所以复杂度为O(nlog)
* 空间复杂度分析：O(logn)，sort排序的额外空间
*
* */
public class Solution1833 {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int num = 0;
        for (int cost : costs)
        {
            coins -= cost;
            if(coins >= 0){
                num++;
            }else{
                return num;
            }
        }
        return num;
    }
}
