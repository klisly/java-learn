package utils;

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
        int temp; // 记录临时中间值
        int size = numbers.length; // 数组大小
        for (int i = size - 1; i > 0; --i) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j + 1]) { // 交换两数的位置
                    temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
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
            int base = numbers[start];
            int l = start;
            int h = end;
            do {
                while (h >= l && numbers[h] > base) {
                    h--;
                }
                while (l <= h && numbers[l] < base) {
                    l++;
                }
                if (l <= h) {
                    int tmp = numbers[l];
                    numbers[l] = numbers[h];
                    numbers[h] = tmp;
                    l++;
                    h--;
                }
            } while (l <= h);
            if (l < end) {
                quickSort(numbers, l, end);
            }
            if (h > start) {
                quickSort(numbers, start, l - 1);
            }
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
        int size = numbers.length, temp;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) k = j;
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
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
        for (int i = 1; i <= size - 1; i++) {
            tmp = numbers[i];
            for (j = i; j > 0 && numbers[j - 1] < tmp; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = tmp;
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
        if (left < right) {
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
        int i = left, j = mid + 1;
        int m = mid, n = right;
        int k = 0;
        while (i <= m && j <= n) {
            if (data[i] < data[j]) {
                tmp[k++] = data[i++];
            } else {
                tmp[k++] = data[j++];
            }
        }

        while (i <= m) {
            tmp[k++] = data[i++];
        }

        while (j <= m) {
            tmp[k++] = data[j++];
        }
        for (i = 0; i < k; i++) {
            data[left + i] = tmp[i];
        }
    }

    public static void shellSort(int[] numbers) {
        int length = numbers.length;
        int i, j, gap;

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

        for (gap = length / 2; gap > 0; gap /= 2) {
            for (i = 0; i < gap; i++) {
                for (j = i; j + gap < length; j += gap) {
                    if (numbers[j] < numbers[j + gap]) {
                        int tmp = numbers[j + gap];
                        int k = j;
                        while (k >= 0 && numbers[k] < tmp) {
                            numbers[k + gap] = numbers[k];
                            if (k >= gap) {
                                k -= gap;
                            } else {
                                break;
                            }
                        }
                        numbers[k] = tmp;
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
        buildHeap(arrays);//初始建堆，array[0]为第一趟值最大的元素
        for(int i = arrays.length - 1; i > 1; i--){
            int temp = arrays[0]; //将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
            arrays[0] = arrays[i];
            arrays[i] = temp;
            adjustDownToUp(arrays, 0, i);

        }
    }

    public static void buildHeap(int[] arrays) {
        for (int i = (arrays.length / 2 - 1); i >= 0; i--) {
            adjustDownToUp(arrays, i, arrays.length);
        }
    }


    //删除堆顶元素操作
    public int[] deleteMax(int[] array){
        //将堆的最后一个元素与堆顶元素交换，堆底元素值设为-99999
        array[0] = array[array.length-1];
        array[array.length-1] = -99999;
        //对此时的根节点进行向下调整
        adjustDownToUp(array, 0, array.length);
        return array;
    }

    //插入操作:向大根堆array中插入数据data
    public int[] insertData(int[] array, int data){
        array[array.length-1] = data; //将新节点放在堆的末端
        int k = array.length-1;  //需要调整的节点
        int parent = (k-1)/2;    //双亲节点
        while(parent >=0 && data>array[parent]){
            array[k] = array[parent];  //双亲节点下调
            k = parent;
            if(parent != 0){
                parent = (parent-1)/2;  //继续向上比较
            }else{  //根节点已调整完毕，跳出循环
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

    public static void out(int[] number) {
        System.out.println();
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i]);
            System.out.print("\t");
        }
        System.out.println();
    }
}
