package com.fcy.Util.FileStateCheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-19  2:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileNode {
    private File file;
    private long lastModified;
    private FileNode left;
    private FileNode right;
    public FileNode(File f){
        this.file=f;
        this.lastModified=f.lastModified();
    }
    public boolean modified(){
        return file.lastModified()!=lastModified;
    }
    public void resume(){
        this.lastModified=file.lastModified();
    }
}
