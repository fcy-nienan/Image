package groupby;

import java.util.logging.Logger;

public class Cell<T extends Object> {
    private T t;
    public Cell(T t){
        this.t=t;
    }
    public T get(){
        return this.t;
    }
    public void set(T t){
        this.t=t;
    }
    @Override
    public String toString(){
        return this.t.toString();
    }
}
