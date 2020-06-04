package stack;

import java.util.List;

public class NestedInteger {
    private List<NestedInteger> list;
    private int value;
    public boolean isInteger(){
        return list==null;
    }
    public Integer getInteger(){
        return value;
    }
    public List<NestedInteger> getList(){
        return list;
    }
}
