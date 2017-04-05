package algorithm;

import java.util.*;

/**
 * Created by wizardholy on 2016/12/27.
 */
public class BucketSort {
    int bucketSize = 10;
    int maxValue = 10000;

    public static void main(String[] args) {
        BucketSort bs = new BucketSort();
        int[] array = bs.getArray();
        bs.bucketSort(array);
    }

    public int[] getArray() {
        int[] arr = new int[maxValue / 3];
        Random r = new Random();
        for (int i = 0; i < maxValue / 3; i++) {
            arr[i] = r.nextInt(maxValue);
        }
        return arr;
    }

    public void bucketSort(int[] a) {
        @SuppressWarnings("unchecked")
        List<Integer> bucket[] = new ArrayList[bucketSize];
        for (int i = 0; i < a.length; i++) {
            int temp = a[i] * bucketSize / maxValue;
            if (bucket[temp] == null) {
                bucket[temp] = new ArrayList<Integer>();
            }
            bucket[temp].add(a[i]);
        }
        // 对各个桶内的list中的元素进行排序
        for (int j = 0; j < bucketSize; j++) {
            Collections.sort(bucket[j]);
        }
        int k = 0;
        for(int i = 0; i < bucket.length; i++){
            for(Integer data : bucket[i]){
                a[k++] = data;
            }
        }
    }

}
