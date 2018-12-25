package nlp.maxent;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        String path = "data/train.txt";
        MaxEnt maxEnt = new MaxEnt();
        maxEnt.loadData(path);
        maxEnt.train(200);
        List<String> fieldList = new ArrayList<String>();
        fieldList.add("Sunny"); // 假如天晴
        fieldList.add("Humid"); // 并且湿润
        Pair<String, Double>[] result = maxEnt.predict(fieldList);  // 预测出门和自宅的概率各是多少
        System.out.println(Arrays.toString(result));
    }

}
