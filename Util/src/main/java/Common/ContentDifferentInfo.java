package Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentDifferentInfo {

    private int line;
    private String src;
    private String dst;
    private DiffType type;
}
