本文将整理java.util.Arrays工具类比较常用的方法： 

本文介绍的方法基于JDK 1.7 之上。 

1.  asList方法  

Java代码  收藏代码
@SafeVarargs  
public static <T> List<T> asList(T... a) {  
    return new ArrayList<>(a);  
}  


   使用该方法可以返回一个固定大小的List，如： 

Java代码  收藏代码
List<String> stringList = Arrays.asList("Welcome", "To", "Java",  
        "World!");  
  
List<Integer> intList = Arrays.asList(1, 2, 3, 4);  

   
2. binarySearch方法 

 

binarySearch方法支持在整个数组中查找，如： 

Java代码  收藏代码
int index = Arrays.binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 6);  


以及在某个区间范围内查找， 如： 

Java代码  收藏代码
public static int binarySearch(int[] a, int fromIndex, int toIndex,  
                               int key) {  
    rangeCheck(a.length, fromIndex, toIndex);  
    return binarySearch0(a, fromIndex, toIndex, key);  
}  

Java代码  收藏代码
int index = Arrays.binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 6, 6);  


3. copyOf及copyOfRange方法 


 


如： 

Java代码  收藏代码
String[] names2 = { "Eric", "John", "Alan", "Liz" };  
  
  
        //[Eric, John, Alan]  
        String[] copy = Arrays.copyOf(names2, 3);  
          
        //[Alan, Liz]  
        String[] rangeCopy = Arrays.copyOfRange(names2, 2,  
                names2.length);  


4. sort方法 

Java代码  收藏代码
String[] names = { "Liz", "John", "Eric", "Alan" };  
//只排序前两个  
//[John, Liz, Eric, Alan]  
Arrays.sort(names, 0, 2);  
//全部排序  
//[Alan, Eric, John, Liz]  
Arrays.sort(names);  


另外，Arrays的sort方法也可以结合比较器，完成更加复杂的排序。 
Java代码  收藏代码
public static <T> void sort(T[] a, Comparator<? super T> c) {  
    if (LegacyMergeSort.userRequested)  
        legacyMergeSort(a, c);  
    else  
        TimSort.sort(a, c);  
}  


5. toString方法 

Arrays的toString方法可以方便我们打印出数组内容。 

如： 

Java代码  收藏代码
String[] names = { "Liz", "John", "Eric", "Alan" };  
Arrays.sort(names);  
System.out.println(Arrays.toString(names));  


控制台将打印出 [Alan, Eric, John, Liz] 

6. deepToString方法 

如果需要打印二维数组的内容： 

int[][] stuGrades = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } }; 

如果直接用
Java代码  收藏代码
System.out.println(Arrays.toString(stuGrades));  
那么得到的结果类似于 
     [[I@35ce36, [I@757aef, [I@d9f9c3]} 

这个时候得用deepToString方法才能得到正确的结果[[80, 81, 82], [84, 85, 86], [87, 88, 89]] 

Java代码  收藏代码
System.out.println(Arrays.deepToString(stuGrades));  


7. equals方法 

使用Arrays.equals来比较1维数组是否相等。 

Java代码  收藏代码
String[] names1 = { "Eric", "John", "Alan", "Liz" };  
  
        String[] names2 = { "Eric", "John", "Alan", "Liz" };  
  
        System.out.println(Arrays.equals(names1, names2));  



8. deepEquals方法 

Arrays.deepEquals能够去判断更加复杂的数组是否相等。 

Java代码  收藏代码
int[][] stuGrades1 = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };  
  
        int[][] stuGrades2 = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };  
  
        System.out.println(Arrays.deepEquals(stuGrades1, stuGrades2));  


9. fill方法 
Java代码  收藏代码
int[] array1 = new int[8];  
        Arrays.fill(array1, 1);  
        //[1, 1, 1, 1, 1, 1, 1, 1]  
        System.out.println(Arrays.toString(array1));  
