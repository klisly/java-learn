package nio;

import java.nio.IntBuffer;

/**
 * Created by wizardholy on 2017/4/18.
 */
public class TestIntBuffer {
    public static void main(String[] args){
        IntBuffer intBuffer = IntBuffer.allocate(8);
        for(int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put(2 * (i + 1));
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            int j = intBuffer.get();
            System.out.print(j+"\t");
        }
    }
}
