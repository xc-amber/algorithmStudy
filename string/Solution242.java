import java.util.HashMap;

public class Solution242 {
    public static boolean isAnagram(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char value : sArr) {
        map1.put(value, map1.getOrDefault(value, 0)+1);
        }
        for (char value : tArr) {
            map2.put(value, map2.getOrDefault(value, 0)+1);
        }
        if(map1.size() != map2.size()){
            return false;
        }
        for (Character c : map1.keySet()) {
            if(map2.get(c) == null || !map1.get(c).equals(map2.get(c))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        isAnagram("aa", "a");
    }
}
