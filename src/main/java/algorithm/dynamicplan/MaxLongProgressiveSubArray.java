package algorithm.dynamicplan;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaxLongProgressiveSubArray {
    /**
     * 给定数组arr，返回arr的最长递增子序列的长度，比如arr=[2,1,5,3,6,4,8,9,7]，最长递增子序列为[1,3,4,8,9]返回其长度为5.
     */
    static Random random = new Random();
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        compute(10);
    }

    static List<List<Integer>> lists = new ArrayList<>();

    private static void compute(int length) {
        genList(length);
        for (int i = 0; i < length; i++) {
            lists.add(null);
        }
        List<Integer> datas = maxLongProgressiveSubArray(length - 1);
        for(Integer index :datas){
            System.out.print(list.get(index)+"\t");
        }
    }


    private static List<Integer> maxLongProgressiveSubArray(int index) {
        if (lists.get(index) != null) {
            return lists.get(index);
        }
        if (index > 0) {
            List<Integer> preIndexes = maxLongProgressiveSubArray(index - 1);
            List<Integer> cur = new ArrayList<>();
            cur.addAll(preIndexes);
            if (list.get(preIndexes.get(preIndexes.size() - 1)) <= list.get(index)) {
                cur.add(index);
            }
            lists.set(index, cur);
        }
        if (index == 0) {
            List<Integer> cur = new ArrayList<>();
            cur.add(index);
            lists.set(index, cur);
        }
        return lists.get(index);
    }

    private static void genList(int length) {
        list.clear();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            list.add(num);
            System.out.print(num + "\t");
        }
        System.out.println();
    }
}
