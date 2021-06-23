public class Solution8 {
    public int myAtoi(String s) {
        int len = s.length();
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
                res = myAtoiDigital(s.substring(index + 1));
                break;
            }
            if(s.charAt(index) == '+'){
              res = myAtoiDigital(s.substring(index + 1));
                break;
            }
            if (Character.isDigit(s.charAt(index))){
                res = myAtoiDigital(s.substring(index));
                break;
            }
        }
        if(flag){
            return res;
        }else{
            if(res == Integer.MAX_VALUE){
                return Integer.MIN_VALUE;
            }else{
                return -res;
            }
        }
    }
    public int myAtoiDigital(String s){
        System.out.println(s);
        int res = 0;
        int len = s.length();
        int index = 0;
        while (index < len){
            if(Character.isDigit(s.charAt(index))){
                if((res * 10) / 10 != res ){
                    return  Integer.MAX_VALUE;
                }
                String str = String.valueOf(s.charAt(index));
                int temp = Integer.parseInt(str);
                res = res * 10 + temp;
            }else{
                break;
            }
            index++;
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        solution8.myAtoi(".1");
    }
}
