import java.util.LinkedHashMap;
/*
* 本道题是典型的LRU算法，可以当做模板，思路不过多解释
* 时间复杂度分析：每个方法都是O(1)
* 空间复杂度分析：LinkedHashMap：O(n)，n = capacity
* */
public class LRUCache {
    LinkedHashMap<Integer, Integer> map;
    int cap;
    public LRUCache(int capacity) {
        map = new LinkedHashMap<>();
        this.cap = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.put(key, value);
            makeRecently(key);
            return;
        }
        if(cap <= map.size()){
            int oldestKey = map.keySet().iterator().next();
            map.remove(oldestKey);
        }
        map.put(key, value);
    }
    public void makeRecently(int key){
        int value = map.get(key);
        map.remove(key);
        map.put(key, value);
    }
}
