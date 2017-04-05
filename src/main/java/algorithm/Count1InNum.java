package algorithm;

/**
 * 算法
 */
public class Count1InNum {
    // 普通算法
    public static int bitCount(int n){
      int c = 0;
      for(c = 0; n > 0; n >>= 1){
          c += n & 1;
      }
      return c;
    }

    // 快速法
    public static int bitCountQuick(int n){
        int c = 0;
        for(c = 0; n > 0; ++c){
            n &= (n - 1);
        }
        return c;
    }


    public static void main(String[] args){
        System.out.println("bitCount:"+bitCount(15));
        System.out.println("bitCountQuick:"+bitCountQuick(15));
    }
}
