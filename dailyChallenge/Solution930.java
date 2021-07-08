/*
*
*
*
*
* */
public class Solution930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int index = i;
            while (sum <= goal && index < nums.length){
                sum += nums[index++];
                if(sum == goal){
                    res++;
                }
            }
        }
        return res;
    }
}
