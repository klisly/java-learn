package designpattern.filter;

import java.util.ArrayList;
import java.util.List;

public class FemaleFilter implements Filter {

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if ("FEMALE".equalsIgnoreCase(person.getGender())) {
                result.add(person);
            }
        }
        return result;
    }
}