package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wizardholy on 2017/4/18.
 */
public class FileReader {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(new File("free2016.txt"));
        FileChannel fc = fin.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        fc.read(buffer);
        buffer.flip(); //将buffer有当前的写模式转换为读模式
        while (buffer.hasRemaining()){
            byte b = buffer.get();
            System.out.print((char)b);
        }
        fin.close();

//        RandomAccessFile aFile = new RandomAccessFile("free2016.txt", "rw");
//        FileChannel inChannel = aFile.getChannel();
//
//        ByteBuffer buf = ByteBuffer.allocate(48);
//
//        int bytesRead = inChannel.read(buf);
//        while (bytesRead != -1) {
//
//            System.out.println("\n Read " + bytesRead);
//            buf.flip();
//
//            while(buf.hasRemaining()){
//                System.out.print((char) buf.get());
//            }
//
//            buf.clear();
//            bytesRead = inChannel.read(buf);
//        }
//        aFile.close();
    }
}
