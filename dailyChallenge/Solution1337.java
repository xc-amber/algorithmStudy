import java.util.*;

public class Solution1337 {
    public int[] kWeakestRows(int[][] mat, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            map.put(i, getSum(mat[i]));
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> {
            if (map.get(o1).equals(map.get(o2))) {
                return o1 - o2;
            } else {
                return map.get(o1) - map.get(o2);
            }
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    public int getSum(int[] arr){
        int sum = 0;
        int index = 0;
        while (index < arr.length && arr[index] == 1 ){
            sum += arr[index];
            index++;
        }
        return sum;
    }
}
