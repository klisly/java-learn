public class Main {

    public static void main(String[] args) {
//        File file = new File("/Users/wizardholy/Desktop/pics");
//        File[] files = file.listFiles();
//        int count = 0;
//        for (File f : files) {
//            f.renameTo(new File("/Users/wizardholy/Desktop/pics/cover_" + count + ".jpg"));
//            count++;
//        }
        System.out.println(getGBS(8, 2));
    }

    public static int getGBS(int x, int y) {
        for (int i = 1; i <= x * y; i++) {
            if (i % x == 0 && i % y == 0) return i;
        }

        return x * y - 1;
    }
}
