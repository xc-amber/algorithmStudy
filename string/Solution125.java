/*
* 方法1：翻转字符串
* 遍历字符串s，将数字和字母放入新的字符串中
* 然后翻转新的字符串，判断翻转前后的字符串忽略大小写是否相等，翻转借助StringBuilder.reverse()
* 时间复杂度分析，遍历一次字符串;O(n),n为字符串长度
* 空间复杂度分析，O(2n)，即为O(n);
* 代码如下：
*     public boolean isPalindrome(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if(Character.isLetterOrDigit(s.charAt(i))){
                sb.append(s.charAt(i));
            }
        }
        String str1 = sb.toString();
        String str2 = sb.reverse().toString();
        return str1.equalsIgnoreCase(str2);
    }
}
*

* 方法2：双指针
* 回文串即为从左往右读和从右往左读是一样的字符串
* 将字符串s转化为char数组arr
* left指针指向左边起始下标0；
* right指针指向右边起始下标len - 1；
* 举例：A man, a plan, a canal: Panama
* 判断'A'和'a'是否是忽略大小写的相等或者是相等的数字，相等的话left++；right--；不等即可直接return false；
* 忽略大小写的相等，可以都转换为大写或者小写比较；
* 字母转换为大写：Character.toUpperCase(Character c)；字母转换为小写：Character.toLowerCase(Character c)
* 字符串转为大写：String.toUpperCase();字符串转为小写：String.toLowerCase();判断字符串相等忽略大小写：str1.equalsIgnoreCase(str2)
* 然后left指针指向了'A'右边的空格，不是有效字符，直接left++;继续下一次判断，right指针指向无效字符时处理同理；
* 当left == right时即可停止循环
* 时间复杂度分析：最差遍历一次字符串s，最好的情况遍历1次，平均遍历len/2次，所以整体时间复杂度O(n),n为字符串s的长度
* 空间复杂度分析：O(1):
* 代码如下：
*public class Solution125 {
    public boolean isPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        while (left < right){
            if(!isValid(s.charAt(left))){
                left++;
                continue;
            }
            if(!isValid(s.charAt(right))){
                right--;
                continue;
            }
            if(s.charAt(left) != s.charAt(right) && Character.toUpperCase(s.charAt(left)) != Character.toUpperCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public boolean isValid(Character c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
}
*
* 看了题解后原来有现成API可以判断是否是数字或者是否是字母Character.isLetterOrDigit(Character c)
* 判断是否是数字Character.isDigit(Character c)，判断是否是字母Character.isLetter(Character c)
* 所以优化代码
*public class Solution125 {
    public boolean isPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        while (left < right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(right))){
                right--;
                continue;
            }
            if(s.charAt(left) != s.charAt(right) && Character.toUpperCase(s.charAt(left)) != Character.toUpperCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
*

*   */
public class Solution125 {
    public boolean isPalindrome(String s) {
        int len = s.length();
        int left = 0;
        int right = len - 1;
        while (left < right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(right))){
                right--;
                continue;
            }
            if(s.charAt(left) != s.charAt(right) && Character.toUpperCase(s.charAt(left)) != Character.toUpperCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
