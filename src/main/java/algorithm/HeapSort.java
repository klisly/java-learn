package algorithm;

/**
 * Created by wizardholy on 2017/4/9.
 */
public class HeapSort {
    public static void heapSort(int[] arrays){
        if(arrays == null || arrays.length <= 1){
            return;
        }
        int length = arrays.length;
        while (length > 1){
            int size = length;
            int i;
            for(i = size/2; i >= 0; i-- ){
                buildMaxTree(arrays, i, size);
            }
            exchangeElements(arrays, 0, size - 1);
            length --;
        }
        out(arrays);
    }

    private static void buildMaxTree(int[] arrays, int i, int length) {
        int changeIndex = i;
        int left = left(i);
        int right = right(i);
        if(left < length && arrays[changeIndex] > arrays[left]){
            changeIndex = left;
        }
        if(right < length && arrays[changeIndex] > arrays[right]){
            changeIndex = right;
        }


        if(changeIndex == i){
            return;
        }

        exchangeElements(arrays, changeIndex, i);
        buildMaxTree(arrays, changeIndex, length);
    }

    public static int left(int i) {
        return i * 2 + 1;
    }

    public static int right(int i) {
        return i * 2 + 2;
    }

    public static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public static void out(int[] number) {
        System.out.println();
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i]);
            System.out.print("\t");
        }
        System.out.println();
    }
}
