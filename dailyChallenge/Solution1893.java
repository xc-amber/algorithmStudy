import java.util.ArrayList;
import java.util.List;

public class Solution1893 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                if(!list.contains(i)){
                    list.add(i);
                }
            }
        }
        for (int i = left; i <= right; i++) {
            if(!list.contains(i)){
                return false;
            }
        }
        return true;
    }
}
