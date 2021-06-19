import java.util.ArrayList;
import java.util.HashMap;

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(len1 <= len2){
            for (int num : nums2) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (int num : nums1) {
                if (map.get(num) != null) {
                    list.add(num);
                    map.put(num, map.get(num) - 1);
                    if (map.get(num) == 0) {
                        map.remove(num);
                    }
                }
            }
        }
        if(len1 > len2){
            for (int num : nums1) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (int num : nums2) {
                if (map.get(num) != null) {
                    list.add(num);
                    map.put(num, map.get(num) - 1);
                    if (map.get(num) == 0) {
                        map.remove(num);
                    }
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
