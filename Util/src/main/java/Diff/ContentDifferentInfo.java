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
public class ContentDifferentInfo {
    private int line;
    private String src;
    private String dst;
    private DiffType type;
    private static Logger logger = Logger.getLogger(ContentDifferentInfo.class.getName());
}
