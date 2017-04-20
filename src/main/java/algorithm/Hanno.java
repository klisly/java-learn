package algorithm;

/**
 问题描述：

 有一个梵塔，塔内有三个座A、B、C，A座上有诺干个盘子，盘子大小不等，大的在下，小的在上（如图）。

 把这些个盘子从A座移到C座，中间可以借用B座但每次只能允许移动一个盘子，并且在移动过程中，3个座上的盘

 子始终保持大盘在下，小盘在上。

 描述简化：把A柱上的n个盘子移动到C柱，其中可以借用B柱。
 */
public class Hanno {
    public static void main(String[] args){
        int n = 10; // n表示有几个盘子
        moveHanno(n, "A", "B", "C"); // 先从A移动到C

    }

    private static void moveHanno(int n, String from, String to, String tmp) {
        if(n > 1){
            moveHanno(n-1, from, tmp, to);
            System.out.println("move no."+n+" to "+to);
            moveHanno(n-1, tmp, to, from);
        } else {
            System.out.println("move no."+n+" to "+to);
        }
    }
}
