package Parser.Demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JDTParserDemo {
    /**
     * get compilation unit of source code
     * @param javaFilePath
     * @return CompilationUnit
     */
//    public static CompilationUnit getCompilationUnit(String javaFilePath){
//        byte[] input = null;
//        try {
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
//            input = new byte[bufferedInputStream.available()];
//            bufferedInputStream.read(input);
//            bufferedInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
////        ASTParser astParser = ASTParser.newParser(AST.JLS3);
////        astParser.setSource(new String(input).toCharArray());
////        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
////
////        CompilationUnit result = (CompilationUnit) (astParser.createAST(null));
//
//        return result;
//    }
}