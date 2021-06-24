public class Solution14 {
    public  static String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if(len == 0){
            return "";
        }
        if(len == 1){
            return strs[0];
        }
        String str1 = strs[0];
        String str2 = strs[1];
        int right = 1;
        String temp;
        while (right <= str1.length() && right <= str2.length()){
            if(str1.substring(0, right).equals(str2.substring(0, right))){
                right++;
            }else{
                break;
            }
        }
        temp = str1.substring(0, right - 1);
        for (int i = 2; i < len; i++) {
            if(right - 1 <= strs[i].length()  && right > 0){
                if(!strs[i].substring(0, right - 1).equals(temp)){
                    right--;
                    if(right < 1){
                        return  "";
                    }else{
                        temp = str1.substring(0, right - 1);
                    }
                    i = 1;
                }
            }else{
                right--;
                temp = str1.substring(0, right - 1);
                i = 1;
            }

        }
        return temp;
    }

    public static void main(String[] args) {
        longestCommonPrefix(new String[]{"ac","ac","a","a"});
//        System.out.println("a".substring(0, 0).equals(""));
    }
}
