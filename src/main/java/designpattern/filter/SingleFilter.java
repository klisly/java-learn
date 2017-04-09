package designpattern.filter;

import java.util.ArrayList;
import java.util.List;

public class SingleFilter implements Filter {

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if ("SINGLE".equalsIgnoreCase(person.getMarital())) {
                result.add(person);
            }
        }
        return result;
    }
}