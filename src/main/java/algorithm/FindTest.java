package algorithm;

import java.util.Random;

/**
 * 随机生成千万级和复制千万级所耗费的时间
 * gen cost 26
 * copy gen cost 3
 */
public class FindTest {
    static int[] rawData = null;

    static int max = 10;
    static int rawSize = 20;
    private static Random random = new Random();
    private static long begin;
    private static int[] nums;
    private static int[] counts;

    public static void main(String[] args) throws Exception {
        int invalidCount = 0;
        int totalCount = 1;
        for (int i = 0; i < totalCount; i++) {
//            max = 1 + random.nextInt(21);
//            rawSize = 1 + random.nextInt(10);
            begin = System.currentTimeMillis();
            generateArray();
//            log("gen cost " + (System.currentTimeMillis() - begin));
            begin = System.currentTimeMillis();
            int[] tmpData = new int[rawData.length];
            System.arraycopy(rawData, 0, tmpData, 0, rawData.length);
//            log(" copy gen cost " + (System.currentTimeMillis() - begin));
//            log("src 0:" + rawData[0] + " : " + tmpData[0] + " end:" + rawData[rawData.length - 1] + " : " + tmpData[tmpData.length - 1]);

//        begin = System.currentTimeMillis();
//        SortUtil.bubbleSort(rawData);
//        log(" bubbleSort cost " + (System.currentTimeMillis() - begin));

            begin = System.currentTimeMillis();
            SortUtil.out(tmpData);
//            SortUtil.bucketSort(tmpData, max, 20);
//            SortUtil.radixSort(tmpData, 10, 3);
            tmpData = SortUtil.bitSort(tmpData);
            SortUtil.out(tmpData);
            int index = FindUtil.seqSearch(tmpData, tmpData[tmpData.length/2]);
            FindTest.log(index+"");

//            SortUtil.quickSort(tmpData, 0, tmpData.length - 1);
//        Arrays.sort(tmpData);
//            SortUtil.mergeSort(tmpData, 0, tmpData.length - 1, new int[tmpData.length]);
            boolean resAssert = assertOrder(tmpData);

            if (!resAssert) {
                invalidCount++;
                SortUtil.out(tmpData);
                log("invalid order");
            }
            if(i % 10 == 0){
                log("round "+ i);
            }
        }

        log(" total valid cout:"+(totalCount - invalidCount)+" invalid count:"+invalidCount);
//        int num = 1000;       //汽水数量
//        int drinkNum = 0;     //喝掉的汽水数量
//        int emptyNum = 0;    //空瓶子的数量
//        while(num > 0){      //有汽水可以喝
//            drinkNum += num; //喝掉所有的汽水
//            emptyNum += num; //空瓶子数量等于上次剩余的加上这次喝掉的数量
//            num = emptyNum / 3; //兑换的汽水数量
//            emptyNum -= num * 3; //本次兑换剩余的空瓶子数量
//        }
//        System.out.println("总共喝掉瓶数：" + drinkNum);
//        System.out.println("剩余空瓶子数：" + emptyNum);
    }

    private static boolean assertOrder(int[] nums) {
        if (nums.length > 2) {
            boolean hasOrder = true;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] <= nums[i + 1]) {
                    continue;
                } else {
                    hasOrder = false;
                    break;
                }
            }
            if(hasOrder){
                return hasOrder;
            }

            hasOrder = true;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] >= nums[i + 1]) {
                    continue;
                } else {
                    hasOrder = false;
                    break;
                }
            }
            return hasOrder;
        } else {
            return true;
        }
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