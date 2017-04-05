package feature.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by wizardholy on 2017/4/1.
 */
public class Java8Test {
    public static void main(String[] args){
        List<Integer> ints = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> max = ints.parallelStream().findAny();
        System.out.println("max:"+max.get());
        System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
    }
}
