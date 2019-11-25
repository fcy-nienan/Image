package Simulating.MiddleMode;

import Simulating.PureMode.IP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;
/*
* represent a method invoke
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessInvoke {
    private static Logger logger = Logger.getLogger(ProcessInvoke.class.getName());
    private IP ip;
    private String methodName;
    private Object[] args;
    public int hashcode(){
        return ip.hashCode()+methodName.hashCode();
    }
}
