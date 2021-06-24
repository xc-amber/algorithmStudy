/*
*
*方法1：指针遍历
* 左指针从haystack下标0开始，右指针从左指针+needle长度位置开始
* 判断haystack.substring(left, left + len).equals(needle)，如果相等返回left，break循环；如果不等left++；
* 时间复杂度分析： 遍历O(m),m为haystack长度,每次遍历substring的时间复杂度O(n),n为needle长度，所以整体为O(m*n)
* 空间复杂度分析：O(1);
*
*  */
public class Solution28 {
    public int strStr(String haystack, String needle) {
        int len = needle.length();
        if(len == 0){
            return 0;
        }
        int left = 0;
        int index = -1;
        while (left + len <= haystack.length()){
            if(haystack.substring(left, left + len).equals(needle)){
                index = left;
                break;
            }
            left++;
        }
        return index;
    }
}
