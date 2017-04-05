腾讯面试题：给20亿个不重复的unsigned int的整数，没排过序的，然后再给一个数，如何快速判断这个数是否在那40亿个数当中并且所耗内存尽可能的少？

 解析:bitmap算法就好办多了

 所谓bitmap，就是用每一位来存放某种状态，适用于大规模数据，但数据状态又不是很多的情况。通常是用来判断某个数据存不存在的。

 例如，要判断一千万个人的状态，每个人只有两种状态：男人，女人，可以用0，1表示。那么就可以开一个int数组，一个int有32个位，就可以表示32个人。操作的时候可以使用位操作。

一，申请512M的内存

一个bit位代表一个unsigned int值

读入20亿个数，设置相应的bit位

读入要查询的数，查看相应bit位是否为1，为1表示存在，为0表示不存在

二、使用位图法判断整形数组是否存在重复

判断集合中存在重复是常见编程任务之一，当集合中数据量比较大时我们通常希望少进行几次扫描，这时双重循环法就不可取了。

位图法比较适合于这种情况，它的做法是按照集合中最大元素max创建一个长度为max+1的新数组，然后再次扫描原数组，遇到几就给新数组的第几位置上1，如遇到 5就给新数组的第六个元素置1，这样下次再遇到5想置位时发现新数组的第六个元素已经是1了，这说明这次的数据肯定和以前的数据存在着重复。这种给新数组初始化时置零其后置一的做法类似于位图的处理方法故称位图法。它的运算次数最坏的情况为2N。如果已知数组的最大值即能事先给新数组定长的话效率还能提高一倍。

import java.util.BitSet;

/**

 * 大数据处理算法一，bitmap算法

 * @author JYC506

 *

 */

public class Bitmap {



 byte[] tem;



 public Bitmap(int length) {

  this.tem = new byte[length];

 }



 public void add(int num) {

  if (num < tem.length) {

   if (tem[num] != 1) {

    tem[num] = 1;

   }

  }

 }



 public boolean contain(int num) {

  if (num < tem.length) {

   if (tem[num] == 1) {

    return true;

   }

  }

  return false;

 }



 public static void main(String[] args) {

  /*运行前内存*/

  long beforeMemory = Runtime.getRuntime().totalMemory();

  long start1=System.currentTimeMillis();

  BitSet set = new BitSet(2000000000);

  for (int i = 0; i < 2000000000; i++) {

   /*假设898989这个数不在20亿个数里面*/

   if (i != 898989) {

    set.set(i, true);

   }

  }

  /*创建20亿个数后所占内存*/

  long afterMemory = Runtime.getRuntime().totalMemory();

  long end1=System.currentTimeMillis();

  System.out.println("总共内存使用:" + (afterMemory - beforeMemory) / 1024 / 1024 + "MB");

  System.out.println("存入内存耗时:"+(end1-start1)+"毫秒");

  long start2 = System.currentTimeMillis();

  boolean isExit1=set.get(898989);

  boolean isExit2=set.get(900000);



  long end2 = System.currentTimeMillis();

  /*输出在20亿个数中判断898989是否包含在里面*/

  System.out.println(isExit1);

  System.out.println("20个亿中"+(isExit1?"包含":"不包含")+898989);

  System.out.println("20个亿中"+(isExit2?"包含":"不包含")+900000);

  System.out.println("查询用时:"+(end2 - start2)+"毫秒");

 }



}
