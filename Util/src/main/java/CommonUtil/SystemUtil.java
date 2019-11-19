package CommonUtil;

import java.util.logging.Logger;

public class SystemUtil {
    private static Logger logger = Logger.getLogger(SystemUtil.class.getName());

    public static void main(String args[]) throws Exception {

    }
    public static String getFileEncoding(){
        return System.getProperty("file.encoding");
    }
}
