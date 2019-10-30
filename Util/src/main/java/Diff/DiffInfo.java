package Diff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.logging.Logger;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiffInfo {
    private static final String ADDSTRING="新增";
    private static final String DELETESTRING="删除";
    private static final String CONTENTDIFFSTRING="内容不同";
    private int line;
    private String src;
    private String dst;
    private DiffType type;
    private static Logger logger = Logger.getLogger(DiffInfo.class.getName());
    enum DiffType{
        ADD(0,ADDSTRING),DELETE(1,DELETESTRING),CONTENTDIFF(2,CONTENTDIFFSTRING);
        private int inddex;
        private String description;
        DiffType(int index,String description){
            this.inddex=index;
            this.description=description;
        }
        public String toString(){
            return this.description;
        }
    }
}
