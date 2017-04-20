package algorithm.array;

import algorithm.SortUtil;

import java.util.ArrayList;
import java.util.List;

/**
 线性时间内对范围在0-n^2内的数排序
 如果数据的范围都在0-n ，就可以直接用计数排序了，空间复杂度为O(n)。
 在考虑下基数排序，一般来说复杂度为 O(nk)，其中n是排序元素个数，
 k是数字位数，k的大小是取决于我们选取的底(基数)，一般对十进制数的话
 就选取的10.  这里为了把k变为常数，就可以取n为底，k就为2. 这样复杂度就为
 O(n)了。即把这些数都看成是n进制的，位数则不会超过2
 */
public class N2LinearOrder {
    static void count2Sort(int arr[], int n){
        int size = arr.length;
        List<Integer> list[] = new ArrayList[n];
        int count;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < size; j++){
                int r = (int) (arr[j] % Math.pow(n, i + 1) / Math.pow(n, i));
                if(list[r] == null){
                    list[r] = new ArrayList<Integer>();
                }
                list[r].add(arr[j]);
            }
            count = 0;
            for(List<Integer> l : list){
                if(l != null){
                    for(Integer integer : l){
                        arr[count++] = integer;
                    }
                    l.clear();
                }

            }
        }
    }

    public static void main(String[] args){
        int arr[] = {40, 12, 45, 32, 33, 1, 22};
        SortUtil.out(arr);
        count2Sort(arr, 9);
        SortUtil.out(arr);
    }
}
