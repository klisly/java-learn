package algorithm;

//去除重复并排序

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @author Gavenyeah
 * @date Time: 2016年415日下午9:20:21
 * @des:m

在海量数据中查找出重复出现的元素或者去除重复出现的元素是面试中常考的文图。
针对此类问题，可以使用位图法来解决。例如：已知某个文件内包含若干个电话号码，
要求统计不同的号码的个数，甚至在O（n）时间复杂度内对这些号码进行排序。

位图法需要的空间很少（依赖于数据分布，但是我们也可以通过一些放啊发对数据进行处理，
使得数据变得密集），在数据比较密集的时候效率非常高。例如：8位整数可以表示的最大十进制数值为99999999，
如果每个数组对应于一个bit位，那么把所有的八进制整数存储起来只需要：99Mbit = 12.375MB.

实际上，Java jdk1.0已经提供了bitmap的实现BitSet类，不过其中的某些方法是jdk1.4之后才有的。

下面我先自己实现一下bitmap 的原理，然后再直接调用jdk的BitSet类分别实现bitmap, 方便比较理解：

 */
public class Bitmap {
    int ARRNUM = 800;
    int LEN_INT = 32;
    int mmax = 9999;
    int mmin = 1000;
    int N = mmax - mmin + 1;

    public static void main(String args[]) {
        new Bitmap().findDuplicate();
        new Bitmap().findDup_jdk();
    }

    public void findDup_jdk() {
        System.out.println("*******调用JDK中的库方法--开始********");
        BitSet bitArray = new BitSet(N);
        int[] array = getArray(ARRNUM);
        for (int i = 0; i < ARRNUM; i++) {
            bitArray.set(array[i] - mmin);
        }
        int count = 0;
        for (int j = 0; j < bitArray.length(); j++) {
            if (bitArray.get(j)) {
                System.out.print(j + mmin + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("排序后的数组大小为：" + count );
        System.out.println("*******调用JDK中的库方法--结束********");
    }

    public void findDuplicate() {
        int[] array = getArray(ARRNUM);
        int[] bitArray = setBit(array);
        printBitArray(bitArray);
    }

    public void printBitArray(int[] bitArray) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (getBit(bitArray, i) != 0) {
                count++;
                System.out.print(i + mmin + "\t");
            }
        }
        System.out.println();
        System.out.println("去重排序后的数组大小为：" + count);
    }

    public int getBit(int[] bitArray, int k) {// 1右移 k % 32位 与上 数组下标为 k/32 位置的值
        return bitArray[k / LEN_INT] & (1 << (k % LEN_INT));
    }

    public int[] setBit(int[] array) {// 首先取得数组位置下标 i/32, 然后 或上
        // 在该位置int类型数值的bit位：i % 32
        int m = array.length;
        int bit_arr_len = N / LEN_INT + 1;
        int[] bitArray = new int[bit_arr_len];
        for (int i = 0; i < m; i++) {
            int num = array[i] - mmin;
            bitArray[num / LEN_INT] |= (1 << (num % LEN_INT));
        }
        return bitArray;
    }

    public int[] getArray(int ARRNUM) {

        @SuppressWarnings("unused")
        int array1[] = { 1000, 1002, 1032, 1033, 6543, 9999, 1033, 1000 };

        int array[] = new int[ARRNUM];
        System.out.println("数组大小：" + ARRNUM);
        Random r = new Random();
        for (int i = 0; i < ARRNUM; i++) {
            array[i] = r.nextInt(N) + mmin;
        }

        System.out.println(Arrays.toString(array));
        return array;
    }
}