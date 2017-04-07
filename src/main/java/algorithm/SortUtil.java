package algorithm;

import sun.security.util.BitArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortUtil {
    /**
     * 冒泡法排序<br/>
     * <p>O(n2)
     * <li>比较相邻的元素。如果第一个比第二个大，就交换他们两个。</li>
     * <li>对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。</li>
     * <li>针对所有的元素重复以上的步骤，除了最后一个。</li>
     * <li>持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。</li>
     *
     * @param numbers 需要排序的整型数组
     */
    public static void bubbleSort(int[] numbers) {
//        int size = numbers.length; // 数组大小
//        for (int i = size - 1; i > 0; --i) {
//            for (int j = 0; j < i; j++) {
//                if (numbers[j] > numbers[j + 1]) { // 交换两数的位置
//                    temp = numbers[j + 1];
//                    numbers[j + 1] = numbers[j];
//                    numbers[j] = temp;
//                }
//            }
//        }
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < numbers.length - i - 1; j++){
                if (numbers[j] > numbers[j + 1]) { // 交换两数的位置
                    numbers[j + 1] = numbers[j] + numbers[j + 1];
                    numbers[j] = numbers[j + 1] - numbers[j] ;
                    numbers[j + 1] = numbers[j + 1] - numbers[j] ;
                }
            }
        }
    }

    /**
     * 快速排序<br/>
     * <ul>O(nlgn)
     * <li>从数列中挑出一个元素，称为“基准”</li>
     * <li>重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分割之后，
     * 该基准是它的最后位置。这个称为分割（partition）操作。</li>
     * <li>递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。</li>
     * </ul>
     *
     * @param numbers
     * @param start
     * @param end
     */
    public static void quickSort(int[] numbers, int start, int end) {
        if (start < end) {
            int index = start;
            int tmp = numbers[index];
            int i = start + 1;
            int j = end;
            while (i < j){
                while (numbers[i] < tmp && i < j){
                    i++;
                }
                while (numbers[j] >= tmp && i < j){
                    j--;
                }
                if(i != j){
                    numbers[j] = numbers[j] + numbers[i];
                    numbers[i] = numbers[j] - numbers[i];
                    numbers[j] = numbers[j] - numbers[i];
                    i++;
                    j--;
                }
            }
            if(numbers[i] <= tmp){
                numbers[index] = numbers[index] + numbers[i];
                numbers[i] = numbers[index] - numbers[i];
                numbers[index] = numbers[index] - numbers[i];
            }
            quickSort(numbers, start, i-1);
            quickSort(numbers, i + 1, end);

//            int base = numbers[start];
//            int l = start;
//            int h = end;
//            do {
//                while (h >= l && numbers[h] > base) {
//                    h--;
//                }
//                while (l <= h && numbers[l] < base) {
//                    l++;
//                }
//                if (l <= h) {
//                    int tmp = numbers[l];
//                    numbers[l] = numbers[h];
//                    numbers[h] = tmp;
//                    l++;
//                    h--;
//                }
//            } while (l <= h);
//            if (l < end) {
//                quickSort(numbers, l, end);
//            }
//            if (h > start) {
//                quickSort(numbers, start, l - 1);
//            }
        }
    }

    /**
     * 选择排序<br/>
     * O(n2)
     * <li>在未排序序列中找到最小元素，存放到排序序列的起始位置</li>
     * <li>再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。</li>
     * <li>以此类推，直到所有元素均排序完毕。</li>
     *
     * @param numbers
     */
    public static void selectSort(int[] numbers) {
//        int size = numbers.length, temp;
//        for (int i = 0; i < size; i++) {
//            int k = i;
//            for (int j = size - 1; j > i; j--) {
//                if (numbers[j] < numbers[k]) k = j;
//            }
//            temp = numbers[i];
//            numbers[i] = numbers[k];
//            numbers[k] = temp;
//        }
        int temp;
        int size = numbers.length;
        for(int i = 0; i < size - 1; i++){
            temp = i;
            for(int j = i + 1; j < size; j++){
                if(numbers[j] > numbers[temp]){
                    temp = j;
                }
            }
            numbers[temp] = numbers[i] + numbers[temp];
            numbers[i] = numbers[temp] - numbers[i] ;
            numbers[temp] = numbers[temp] - numbers[i] ;
        }
    }

    /**
     * 插入排序<br/>
     * <ul>
     * <li>从第一个元素开始，该元素可以认为已经被排序</li>
     * <li>取出下一个元素，在已经排序的元素序列中从后向前扫描</li>
     * <li>如果该元素（已排序）大于新元素，将该元素移到下一位置</li>
     * <li>重复步骤3，直到找到已排序的元素小于或者等于新元素的位置</li>
     * <li>将新元素插入到该位置中</li>
     * <li>重复步骤2</li>
     * </ul>
     *
     * @param numbers
     */
    public static void insertSort(int[] numbers) {
        int size = numbers.length;
        int tmp;
        int j;
//        for (int i = 1; i <= size - 1; i++) {
//            tmp = numbers[i];
//            for (j = i; j > 0 && numbers[j - 1] < tmp; j--) {
//                numbers[j] = numbers[j - 1];
//            }
//            numbers[j] = tmp;
//        }

        for(int i = 1; i < size; i++){
            if(numbers[i] < numbers[i-1]){
                tmp = numbers[i];
                for(j = i - 1; j >= 0; j--){
                    if(numbers[j] > tmp){
                        numbers[j+1] = numbers[j];
                    }
                }
                numbers[j+1] = tmp;
            }
        }
    }

    /**
     * 归并排序<br/>
     * <ul>
     * <li>申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列</li>
     * <li>设定两个指针，最初位置分别为两个已经排序序列的起始位置</li>
     * <li>比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置</li>
     * <li>重复步骤3直到某一指针达到序列尾</li>
     * <li>将另一序列剩下的所有元素直接复制到合并序列尾</li>
     * </ul>
     * O(nlgn)
     *
     * @param numbers
     */
    public static void mergeSort(int[] numbers, int left, int right, int[] tmp) {
//        if (left < right) {
//            int mid = (left + right) / 2;
//            mergeSort(numbers, left, mid, tmp);
//            mergeSort(numbers, mid + 1, right, tmp);
//            merge(numbers, left, right, mid, tmp);
//        }

        if(right > left){
            int mid = (left + right) / 2;
            mergeSort(numbers, left, mid, tmp);
            mergeSort(numbers, mid + 1, right, tmp);
            merge(numbers, left, right, mid, tmp);
        }
    }

    /**
     * 归并算法实现
     *
     * @param data
     * @param left
     * @param mid
     * @param right
     * @param tmp
     */
    private static void merge(int[] data, int left, int mid, int right, int[] tmp) {
//        int i = left, j = mid + 1;
//        int m = mid, n = right;
//        int k = 0;
//        while (i <= m && j <= n) {
//            if (data[i] < data[j]) {
//                tmp[k++] = data[i++];
//            } else {
//                tmp[k++] = data[j++];
//            }
//        }
//
//        while (i <= m) {
//            tmp[k++] = data[i++];
//        }
//
//        while (j <= m) {
//            tmp[k++] = data[j++];
//        }
//        for (i = 0; i < k; i++) {
//            data[left + i] = tmp[i];
//        }

        int index = 0, x = left, y = mid + 1;
        while (x <= mid && y <= right){
            tmp[index++] = data[x] > data[y]?data[x++]:data[y++];
        }
        while (x <= mid){
            tmp[index++] = data[x++];
        }
        while (y <= right){
            tmp[index++] = data[y++];
        }
        index = left;
        while (index <= right){
            data[index]=tmp[index];
            index++;
        }
    }

    public static void shellSort(int[] numbers) {
//        int length = numbers.length;
//        int i, j, gap;

//        for (gap = length / 2; gap > 0; gap /= 2) //步长
//            for (i = 0; i < gap; i++)        //直接插入排序
//            {
//                for (j = i + gap; j < length; j += gap)
//                    if (numbers[j] < numbers[j - gap])
//                    {
//                        int temp = numbers[j];
//                        int k = j - gap;
//                        while (k >= 0 && numbers[k] > temp)
//                        {
//                            numbers[k + gap] = numbers[k];
//                            k -= gap;
//                        }
//                        numbers[k + gap] = temp;
//                    }
//            }

//        for (gap = length / 2; gap > 0; gap /= 2) {
//            for (i = 0; i < gap; i++) {
//                for (j = i; j + gap < length; j += gap) {
//                    if (numbers[j] < numbers[j + gap]) {
//                        int tmp = numbers[j + gap];
//                        int k = j;
//                        while (k >= 0 && numbers[k] < tmp) {
//                            numbers[k + gap] = numbers[k];
//                            if (k >= gap) {
//                                k -= gap;
//                            } else {
//                                break;
//                            }
//                        }
//                        numbers[k] = tmp;
//                    }
//                }
//            }
//        }
        int size = numbers.length, i, j , k;
        int tmp;
        int gap = size / 2;
        for(;gap > 0; gap = gap/2){
            for(i = 0; i < gap; i++){
                for (j = i+gap; j < size; j+=gap){
                    if(numbers[j] < numbers[j - gap]){
                        tmp = numbers[j];
                        for(k = j - gap; k >= 0; k -= gap){
                            if(numbers[k] > tmp){
                                numbers[k + gap] = numbers[k];
                            } else {
                                break;
                            }
                        }
                        numbers[k + gap] = tmp;
                    }
                }
            }
        }
    }

    /**
     * 堆排序是一种树形选择排序方法，它的特点是：在排序的过程中，将array[0，...，n-1]看成是一颗完全二叉树的顺序存储结构，利用完全二叉树中双亲节点和孩子结点之间的内在关系，在当前无序区中选择关键字最大（最小）的元素。
     * <p>
     * 1. 若array[0，...，n-1]表示一颗完全二叉树的顺序存储模式，则双亲节点指针和孩子结点指针之间的内在关系如下：
     * <p>
     * 　　任意一节点指针 i：父节点：i==0 ? null : (i-1)/2
     * <p>
     * 　　　　　　　　　　  左孩子：2*i + 1
     * <p>
     * 　　　　　　　　　　  右孩子：2*i + 2
     * <p>
     * 2. 堆的定义：n个关键字序列array[0，...，n-1]，当且仅当满足下列要求：(0 <= i <= (n-1)/2)
     * <p>
     * 　　　　　　① array[i] <= array[2*i + 1] 且 array[i] <= array[2*i + 2]； 称为小根堆；
     * <p>
     * 　　　　　　② array[i] >= array[2*i + 1] 且 array[i] >= array[2*i + 2]； 称为大根堆；
     * <p>
     * 3. 建立大根堆：
     * <p>
     * 　　n个节点的完全二叉树array[0，...，n-1]，最后一个节点n-1是第(n-1-1)/2个节点的孩子。对第(n-1-1)/2个节点为根的子树调整，使该子树称为堆。
     * <p>
     * 　　对于大根堆，调整方法为：若【根节点的关键字】小于【左右子女中关键字较大者】，则交换。
     * <p>
     * 　　之后向前依次对各节点（(n-2)/2 - 1）~ 0为根的子树进行调整，看该节点值是否大于其左右子节点的值，若不是，将左右子节点中较大值与之交换，交换后可能会破坏下一级堆，于是继续采用上述方法构建下一级的堆，直到以该节点为根的子树构成堆为止。
     * <p>
     * 　　反复利用上述调整堆的方法建堆，直到根节点。
     * <p>
     * 4.堆排序：（大根堆）
     * <p>
     * 　　①将存放在array[0，...，n-1]中的n个元素建成初始堆；
     * <p>
     * 　　②将堆顶元素与堆底元素进行交换，则序列的最大值即已放到正确的位置；
     * <p>
     * 　　③但此时堆被破坏，将堆顶元素向下调整使其继续保持大根堆的性质，再重复第②③步，直到堆中仅剩下一个元素为止。
     * <p>
     * 堆排序算法的性能分析：
     * <p>
     * 　　空间复杂度:o(1)；
     * <p>
     * 　　时间复杂度:建堆：o(n)，每次调整o(log n)，故最好、最坏、平均情况下：o(n*logn);
     * <p>
     * 　　稳定性：不稳定
     *
     * @param arrays
     */
    public static void heapSort(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }
        buildHeap(arrays);//初始建堆，array[0]为第一趟值最大的元素
        exchangeElements(arrays, 0, arrays.length - 1);

        for (int i = 1; i < arrays.length - 1; i++) {
            maxHeap(arrays, 0, arrays.length - i);
            exchangeElements(arrays, 0, arrays.length - i - 1);
        }
    }

    public static void buildHeap(int[] arrays) {
        int half = arrays.length / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(arrays, i, arrays.length);
        }
    }

    private static void maxHeap(int[] arrays, int index, int length) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int lagest = index;
        if (left < length && arrays[left] > arrays[lagest]) {
            lagest = left;
        }
        if (right < length && arrays[right] > arrays[lagest]) {
            lagest = right;
        }
        if (index == lagest) {
            return;
        }
        exchangeElements(arrays, lagest, index);
        maxHeap(arrays, lagest, length);
    }


    //删除堆顶元素操作
    public int[] deleteMax(int[] array) {
        //将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
        array[0] = array[array.length - 1];
        array[array.length - 1] = -99999;
        //对此时的根节点进行向下调整
        adjustDownToUp(array, 0, array.length);
        return array;
    }

    //插入操作:向大根堆array中插入数据data
    public int[] insertData(int[] array, int data) {
        array[array.length - 1] = data; //将新节点放在堆的末端
        int k = array.length - 1;  //需要调整的节点
        int parent = (k - 1) / 2;    //双亲节点
        while (parent >= 0 && data > array[parent]) {
            array[k] = array[parent];  //双亲节点下调
            k = parent;
            if (parent != 0) {
                parent = (parent - 1) / 2;  //继续向上比较
            } else {  //根节点已调整完毕，跳出循环
                break;
            }
        }
        array[k] = data;  //将插入的结点放到正确的位置
        return array;
    }

    private static void adjustDownToUp(int[] arrays, int k, int length) {
        int tmp = arrays[k];
        for (int i = 2 * k + 1; i < length - 1; i = 2 * i + 1) {
            if (i < length && arrays[i] < arrays[i + 1]) {
                i++;
            }
            if (tmp >= arrays[i]) {
                break;
            } else {
                arrays[k] = arrays[i];
                k = i;
            }
        }
        arrays[k] = tmp;
    }

    /**
     * 计数排序对输入的数据有附加的限制条件：
     * 1、输入的线性表的元素属于有限偏序集S；
     * 2、设输入的线性表的长度为n，|S|=k（表示集合S中元素的总数目为k），则k=O(n)。
     * 在这两个条件下，计数排序的复杂性为O(n)。
     * 计数排序的基本思想是对于给定的输入序列中的每一个元素x，确定该序列中值小于x的元素的个数
     * （此处并非比较各元素的大小，而是通过对元素值的计数和计数值的累加来确定）。一旦有了这个信息，
     * 就可以将x直接存放到最终的输出序列的正确位置上。例如，如果输入序列中只有17个元素的值小于x的值，
     * 则x可以直接存放在输出序列的第18个位置上。
     * 计数排序算法没有用到元素间的比较，它利用元素的实际值来确定它们在输出数组中的位置。
     * 因此，计数排序算法不是一个基于比较的排序算法，从而它的计算时间下界不再是Ω(nlogn)。
     * 另一方面，计数排序算法之所以能取得线性计算时间的上界是因为对元素的取值范围作了一定限制，
     * 即k=O(n)。如果k=n2,n3,..，就得不到线性时间的上界。此外，我们还看到，
     * 由于算法第4行使用了downto语句，经计数排序，输出序列中值相同的元素之间的相对次序
     * 与他们在输入序列中的相对次序相同，换句话说，计数排序算法是一个稳定的排序算法。
     *
     * @param arrays
     */
    public static void countSort2(int[] arrays) {
        if (arrays == null || arrays.length <= 1) {
            return;
        }
        int b[] = new int[arrays.length];
        int max = arrays[0], min = arrays[0];
        for (int i : arrays) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int k = max - min + 1;
        int c[] = new int[k]; // 缩减空间
        for (int i = 0; i < arrays.length; i++) {
            c[arrays[i] - min] += 1;
        }
        int nk = 0;
        for (int i = 0; i < c.length; i++) {
            while (c[i] > 0) {
                arrays[nk++] = min + i;
                c[i] = c[i] - 1;
            }
        }

    }

    public static void countSort(int[] array, int range) throws Exception {
        if (range <= 0) {
            throw new Exception("range can't be negative or zero.");
        }

        if (array.length <= 1) {
            return;
        }

        int[] countArray = new int[range + 1];
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (value < 0 || value > range) {
                throw new Exception("array element overflow range.");
            }
            countArray[value] += 1;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] temp = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int value = array[i];
            int position = countArray[value] - 1;

            temp[position] = value;
            countArray[value] -= 1;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = temp[i];
        }
    }

    public static void bucketSort(int[] a, int maxValue, int bucketSize) {
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
        for (int i = 0; i < bucket.length; i++) {
            for (Integer data : bucket[i]) {
                a[k++] = data;
            }
        }
    }

    public static void radixSort(int[] data, int radix, int digits) { //radix表示基数（进制），digits表示最大数值位数
        LinkedList<LinkedList> queue = new LinkedList<LinkedList>();
        for (int r = 0; r < radix; r++) {
            LinkedList<Integer> queue1 = new LinkedList<Integer>();
            queue.offer(queue1);   //offer用于queue中（Linkedlist同时实现了List和queue接口），add用于List中
        }
        //最大元素的位数,进行digits次分配和收集
        for (int i = 0; i < digits; i++) {
            //分配数组元素
            for (int j = 0; j < data.length; j++) {
                //得到digits的第i+1位
                int r = (int) (data[j] % Math.pow(radix, i + 1) / Math.pow(radix, i));
                queue.get(r).offer(data[j]);
            }
            //将收集队列元素
            int count = 0;
            for (int k = 0; k < radix; k++) {
                while (queue.get(k).size() > 0) {
                    data[count++] = (Integer) queue.get(k).poll();
                }
            }
        }
    }

    /**
     简单的说就是用数组存放若有数据就标志为1或true，若不存在标志为0或false。比如1，2，2，5，这里最大值为5，0至5中不存0，3，4，所以：
     Array[0]=0，Array[1]=1，Array[2]=2，Array[3]=0，Array[4]=0，Array[5]=1
     上面数中由于2有两个，所以用int存数组的值，不用boolean型，这样如果有多个同样的数字可以用值表示个数。如上面Array[2]=2，就表示2有2个。

     这样排序就方便多了，比如上面开始是{2,5,2,1}这样一无序数组A。找出最大值：5.即用来作位图排序的数组B要申请的大小为5.循环这个数组，把数组A的值用作数组B的下标,如果存在就把值加1，即数组B的值为对应的个数。
     for (int i : A) {
     B[i]++;
     }
     这样B的值最后同上面的Array一样。把B值大于0的输出就是排好序的了。如上面的数组大于0依次有：1，2，2，5.

     从上面可以看出位图排序至少要注意两点：
     1、  最大值和最小值之间不能相差太大，否则浪费空间。
     2、  如果有负数，上面要转换一下，最申请的空间大小为max-min+1，数组B的下标也要作对应的转换，输出前也要转换回去。如int[] arr = { 1, 3, -3, 0, 0};
     */
    public static int[] bitSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for(int i : arr){
            if(i < min){
                min = i;
            }
            if(i > max){
                max = i;
            }
        }
        int count = 0;
        BitArray newArray =  new BitArray(max - min + 1);
        for(int i = 0; i < arr.length; i++){
            if(!newArray.get(arr[i] - min)){
                newArray.set(arr[i] - min, true);
                count++;
            }
        }
        int[] res = new int[count];
        count = 0;
        for(int i = 0; i < newArray.length(); i++){
            if(newArray.get(i)){
                res[count++] = min + i;
            }
        }
        return res;
    }

    public static void oddEvenSort(int[] arr){
        int len = arr.length;
        boolean finish = false;
        while (!finish){
            finish = true;
            for(int i = 0; 2 * i < len; i++){
                int k = 2 * i;
                if(k < len -1 && arr[k] > arr[k + 1]){
                    exchangeElements(arr, k, k + 1);
                    finish = false;
                }
            }
            for(int i = 0; 2 * i < len; i++){
                int k = 2 * i + 1;
                if(k < len -1 && arr[k] > arr[k + 1]){
                    exchangeElements(arr, k, k + 1);
                    finish = false;
                }
            }
        }
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
