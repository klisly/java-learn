package designpattern.filter;

import java.util.List;

/**
 * Created by wizardholy on 2017/4/9.
 */
public class FilterAnd implements Filter{
    private Filter filter;
    private Filter otherFilter;

    public FilterAnd(Filter filter, Filter otherFilter) {
        this.filter = filter;
        this.otherFilter = otherFilter;
    }

    @Override
    public List<Person> filter(List<Person> persons) {
        List<Person> tmpList = filter.filter(persons);
        return otherFilter.filter(tmpList);
    }
}
