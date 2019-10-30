package CommonUtil;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
    private static ObjectMapper mapper=new ObjectMapper();
    private static Object obj=new Object();
    /*
    对象和json数据之间的转换
    bean 类需要配置无参构造函数
    bean如果是该类的内部类会报错
     */
    public static Object stringToobject(String text,Class t){
        try{
            obj=mapper.readValue(text,t);
        }catch(Exception e){
            return null;
        }
        return obj;
    }
    public static String objectToString(Object obj){
        String str=null;
        try{
            str=mapper.writeValueAsString(obj);
        }catch(Exception e){
            return str;
        }
        return str;
    }

    public static void main(String[] args) {

    }

}
