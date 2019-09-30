package com.fcy.Util.FileTransfer.Common;

import java.io.File;
import java.util.logging.Logger;

public class MkDirCommand implements Command{
    private static Logger logger = Logger.getLogger(MkDirCommand.class.getName());
    private String path;
    public MkDirCommand(String path){
        if (path==null)throw new NullPointerException();
        IOUtil.checkDirPath(path);
    }
    @Override
    public void execute() {
        File file=new File(path);
        if (!file.exists()){
            if (file.isDirectory()){
                file.mkdirs();
            }else{
                logger.info("invalid directory !"+path);
            }
        }
    }

    public static void main(String args[]) throws Exception {

    }
}
