public class Solution8 {
    public int myAtoi(String s) {
        int len = s.length();
        if(len < 1){
            return 0;
        }
        int res = 0;
        int index = 0;
        boolean flag = true;

        if(!Character.isDigit(s.charAt(0))){
            if(Character.isLetter(s.charAt(0))){
                return 0;
            }
            if(s.charAt(0) != ' ' && s.charAt(0) != '+' && s.charAt(0) != '-'){
                return 0;
            }
        }
        while (index < len){
            if(s.charAt(index) == ' '){
                index++;
                continue;
            }
            if(s.charAt(index) == '-'){
                flag = false;
                res = myAtoiDigital(s.substring(index + 1), flag);
                break;
            }
            if(s.charAt(index) == '+'){
              res = myAtoiDigital(s.substring(index + 1), flag);
                break;
            }
            if (Character.isDigit(s.charAt(index))){
                res = myAtoiDigital(s.substring(index), flag);
                break;
            }
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
            }else{
                break;
            }
            index++;
        }
        if(flag){
            if(res > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else {
                return ((int) res);
            }
        }else{
            if(-res < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }else {
                return ((int) - res);
            }
        }
    }

    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        solution8.myAtoi("2147483648");
    }
}
