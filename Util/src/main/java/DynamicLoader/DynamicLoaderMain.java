package DynamicLoader;

import java.util.logging.Logger;

public class DynamicLoaderMain {
    public static void main(String args[]) throws Exception {
        String home="D:\\classes";
        ClassLoaderTask task=new ClassLoaderTask(home);
        task.start();
    }
}
