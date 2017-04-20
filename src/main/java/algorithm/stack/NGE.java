package algorithm.stack;

import java.util.Stack;

/**
 * 给定一个数组,打印出每一个元素的下一个更大的元素( Next Greater Element,NGE )，
 * 就叫做NGE问题吧。
 */
public class NGE {
    // 2重循环遍历 O(n2)

    // 我们可以用栈来维护一个递减的序列，里面存储的数组左边未找到NGE的元素，
    // 初始时只包含第一个元素。我们可以假定栈顶就是最小的元素，
    // 因此可以用从栈顶top开始和后面的元素next比较。如果top<next则说明，
    // 找到了top的NGE，可以弹出栈。然后继续比较栈顶元素top和next，直到栈为空或
    // next <= top。然后把next加入栈，以便查找next的NGE。由于只有一次遍历，
    // 时间复杂度为O(n)
    public static void main(String[] args) {
        int arr[] = new int[]{13, 12, 11, 10};
        Stack<Integer> stack = new Stack<>();
        for (Integer i : arr) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && stack.peek() < i) {
                    System.out.println("nge:" + stack.pop() + ":" + i);
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            System.out.println("nge:" + stack.pop() + ":-1");
        }
    }
}
