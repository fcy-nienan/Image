package FileStateCheck;

import lombok.Getter;

import java.io.File;

/**
 * @descripiton:
 * @author: fcy
 * @date: 2019-05-19  13:36
 */
//统计一个目录下的所有文件个数
public class CountDirTask implements Runnable {
    private String path;//路径
    @Getter
    private long sum;//文件总数
    @Getter
    private long cost;//花费时间
    public CountDirTask(String path){
        this.path=path;
    }
    @Override
    public void run() {
        long start=System.currentTimeMillis();
        this.sum=countDir(new File(path));
        long end=System.currentTimeMillis();
        this.cost=end-start;
        showResult();
    }
    public void showResult(){
        System.out.printf("%s--%s--%s\r\n",path,cost,sum);
        Long count=Count.countMap.get("total");
        if (count==null){
            Count.countMap.put(Count.FILE_COUNT,sum);
        }else{
            Count.countMap.put(Count.FILE_COUNT,sum+count);
        }
    }
    private int countDir(File file){
        if (!file.exists())return 0;
        if (!file.canRead())return 0;
        if (file.isFile()){
            return 1;
        }
        File[] files=file.listFiles();
        if (files==null)return 0;
        int sum=0;
        for(File f:files){
            sum+=countDir(f);
        }
        return sum;
    }
}
