import java.util.*;
/*
*
* 方法一：list+哈希集合
* 思路见注释
* 时间复杂度分析：遍历orders记录食物清单和tableMap：O(n)，n为点单的总数；对食物清单进行排序O(mlogm)，m为菜的总数;对桌号排序O(nlogn);
* 最后记录结果的循环为O(n*m),所以总体为O(n+mlogm+nlogn+n*m)
* 空间复杂度分析：food记录食物清单O(m),m为菜的总数，tableMap记录桌号与对应的菜O(n),n为点单的总数
*
* */

public class Solution1418 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();  //记录返回的结果
        List<String> food = new ArrayList<>(); //记录点单的食物清单
        Map<String, Map<String, Integer>> tableMap = new HashMap<>(); //记录key为桌号，value的map中key为食物名，value为数量
        for (List<String> order : orders) {
//            如果食物清单中没有才加进来，因为后面要排序所以还是用list，没用set
            if(!food.contains(order.get(2))){
                food.add(order.get(2));
            }
//            如果tableMap中没有这个桌子的点单记录，就new一个map赋给value
            tableMap.computeIfAbsent(order.get(1), k -> new HashMap<>());
//            获取这个桌子的点单记录，将新点的菜品加入点单记录
            Map<String, Integer> temp = tableMap.get(order.get(1));
            temp.put(order.get(2), temp.getOrDefault(order.get(2), 0) + 1);
            tableMap.put(order.get(1), temp);
        }
//        对食物清单进行排序
        Collections.sort(food);
//        new一个返回结果的第一个食物清单list
        List<String> list = new ArrayList<>();
//        完成返回结果的第一个list
        list.add("Table");
        list.addAll(food);
        res.add(list); //添加到结果里
//        获取点单的桌号并排序
        List<Integer> keyList = new ArrayList<>();
        for (String s : tableMap.keySet()) {
            keyList.add(Integer.parseInt(s));
        }
        Collections.sort(keyList);
//        将点过单的桌号以list形式加入结果中
        for (Integer s : keyList) {
            List<String> tempList = new ArrayList<>();
            tempList.add(String.valueOf(s));
            res.add(tempList);
        }
//        遍历点过单的桌号，按照食物清单顺序添加结果表格
        for (int i = 1; i < res.size(); i++) {
            for (int j = 1; j < list.size(); j++) {
                String orderFood = list.get(j);
                int num;
                if(tableMap.get(res.get(i).get(0)).get(orderFood) == null){
                    num = 0;
                }else{
                    num = tableMap.get(res.get(i).get(0)).get(orderFood);
                }
                res.get(i).add(String.valueOf(num));
            }
        }
        return res;
    }
}
