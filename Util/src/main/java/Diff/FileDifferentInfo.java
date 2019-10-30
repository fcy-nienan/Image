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
    private String srcName;
    private String dstName;
    private List<ContentDifferentInfo> differentInfos;
}
