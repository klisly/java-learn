package designpattern.filter;

import java.util.ArrayList;
import java.util.List;

public class MarriedFilter implements Filter {

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if ("MARRIED".equalsIgnoreCase(person.getMarital())) {
                result.add(person);
            }
        }
        return result;
    }
}