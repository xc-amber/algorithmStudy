import java.util.*;
/*
* (参考题解的)
* 方法一：栈 + 哈希表
对于括号序列相关的题目，通用的解法是使用递归或栈。本题中我们将使用栈解决。

从左到右遍历该化学式，并使用哈希表记录当前层遍历到的原子及其数量，因此初始时需将一个空的哈希表压入栈中。对于当前遍历的字符：

如果是左括号，将一个空的哈希表压入栈中，进入下一层。

如果不是括号，则读取一个原子名称，若后面还有数字，则读取一个数字，否则将该原子后面的数字视作 1。然后将原子及数字加入栈顶的哈希表中。

如果是右括号，则说明遍历完了当前层，若括号右侧还有数字，则读取该数字 num，否则将该数字视作 1。然后将栈顶的哈希表弹出，将弹出的哈希表中的原子数量与 num 相乘，加到上一层的原子数量中。

遍历结束后，栈顶的哈希表即为化学式中的原子及其个数。遍历哈希表，取出所有「原子-个数」对加入数组中，对数组按照原子字典序排序，然后遍历数组，按题目要求拼接成答案。

* 时间复杂度：O(n^2)。其中 n 是化学式 formula 的长度。最坏情况下栈有 O(n)层，每次出栈时需要更新O(n) 个原子的数量，因此遍历化学式的时间复杂度为 O(n^2)
遍历结束后排序的时间复杂度为 O(nlogn)。因此总的时间复杂度为 O(n^2+nlog n)=O(n^2)。
 空间复杂度：O(n)。空间复杂度取决于栈中所有哈希表中的元素个数之和，而这不会超过化学式formula 的长度，因此空间复杂度为 O(n)。
* */

public class Solution726 {
    int i = 0;  //遍历字符串的指针
    int n; //字符串长度
    String formula;
    public String countOfAtoms(String formula) {
        this.n = formula.length();
        this.formula = formula;
//        创建栈，当遇到'('时压入空的map，当遇')'时取出顶部map内容加入下一个map中；核心数据结构
        Deque<Map<String, Integer>> deque = new LinkedList<>();
        deque.push(new HashMap<>());
        while (i < n){
//            从头开始遍历，当不是括号时，就取出原子元素和它的个数
            if(formula.charAt(i) != '(' && formula.charAt(i) != ')'){
                String atom = getAtom(); // 取到原子的值（取原子的方法中已经i++）
                int num = getNum();  //取到原子的个数（取个数的方法中已经i++）
                Map<String, Integer> topMap = deque.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);  //取出的原子和其个数加入栈顶的map
            }else if(formula.charAt(i) == '('){  //如果遍历到了左边括号则新建一个map压入栈，且i++右移
                Map<String, Integer> newMap = new HashMap<>();
                deque.push(newMap);
                i++;
            }else {  //如果遍历到右边括号说明括号内的原子已经遍历完了，取出栈顶的map，并将其的内容加入下一个栈顶的map中
                Map<String, Integer> popMap = deque.pop(); //取出栈顶的map
                i++; //右移i，为了取右括号后的个数
                int num = getNum(); //获取数字时i已经++；所以这边处理完无需再++
                Map<String, Integer> topMap = deque.peek();  //获取新的栈顶map
//                遍历被取出的map的内容，并加入新的栈顶map中
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int value = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + value * num);
                }
            }
        }
        Map<String, Integer> resultMap = deque.peek();  //取出最后的map
        TreeMap<String, Integer> treeMap = new TreeMap<>(resultMap);  //key按字典序排序
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            sb.append(atom);
            int num = entry.getValue();
            if(num > 1){
                sb.append(num);
            }
        }
        return sb.toString();
    }
//    获取原子元素的方法，例如H,O,Mg
    public String getAtom(){
        StringBuilder sb = new StringBuilder();
        sb.append(formula.charAt(i++)); //添加当前指针指向的字符，并将指针后移
//        当指针小于长度且下一个字符为小写字母时，追加小写字母，否则退出循环
        while (i < n && Character.isLowerCase(formula.charAt(i))){
            sb.append(formula.charAt(i++));  //追加小写字母，且指针后移
        }
        return sb.toString();
    }
    public int getNum(){
        int num = 0;
//        如果指针已经超过了右届或者指向的不是数字返回1
        if(i == n || !Character.isDigit(formula.charAt(i))){
            return 1;
        }
//        如果指针小于长度且为数字就累加
        while (i < n && Character.isDigit(formula.charAt(i))){
            num = num * 10 + formula.charAt(i++) - '0';
        }
        return num;
    }

    public static void main(String[] args) {
        Solution726 solution726 = new Solution726();
        solution726.countOfAtoms("H2O");
    }
}
