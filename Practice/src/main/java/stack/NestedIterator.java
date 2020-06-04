package stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator {
    List<NestedInteger> list;
    List<Integer> integers=new ArrayList<>();
    Iterator<Integer> iterator;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.list=nestedList;
        receive(list,integers);
        iterator=integers.iterator();
    }
    private void receive(List<NestedInteger> nestedIntegers,List<Integer> list){
        if (nestedIntegers==null)return;
        for(int i=0;i<nestedIntegers.size();i++){
            NestedInteger nestedInteger=nestedIntegers.get(i);
            if (nestedInteger.isInteger()){
                list.add(nestedInteger.getInteger());
            }else{
                receive(nestedInteger.getList(),list);
            }
        }
    }

    public Integer next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}
