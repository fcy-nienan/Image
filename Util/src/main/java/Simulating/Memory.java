package Simulating;

import CommonUtil.IOUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Memory implements Runnable{
    private static Logger logger = Logger.getLogger(Memory.class.getName());
    private static final String startMsg="memory process is running....";
    private Map<Object,Object> memory=new HashMap<Object, Object>();
    public static void main(String args[]) throws Exception {
        Runtime runtime= (Runtime) Runtime.class.getDeclaredMethod("getRuntime").invoke(null);
        Method method=null;
        Method exec = runtime.getClass().getDeclaredMethod("exec", String.class);
        Object invoke1 = exec.invoke(runtime, "cmd /c dir");
        Process p= (Process) invoke1;
        IOUtil.disPlayStream(p.getInputStream());
        for (Method declaredMethod : runtime.getClass().getDeclaredMethods()) {
            Type[] genericParameterTypes = declaredMethod.getGenericParameterTypes();
            if (declaredMethod.getName().equals("exec")&&genericParameterTypes.length==1){
                for (Type genericParameterType : genericParameterTypes) {
                    System.out.println(genericParameterType.getTypeName());
                }
                method=declaredMethod;
                break;
            }
        }
        Object invoke = method.invoke(runtime, "cmd /c dir");
        Process process= (Process) invoke;
        IOUtil.disPlayStream(process.getInputStream());
    }

    @Override
    public void run() {
        logger.info(startMsg);
    }
    public Object put(Object key,Object value){
        return memory.put(key, value);
    }
    public Object remove(Object key,Object value){
        return memory.remove(key);
    }
}
