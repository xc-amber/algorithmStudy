import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* 方法1：数组+map
* 遍历字符串，使用map统计每个字符出现的次数
* 同时使用freq数组存储出现各个次数的字符；例如字符串“tree",freq[1]={t,r},freq[2]={e}
* 当map统计次数后，在freq中删除前一个次数中的字符，加到新的次数中
* 时间复杂度分析：遍历一次字符串O(n)，n为字符串长度；拼接返回结果字符串遍历一次freq数组O(m)，m为字符串中各个字符出现的次数的种数，总体为O(m+n)
* 空间复杂度分析：O(n + m)
*
* */
public class Solution451 {
    public String frequencySort(String s) {
        char[] ch = s.toCharArray();
        List<Character>[] freq = new List[ch.length + 1];
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();
//        遍历字符串
        for (char c : ch)
        {
            map.put(c, map.getOrDefault(c, 0) + 1);
            int num = map.get(c);
//            当map中c出现的次数>1，就去当前出现次数-1的list中删除字符，并加入当前次数的list
            if(num > 1){
                freq[num - 1].remove(new Character(c));
            }
            if(freq[num] == null){
                freq[num] = new ArrayList<>();
            }
            freq[num].add(c);
        }
//        反向遍历freq数组，拼接字符串
        for (int i = freq.length - 1; i >= 0; i--)
        {
            if(freq[i] != null){
                for (Character temp : freq[i])
                {
                    for (int j = 1; j <= i; j++)
                    {
                        stringBuilder.append(temp);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
