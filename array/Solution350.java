import java.util.ArrayList;
import java.util.HashMap;

public class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(len1 <= len2){
            for (int j : nums2) {
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
            for (int j : nums1) {
                if (map.get(j) != null) {
                    list.add(j);
                    map.put(j, map.get(j) - 1);
                    if (map.get(j) == 0) {
                        map.remove(j);
                    }
                }
            }
        }
        if(len1 > len2){
            for (int j : nums1) {
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
            for (int j : nums2) {
                if (map.get(j) != null) {
                    list.add(j);
                    map.put(j, map.get(j) - 1);
                    if (map.get(j) == 0) {
                        map.remove(j);
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
