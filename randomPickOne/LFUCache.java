import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
/*
 * 本道题是典型的LFU算法，可以当做模板，思路不过多解释
 * 时间复杂度分析：每个方法都是O(1)
 * 空间复杂度分析：keyToValue,keyToFreq,freqToKey：O(n)，n = capacity
* */
public class LFUCache {
    Map<Integer, Integer>  keyToValue; //key到value的映射
    Map<Integer, Integer>  keyToFreq;  //key到Freq的映射
    Map<Integer, LinkedHashSet<Integer>> freqToKey;  //Freq到keySet的映射，核心数据结构
    int cap;  //容量
    int minFreq;  //最小频次
    public LFUCache(int capacity) {
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
        this.cap = capacity;
    }

    public int get(int key) {
//        如果KT表中没有的话返回-1
        if(!keyToValue.containsKey(key)){
            return -1;
        }
//        因为get操作了，所以将该key的freq加1
        increaseFreq(key);
        return keyToValue.get(key);
    }

    public void put(int key, int value) {
        if(cap <= 0){
            return;
        }
//        若key已存在，修改对应的value即可
        if(keyToValue.containsKey(key)){
            keyToValue.put(key, value);
//            put操作，将该key的freq加1
            increaseFreq(key);
            return;
        }
//        如果容量不够了，删除freq最小的key
        if(cap <= keyToValue.size()){
            removeMinFreq();
        }
//        插入KT表
        keyToValue.put(key, value);
//        插入KF表
        keyToFreq.put(key, 1);
//        插入FK表
        freqToKey.putIfAbsent(1, new LinkedHashSet<>());
        freqToKey.get(1).add(key);
//        插入新key后最小的freq肯定是1
        minFreq = 1;
    }
    public void increaseFreq(int key){
        int freq = keyToFreq.get(key);
//        更新KF表
        keyToFreq.put(key, freq + 1);
//        更新FK表，将key从freq对应的keySet中删除
        freqToKey.get(freq).remove(key);
//        freq+1，并将key加入freq + 1的keySet中
        freqToKey.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKey.get(freq + 1).add(key);
//        如果freq对应的keySet变为空了，FK表删除该freq
        if(freqToKey.get(freq).isEmpty()){
            freqToKey.remove(freq);
//            如果这个freq正好为minFreq，那么minFreq = freq + 1;
            if(freq == minFreq){
                minFreq++;
            }
        }
    }
    public void removeMinFreq(){
//        FK表中获取minFreq对应的keySet
        LinkedHashSet<Integer> keySet = freqToKey.get(minFreq);
//        keySet中找到最旧的deletedKey
        int deletedKey = keySet.iterator().next();
//        keySet中删除deletedKey
        keySet.remove(deletedKey);
//        如果keySet变为空了，KF表中删除minFreq，此处无需更新minFreq;因为removeMinFreq方法只有在put时会调用，在put新的缓存时，会将minFreq变为1
        if(keySet.isEmpty()){
            freqToKey.remove(minFreq);
        }
//        更新KF表
        keyToFreq.remove(deletedKey);
//        更新KV表
        keyToValue.remove(deletedKey);
    }
}
