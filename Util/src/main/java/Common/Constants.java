package Common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class Constants {
    public static final String ADD_FILE_STRING="文件新增";
    public static final String DELETE_FILE_STRING="文件删除";
    public static final String EQUAL_FILE_STRING="文件相同";
    public static final String NOT_EQUAL_FILE_STRING="文件不同";
    public static final String ADD_CONTENT_STRING="内容新增";
    public static final String DELETE_CONTENT_STRING="内容删除";
    public static final String DIFFERENT_CONTENT_STRING="内容不同";
    public static final String EQUAL_CONTENT_STRING="内容相同";

    public static void main(String[] args) {
        List list=new ArrayList();
        System.out.println(list instanceof Collection);
    }
}
