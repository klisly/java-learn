package nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wizardholy on 2017/4/18.
 */
public class FileWritter {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[]{83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};
        FileOutputStream fout = new FileOutputStream(new File("free.txt"));
        FileChannel fc = fout.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        output("初始化", buffer);
        for (int i = 0; i < bytes.length; i++) {
            output("输入", buffer);
            buffer.put(bytes[i]);
        }
        buffer.flip();
        output("输出", buffer);
        fc.write(buffer);
        fout.close();
    }

    public static void output(String step, Buffer buffer) {
        System.out.println(step + " : ");
        System.out.print("capacity: " + buffer.capacity() + ", ");
        System.out.print("position: " + buffer.position() + ", ");
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }
}
