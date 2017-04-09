package designpattern.filter;

import java.util.List;

public interface Filter {
    // 根据传过来的Person列表，根据一定的条件过滤，得到目标集合
    List<Person> filter(List<Person> persons);
}