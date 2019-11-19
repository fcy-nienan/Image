package CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.logging.Logger;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UniqueID {
    private String id;
    private Date applyDate;
    private String applyAccount;
    private boolean applyFlag;
    @Override
    public boolean equals(Object o){
        if (o instanceof UniqueID){
            UniqueID other= (UniqueID) o;
            if (other.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
