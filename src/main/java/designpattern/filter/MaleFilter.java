package designpattern.filter;

import java.util.ArrayList;
import java.util.List;

public class MaleFilter implements Filter {

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if ("MALE".equalsIgnoreCase(person.getGender())) {
                result.add(person);
            }
        }
        return result;
    }
}