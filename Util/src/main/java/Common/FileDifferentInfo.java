package Common;

import Common.ContentDifferentInfo;
import Common.DiffType;
import CommonUtil.IOUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDifferentInfo {
    private String srcName;
    private String dstName;
    private DiffType type;
    private List<ContentDifferentInfo> differentInfos;
    @Override
    public String toString(){
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(this.getClass().getSimpleName())
                    .append(IOUtil.getLineSeparator());
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object o=field.get(this);
                builder.append(field.getName())
                        .append("=");
                if (o instanceof String)
                    builder.append(field.get(this));
                else if (o instanceof Collection){
                    builder.append("[")
                            .append(IOUtil.getLineSeparator());
                    Iterator iterator=((Collection) o).iterator();
                    while (iterator.hasNext()){
                        builder.append('\t');
                        builder.append(iterator.next().toString());
                        builder.append(IOUtil.getLineSeparator());
                    }
                    builder.append("]");
                }else if (o instanceof Map){
                    builder.append("[")
                            .append(IOUtil.getLineSeparator());
                    Iterator<Map.Entry> iterator=((Map) o).entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry entry=iterator.next();
                        builder.append('\t')
                                .append(entry.getKey())
                                .append(":")
                                .append(entry.getValue())
                                .append(IOUtil.getLineSeparator());
                    }
                    builder.append("]");
                }else{
                    builder.append(o.toString());
                }
                builder.append(IOUtil.getLineSeparator());
            }
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
            return super.toString();
        }
    }
}
