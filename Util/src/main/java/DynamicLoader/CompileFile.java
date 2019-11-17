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
    private static String errorPath="ERROR";
    private static String outputPath="OUTPUT";
    private static String errorSuffix=".ERROR";
    private static String outputSuffix=".OUTPUT";
    public static void compile(String src) throws FileNotFoundException {
        log.info("start to compile file {}",src);
        File f=new File(src);
        String fileName=f.getName();
        String srcDir=src.substring(0,src.length()-fileName.length());
        JavaCompiler compiler= ToolProvider.getSystemJavaCompiler();
        String ep=srcDir+File.separator+errorPath+File.separator+fileName+errorSuffix;
        String op=srcDir+File.separator+outputPath+File.separator+fileName+outputSuffix;
        File errorDir=new File(srcDir+File.separator+errorPath);
        File outputDir=new File(srcDir+File.separator+outputPath);
        if (!errorDir.exists())errorDir.mkdirs();
        if (!outputDir.exists())outputDir.mkdirs();
        FileOutputStream outputStream=new FileOutputStream(ep);
        FileOutputStream errorStream=new FileOutputStream(op);
        int run = compiler.run(null,outputStream,errorStream,"-d",srcDir,src);
        IOUtil.closeStream(outputStream);
        IOUtil.closeStream(errorStream);
        log.info("exit status:"+run+"!0:success other:failed!");
    }
}
