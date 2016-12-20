import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by wizardholy on 2016/11/26.
 */
public class IpParse {
    class Ip{
        String host;
        String ip;

        @Override
        public String toString() {
            return "host:"+host+" ip:"+ip;
        }
    }
    public static void main(String[] args) {
        try {
            String content = FileUtils.readFileToString(new File("free2016.txt"), "UTF-8");
            String[] ips = content.split("\r\n");
            for(int i = 0; i < ips.length; i++){
                String[] parts = ips[i].split(":");
                System.out.println("ip:"+parts[0]+" port:"+parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
