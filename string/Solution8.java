/*
* 方法1：
* 计算字符串对应的值，首先需要先排除前面的" "和"+","-"，那么进行如下的操作
* 首先从原字符串s从头开始遍历，如果是空格字符" "，那么index++，继续遍历；
* 如果是"+","-",则记录相应的正负性，对应flag的true or false；然后把后面的字符串交由myAtoiDigital方法处理
* 如果是数字，也直接交由myAtoiDigital方法处理
*
* 定义方法myAtoiDigital(String s, boolean flag)；s为字符串，flag标识正负性，这个方法的作用是计算该字符串对应的值
* 在myAtoiDigital方法中遍历新的字符串s，如果是数字就进行计算；将字符转为对应的int值可以用如下方法：
*               String str = String.valueOf(s.charAt(index));
                int temp = Integer.parseInt(str);
* 如果遍历到不是数字直接break；
* 计算res的时候要判断是否整数溢出，res使用long类型，就可以判断
* 时间复杂度分析： 遍历字符串s，O(n),n为字符串长度
* 空间复杂度分析：O(1)
* */
public class Solution8 {
    public int myAtoi(String s) {
        int len = s.length();
        if(len < 1){
            return 0;
        }
        int res = 0;
        int index = 0;
        while (index < len){
            if(s.charAt(index) == ' '){
                index++;
                continue;
            }
            if(s.charAt(index) == '-'){
                res = myAtoiDigital(s.substring(index + 1), false);
                break;
            }
            if(s.charAt(index) == '+'){
              res = myAtoiDigital(s.substring(index + 1), true);
                break;
            }
            if (Character.isDigit(s.charAt(index))){
                res = myAtoiDigital(s.substring(index), true);
            }
            break;
        }
        return res;
    }
    public int myAtoiDigital(String s, boolean flag){
        long res = 0;
        int len = s.length();
        int index = 0;
        while (index < len){
            if(Character.isDigit(s.charAt(index))){
                String str = String.valueOf(s.charAt(index));
                int temp = Integer.parseInt(str);
                res = res * 10 + temp;
                if(flag){
                    if(res > Integer.MAX_VALUE){
                        return Integer.MAX_VALUE;
                    }
                }else{
                    if(-res < Integer.MIN_VALUE){
                        return Integer.MIN_VALUE;
                    }
                }

            }else{
                break;
            }
            index++;
        }
        return flag ? ((int) res) : ((int) - res);
    }

}
