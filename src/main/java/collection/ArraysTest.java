package collection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wizardholy on 2017/3/27.
 */
public class ArraysTest {
    public static void main(String[] args){
        // asList 可以返回一个固定大小的List, 初始化list
        List<Integer> nums = Arrays.asList(1,2,5,6,9);
        nums.sort((o1, o2)->{
            return o1 - o2;
        });
        List<String> strs = Arrays.asList("one","two","three");
        List<Double> doubles = Arrays.asList(1.01,1.023);
        // binarySearch 方法支持在整个数组中查找
        System.out.printf("index of %d is %d\n",3, Arrays.binarySearch(new int[]{1,2,3,4,5,6}, 3));
        // 某个区间范围内查找
        System.out.printf("index of %d is %d\n",3, Arrays.binarySearch(new int[]{1,2,3,4,5,6,7,8,9,1}, 4, 9, 10));

        // copyOf & copyOfRange
        String[] names2 = { "Eric", "John", "Alan", "Liz" };

        //[Eric, John, Alan]
         System.out.println(Arrays.toString(Arrays.copyOf(names2, 3)));

        //[Alan, Liz] 可以方便我们打印出数组内容。
        // Arrays.toString Returns a string representation of the contents of the specified array.
        System.out.println(Arrays.toString(Arrays.copyOfRange(names2, 2,
                names2.length)));

        //
        String[] names = { "Liz", "John", "Eric", "Alan", "AAna" };
        System.out.println("origin:"+Arrays.toString(names));
        Arrays.sort(names, 1,3);
        //只排序前两个
        //[John, Liz, Eric, Alan]
        System.out.println("sort by index:"+Arrays.toString(names));
        //全部排序
        //[Alan, Eric, John, Liz]
        Arrays.sort(names);
        System.out.println("sort all:"+Arrays.toString(names));

        // deepToString 打印二维数组的内容
        int[][] stuGrades = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };
        System.out.println("two dimen array deepToString:"+Arrays.deepToString(stuGrades));
        int[][][] stuGradess ={ { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } }};
        System.out.println("three dimen array deepToString:"+Arrays.deepToString(stuGradess));
        // equals
        System.out.println(Arrays.equals(names, names2));
        System.out.println(Arrays.equals(names, names));
        // deepEquals
        int[][] stuGrades1 = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };

        int[][] stuGrades2 = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };

        System.out.println(Arrays.deepEquals(stuGrades1, stuGrades2));

        // fill
        int[] array1 = new int[8];
        Arrays.fill(array1, 1);
        //[1, 1, 1, 1, 1, 1, 1, 1]
        System.out.println("fill:"+Arrays.toString(array1));

    }
}
