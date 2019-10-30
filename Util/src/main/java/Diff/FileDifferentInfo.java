package Diff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDifferentInfo {
    static final String ADDENTRY="新增";
    static final String DELETEENTRY="删除";
    static final String ENTRYDIFF="内容不同";
    private String srcName;
    private String dstName;
    private DiffType type;
    private List<ContentDifferentInfo> differentInfos;
}
