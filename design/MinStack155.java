import java.util.*;
/*
* 方法1：原始栈+最小栈
* 时间复杂度:O(1)
* 空间复杂度:O(n)
* */
public class MinStack155 {
    Deque<Integer> stack; //原始栈，记录数据操作
    Deque<Integer> minStack;  //最小栈，记录当前栈最小值情况
    public MinStack155() {
        this.stack = new LinkedList<>();
        this.minStack = new LinkedList<>();
    }

//    压入数据时，原始栈正常压入；最小栈：当最小栈为空，或者压入的数据小于等于最小栈的栈顶元素时，压入最小栈
    public void push(int val) {
        stack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }
//    弹出数据时,原始栈正常弹出;最小栈:当弹出的数据等于最小栈栈顶元素时,最小栈的栈顶元素也弹出
    public void pop() {
        int temp = stack.pop();
        if(temp == minStack.peek()){
            minStack.pop();
        }
    }

//    原始栈的栈顶
    public int top() {
        return stack.peek();
    }
//    最小栈的栈顶
    public int getMin() {
        return minStack.peek();
    }
}
