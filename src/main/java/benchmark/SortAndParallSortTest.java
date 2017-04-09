package benchmark;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortAndParallSortTest {
    //
   static final int UPPER_LIMIT = 0xffffff;
    static final int ROUNDS = 10;
    static final int INCREMENT = 5;
    static final int INIT_SIZE = 1000;

    public static void main(String[] args) {
        // 测试数组大小从INIT_SIZE开始,每次增加INCREMENT倍,直到超过UPPER_LIMIT.
        for (int capacity = INIT_SIZE; capacity <= UPPER_LIMIT; capacity *= INCREMENT) {
            ArrayList<Integer> list = new ArrayList<Integer>(capacity);

            for (int i = 0; i < capacity; i++) {
                list.add((int) (Math.random() * capacity));
            }
            // avgTimeOfParallelSort:parallelSort经过ROUNDS次排序所耗费的平均时间
            double avgTimeOfParallelSort = 0;
            // avgTimeOfSort:sort经过ROUNDS次排序所耗费的平均时间
            double avgTimeOfSort = 0;

            for (int i = 1; i <= ROUNDS; i++) {
                // 每次排序都先打乱顺序
                Collections.shuffle(list);

                Integer[] arr1 = list.toArray(new Integer[capacity]);
                Integer[] arr2 = arr1.clone();

                avgTimeOfParallelSort += counter(arr1, true);

                avgTimeOfSort += counter(arr2, false);

            }

            output(capacity, avgTimeOfParallelSort / ROUNDS, avgTimeOfSort
                    / ROUNDS);
        }

    }

    /**
     * 用于计算排序花费的时间
     *
     * @param arr
     *            要排序的数组
     * @param useParallelSort
     *            true:使用parallelSort;false:使用sort
     * @return 返回花费的时间
     */
    private static double counter(Integer[] arr, boolean useParallelSort) {
        long begin, end;
        begin = System.nanoTime();
        if (useParallelSort) {
            Arrays.parallelSort(arr);
        } else {
            Arrays.sort(arr);
        }
        end = System.nanoTime();
        return BigDecimal.valueOf(end - begin, 9).doubleValue();

    }

    /**
     *
     * @param capacity
     *            当前数组容量
     * @param avgTimeOfParallelSort
     *            parallelSort花费的平均时间
     * @param avgTimeOfSort
     *            sort花费的平均时间
     */
    private static void output(int capacity, double avgTimeOfParallelSort,
                        double avgTimeOfSort) {
        System.out
                .println("==================================================");
        System.out.println("Capacity:" + capacity);
        System.out.println("ParallelSort:" + avgTimeOfParallelSort);
        System.out.println("Sort:" + avgTimeOfSort);
        System.out.println("Winner is:"
                + (avgTimeOfParallelSort < avgTimeOfSort ? "ParallelSort"
                : "Sort"));
        System.out
                .println("==================================================");
    }
}
