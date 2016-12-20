import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> datas = new ArrayList<Integer>();
        datas.add(1);
        datas.add(3);
        datas.add(5);
        datas.add(7);
        datas.add(9);
        datas.add(11);
        datas.add(13);
        datas.add(15);
        for (int i = 0; i < datas.size(); i++) {
            for (int j = 0; j < datas.size(); j++) {
                for (int k = 0; k < datas.size(); k++) {
                    if(datas.get(i)+datas.get(j)+datas.get(k) == 30){
                        System.out.println(datas.get(i)+" "+datas.get(j)+datas.get(k));
                    }
                }
            }
        }
    }
}
