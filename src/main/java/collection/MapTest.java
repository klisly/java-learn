package collection;

import java.util.*;

/**
 * Created by wizardholy on 2017/3/28.
 */
public class MapTest {
    

    public static void main(String[] args) throws Exception {
        MapTest test = new MapTest();
        test.useHashMap();
        test.useTreeMap();
        test.useLikedHashMap();
    }

    public void useHashMap() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Level 1");
        map.put("2", "Level 2");
        map.put("3", "Level 3");
        map.put("a", "Level a");
        map.put("b", "Level b");
        map.put("c", "Level c");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            System.out.println("Key: " + e.getKey() + ";   Value: " + e.getValue());
        }
    }

    // 有序(默认排序，不能指定)   
    public void useTreeMap() throws Exception {
        System.out.println("------有序（但是按默认顺充，不能指定）------");
        Map<String, String> map = new TreeMap<String, String>();
        map.put("1", "Level 1");
        map.put("2", "Level 2");
        map.put("3", "Level 3");
        map.put("a", "Level a");
        map.put("b", "Level b");
        map.put("c", "Level c");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            System.out.println("Key: " + e.getKey() + ";   Value: " + e.getValue());
        }
    }

    public void useLikedHashMap() throws Exception {
        System.out.println("------有序（根据输入的顺序输出）------");
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "Level 1");
        map.put("2", "Level 2");
        map.put("3", "Level 3");
        map.put("a", "Level a");
        map.put("b", "Level b");
        map.put("c", "Level c");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            System.out.println("Key: " + e.getKey() + ";   Value: " + e.getValue());
        }
    }
    
}
