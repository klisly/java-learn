package lang;

import utils.SortUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 随机生成千万级和复制千万级所耗费的时间
 * gen cost 26
 * copy gen cost 3
 */
public class IntArrayIncrease {
    static int[] rawData = null;

    static int max = 20;
    static int rawSize = 20;
    private static Random random = new Random();
    private static long begin;
    private static int[] nums;
    private static int[] counts;
    public static void main(String[] args) {
        begin = System.currentTimeMillis();
        generateArray();
        log("gen cost " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        int[] tmpData = new int[rawData.length];
        System.arraycopy(rawData, 0, tmpData, 0, rawData.length);
        log(" copy gen cost " + (System.currentTimeMillis() - begin));
        log("src 0:" + rawData[0] + " : " + tmpData[0] + " end:" + rawData[rawData.length - 1] + " : " + tmpData[tmpData.length - 1]);

//        begin = System.currentTimeMillis();
//        SortUtil.bubbleSort(rawData);
//        log(" bubbleSort cost " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        SortUtil.out(tmpData);
//        SortUtil.insertSort(tmpData);
        Arrays.sort(tmpData);
        SortUtil.mergeSort(tmpData, 0, tmpData.length - 1, new int[tmpData.length]);

        log(" cost " + (System.currentTimeMillis() - begin));
        SortUtil.out(tmpData);

    }

    private static void generateArray() {
        rawData = new int[rawSize];
        for (int i = 0; i < rawSize; i++) {
            rawData[i] = random.nextInt(max);
        }
    }

    public static void log(String mesage) {
        System.out.println(mesage);
    }
}