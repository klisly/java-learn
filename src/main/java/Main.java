import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File("/Users/wizardholy/Desktop/pics");
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files) {
            f.renameTo(new File("/Users/wizardholy/Desktop/pics/cover_" + count + ".jpg"));
            count++;
        }
    }
}
