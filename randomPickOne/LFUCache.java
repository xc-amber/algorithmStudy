import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
/*
 * 本道题是典型的LFU算法，可以当做模板，思路不过多解释
 * 时间复杂度分析：每个方法都是O(1)
 * 空间复杂度分析：keyToValue,keyToFreq,freqToKey：O(n)，n = capacity
* */
public class LFUCache {
    Map<Integer, Integer>  keyToValue;
    Map<Integer, Integer>  keyToFreq;
    Map<Integer, LinkedHashSet<Integer>> freqToKey;
    int cap;
    int minFreq;
    public LFUCache(int capacity) {
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
        this.cap = capacity;
    }

    public int get(int key) {
        if(!keyToValue.containsKey(key)){
            return -1;
        }
        increaseFreq(key);
        return keyToValue.get(key);
    }

    public void put(int key, int value) {
        if(cap <= 0){
            return;
        }
        if(keyToValue.containsKey(key)){
            keyToValue.put(key, value);
            increaseFreq(key);
            return;
        }
        if(cap <= keyToValue.size()){
            removeMinFreq();
        }
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKey.putIfAbsent(1, new LinkedHashSet<>());
        freqToKey.get(1).add(key);
        minFreq = 1;
    }
    public void increaseFreq(int key){
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKey.get(freq).remove(key);
        freqToKey.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKey.get(freq + 1).add(key);
        if(freqToKey.get(freq).isEmpty()){
            freqToKey.remove(freq);
            if(freq == minFreq){
                minFreq++;
            }
        }
    }
    public void removeMinFreq(){
        LinkedHashSet<Integer> set = freqToKey.get(minFreq);
        int deletedKey = set.iterator().next();
        set.remove(deletedKey);
        if(set.isEmpty()){
            freqToKey.remove(minFreq);
        }
        keyToFreq.remove(deletedKey);
        keyToValue.remove(deletedKey);
    }
}
