package algorithm.array;

import algorithm.SortTest;
import algorithm.SortUtil;

/**
 题目描述：
 定义字符串的左旋转操作：把字符串前面的若干个字符移动到字符串的尾部，
 如把字符串abcdef左旋转2位得到字符串cdefab。
 请实现字符串左旋转的函数，要求对长度为n的字符串操作的时间复杂度为O(n)，空间复杂度为O(1)。

 */
public class ArrayReverseAndRotate {
    public static void main(String[] args) {
        int[] arrays = SortTest.generateArray(10, 10);
        SortUtil.out(arrays);
        ArrayReverseAndRotate.reverse(arrays, 0, arrays.length - 1);
        System.out.println("After reverse");
        SortUtil.out(arrays);
        ArrayReverseAndRotate.rotate(arrays, 2);
        System.out.println("After rotate");
        SortUtil.out(arrays);

    }

    public static void reverse(int[] arrays, int start, int end) {
        int left = start, right = end;
        while (left < right) {
            int tmp = arrays[left];
            arrays[left] = arrays[right];
            arrays[right] = tmp;
            left++;
            right--;
        }
    }

    public static void rotate(int[] arrays, int pos) {
        ArrayReverseAndRotate.reverse(arrays, 0, pos);
        ArrayReverseAndRotate.reverse(arrays, pos + 1, arrays.length - 1);
        ArrayReverseAndRotate.reverse(arrays, 0, arrays.length - 1);
    }

}
