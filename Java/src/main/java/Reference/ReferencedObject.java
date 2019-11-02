package Reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ReferencedObject {
    private long longValue;
    private float floatValue;
}
