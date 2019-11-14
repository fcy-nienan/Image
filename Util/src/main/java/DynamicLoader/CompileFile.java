package DynamicLoader;

import CommonUtil.IOUtil;
import lombok.extern.slf4j.Slf4j;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.logging.Logger;
@Slf4j
public class CompileFile {
    public static void compile(String src) throws FileNotFoundException {
        File f=new File(src);
        String fileName=f.getName();
        String srcDir=src.substring(0,src.length()-fileName.length());
        JavaCompiler compiler= ToolProvider.getSystemJavaCompiler();

        FileOutputStream outputStream=new FileOutputStream(srcDir+File.separator+"output");
        FileOutputStream errorStream=new FileOutputStream(srcDir+File.separator+"error");
        int run = compiler.run(null,outputStream,errorStream,"-d",srcDir,src);
        IOUtil.closeStream(outputStream);
        IOUtil.closeStream(errorStream);
        log.info("exit status:"+run+"!0:success other:failed!");
    }
}
