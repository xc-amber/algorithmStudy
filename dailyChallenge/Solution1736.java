
/*
  以下位数忽略':'
* 当第一位为？时，最大值取决于第二位，第二位为4~9时，第一位最大为1；第二位为0~3或者？时最大为2
* 当第二位为？时，最大值去决议第一位，如果第一位为0~1，第二位最大值为9，否则为3
* 当第三位为？时，最大值直接是5
* 当第四位为？时，最大值直接是9
* 时间空间复杂度均为O(1)
*/

public class Solution1736 {
    public String maximumTime(String time) {
        char[] ch = time.toCharArray();
        if(ch[0] == '?'){
            if(ch[1] == '?' || ch[1] < '4'){
                ch[0] = '2';
            }else {
                ch[0] = '1';
            }
        }
        if(ch[1] == '?'){
            if(ch[0] != '2'){
                ch[1] = '9';
            }else {
                ch[1] = '3';
            }
        }
        if(ch[3] == '?'){
            ch[3] = '5';
        }
        if(ch[4] == '?'){
            ch[4] = '9';
        }
        return new String(ch);
    }
}
