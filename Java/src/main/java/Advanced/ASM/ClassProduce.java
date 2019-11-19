package Advanced.ASM;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.util.logging.Logger;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class ClassProduce {
    private static Logger logger = Logger.getLogger(ClassProduce.class.getName());

    public static void main(String args[]) throws Exception {
        ClassReader cr = new ClassReader(ClassProduce.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        cr.accept(cw, Opcodes.ASM5);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "newFunc", "(Ljava/lang/String;)V", null, null);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitEnd();

        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();

        //将二进制流写到out/下
        FileOutputStream fos = new FileOutputStream("Bazhang222.class");
        fos.write(code);
        fos.close();
    }
}
