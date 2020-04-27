package groupby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.logging.Logger;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SoutParam {
    private String format;
    private Object args;
}
