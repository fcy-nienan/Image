package DesignPattern1.Visit;

import java.util.Iterator;
import java.util.List;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-06-25  16:23
 */
public class System {
    private List<Acceptor> acceptors;
    public Iterator iterator(){
        return acceptors.iterator();
    }
}
