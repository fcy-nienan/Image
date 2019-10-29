package DesignPattern.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2018-11-24  1:49
 */
public class NameFilter implements Filter {
    @Override
    public List<Person> filter(List<Person> personList) {
        List<Person> result=new ArrayList<>();
        personList.forEach(e->{
            if (e.getName().length()<6){
                result.add(e);
            }
        });
        return result;
    }
}
