package Advanced.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

public class DemoTimerTask {
    private static Logger logger = Logger.getLogger(DemoTimerTask.class.getName());

    public static void main(String args[]) throws Exception {
        List list=new ArrayList();
        list.add("1");
        List<Object[]> tt=list;
        List<String> kk=(List)tt;
        System.out.println(kk.get(0).toString());
    }
}
