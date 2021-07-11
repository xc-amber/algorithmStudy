import java.util.Arrays;

public class Solution274 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int left = 0;
        int len = citations.length;
        int right = citations.length - 1;
        int res = Math.min(citations[0], len);
        while (left <= right){
            int mid = left + (right - left) / 2;
            if(len - mid >= citations[mid]){
                res = Math.max(res, citations[mid]);
                left = mid + 1;
            }else {
                res = Math.max(res, len - mid);
                right = mid - 1;
            }
        }
        return res;
    }
}
