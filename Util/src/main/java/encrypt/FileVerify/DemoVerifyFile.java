package encrypt.FileVerify;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_256;
import static org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_512;
//校验文件的完整性
//在IDEA中官网中看到的SHA_256校验码也是3e83f5eec7ed71fe075a439822b0caaeabf2f9e87c36919246ebae6d44506367
//下面程序运行的结果也是3e83f5eec7ed71fe075a439822b0caaeabf2f9e87c36919246ebae6d44506367
public class DemoVerifyFile {
    public static void start() throws IOException {
        File file = new File("C:\\Users\\80771\\Downloads\\CLion-2020.1.1.exe");
        String hex = new DigestUtils(SHA_256).digestAsHex(file);
        System.out.println(hex);
    }
}
