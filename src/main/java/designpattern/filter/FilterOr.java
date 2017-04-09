package designpattern.filter;

import java.util.List;

/**
 * Created by wizardholy on 2017/4/9.
 */
public class FilterOr implements Filter {
    private Filter filter;
    private Filter otherFilter;

    public FilterOr(Filter filter, Filter otherFilter) {
        this.filter = filter;
        this.otherFilter = otherFilter;
    }

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> tmpList1 = filter.filter(persons);
        List<Person> tmpList2 = otherFilter.filter(persons);
        for (Person person : tmpList2) {
            if (!tmpList1.contains(person)) {
                tmpList1.add(person);
            }
        }
        return tmpList1;
    }
}
