package Advanced.Reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Slf4j
public class ReferencedObject {
    private long longValue;
    private float floatValue;

    @Override
    protected void finalize () throws Throwable {
        log.info("this object will be collected!");
    }
}
