package com.fcy.Util.FileTransfer.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class CreateFileCommand implements Command {
    private static Logger logger = Logger.getLogger(CreateFileCommand.class.getName());
    private String path;
    private byte[] bytes;
    public CreateFileCommand(String path,byte[] bytes){
        this.path=path;
        this.bytes=bytes;
    }
    @Override
    public void execute() {
        File file=new File(path);
        if (!file.exists()&&EnvironmentVariable.overwrite){
            FileOutputStream outputStream=null;
            try {
                outputStream=new FileOutputStream(file);
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            IOUtil.closeStream(outputStream);
        }
    }
}
