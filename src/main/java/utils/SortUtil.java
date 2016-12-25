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
                        int k = j ;
                        while (k >= 0 && numbers[k] < tmp) {
                            numbers[k + gap] = numbers[k];
                            if(k >= gap){
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


    public static void out(int[] number) {
        System.out.println();
        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i]);
            System.out.print("\t");
        }
        System.out.println();
    }
}
