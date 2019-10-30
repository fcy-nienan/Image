package OtherSkill;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.InputStream;
import java.io.OutputStream;

public class CompileMain {
    public static void main(String[] args) {
        JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
        InputStream is=null;
        OutputStream out=null;
        OutputStream err=null;
        compiler.run(is,out,err,"-sourcepath","src","test.java");
    }
}
